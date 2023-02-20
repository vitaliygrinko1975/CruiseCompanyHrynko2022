package ua.nure.hrynko.services;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.MySqlAccountDAO;
import ua.nure.hrynko.dao.MySqlCruiseDAO;
import ua.nure.hrynko.dao.MySqlOrderDAO;
import ua.nure.hrynko.dao.MySqlUserDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class AllMethodsWithTransactions {
    private static final long serialVersionUID = 7732286214029478505L;
    private static final Logger LOG = Logger.getLogger(AllMethodsWithTransactions.class);

    private MySqlOrderDAO orderDAO;
    private MySqlCruiseDAO cruiseDAO;
    private MySqlUserDAO userDAO;
    private MySqlAccountDAO accountDAO;
    List<String> errors;

    public int addBasketToOrdersDbReturnDepositAmount(Integer userId, Map<Integer, Integer> mapForBasket) throws DBException {

        orderDAO = MySqlOrderDAO.getInstance();
        cruiseDAO = MySqlCruiseDAO.getInstance();
        int depositAmount = 0;

        //START TRANSACTION
        Connection con = DBManager.getInstance().getConnection();
        try {
            con.setAutoCommit(false);

            for (Map.Entry<Integer, Integer> item : mapForBasket.entrySet()) {
               Cruise currentCruise = cruiseDAO.findCruiseById(con,item.getKey());
                depositAmount += currentCruise.getPrice() * item.getValue();
                for (int i = 0; i < item.getValue(); i++) {
                    int cruiseId = item.getKey();
                    int newCapacity = currentCruise.getCapacity() - 1;
                    if (newCapacity >= 0) {
                        orderDAO.addItemToOrdersDb(con, userId, cruiseId, "В обработке");
                        currentCruise.setCapacity(newCapacity);
                        cruiseDAO.updateCruisesDb(con, currentCruise);
                    } else {
                        orderDAO.addItemToOrdersDb(con, userId, cruiseId, "Нет свободных мест");
                    }
                }
            }
            con.commit();
        } catch (Exception ex) {
            DBManager.rollback(con);
            LOG.error("cannot confirm order of selected units", ex);
            throw new DBException("cannot confirm order of selected units", ex);
        } finally {
            DBManager.close(con);
        }//END TRANSACTION
        return depositAmount;

    }

    public String changeStatusWithWithdrawalFromDeposit(int orderIdForUpdate, String status) throws DBException {
        orderDAO = MySqlOrderDAO.getInstance();
        cruiseDAO = MySqlCruiseDAO.getInstance();
        userDAO = MySqlUserDAO.getInstance();
        accountDAO = MySqlAccountDAO.getInstance();

        String message = "Оплачено";
        //START TRANSACTION
        Connection con = DBManager.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            Order orderForUpdate = orderDAO.findOrderById(con, orderIdForUpdate);
            User userWithOrder = userDAO.findUserById(con, orderForUpdate.getUserId());
            LOG.trace("userWithOrder --> " + userWithOrder);
            Cruise cruiseWithOrder = cruiseDAO.findCruiseById(con, orderForUpdate.getCruiseId());
            int accountsId = userWithOrder.getAccountsId();
            LOG.trace("accountsId --> " + accountsId);
            Account usersAccount = accountDAO.findAccountById(con, accountsId);
            double currentBalance = usersAccount.getBalance();
            LOG.trace("currentBalance --> " + currentBalance);
            double balanceReducer = cruiseWithOrder.getPrice();
            LOG.trace("balanceReducer --> " + balanceReducer);
            double newBalance = currentBalance - balanceReducer;
            LOG.trace("newBalance --> " + newBalance);
            if (newBalance >= 0 && !status.equals(message)) {
                accountDAO.updateAccountToDb(con, orderForUpdate.getUserId(), newBalance);
                orderDAO.updateItemOrder(con, orderIdForUpdate, message);
            }
            if (newBalance < 0 && !status.equals("Оплачено")) {
                message = "На счете не достаточно средств";
                orderDAO.updateItemOrder(con, orderIdForUpdate, message);
            }
            con.commit();
        } catch (Exception ex) {
            DBManager.rollback(con);
            LOG.error("cannot change status of selected unit", ex);
            throw new DBException("cannot change status of selected unit", ex);
        } finally {
            DBManager.close(con);
        }//END TRANSACTION

        return message;
    }

    public String signUpUserAndAddNewItemToAccountDb(HttpServletRequest request) throws AppException{
        SignUpValidator signUpValidator = new SignUpValidator();
        HttpSession session = request.getSession();
        EncodePassword encodePassword = new EncodePassword();
        String login = request.getParameter("addLoginUser");
        LOG.trace("Request parameter: login --> " + login);
        String password = encodePassword.getHashPassword(request.getParameter("addPasswordUser"));
        LOG.trace("Request parameter: password --> " + password);
        String firstName = request.getParameter("addFirstNameUser");
        LOG.trace("Request parameter: firstName --> " + firstName);
        String lastName = request.getParameter("addLastNameUser");
        LOG.trace("Request parameter: lastName --> " + lastName);
        String email = request.getParameter("addEmailUser");
        LOG.trace("Request parameter: email --> " + email);
        String phone = request.getParameter("addPhoneUser");
        LOG.trace("Request parameter: phone --> " + phone);

        errors = signUpValidator.registerValidate(request);

        session.setAttribute("errors", errors);
        LOG.trace("Set session attribute: errors --> " + errors);
        // TRANSACTION
        Connection con = DBManager.getInstance().getConnection();
        userDAO = MySqlUserDAO.getInstance();
        accountDAO = MySqlAccountDAO.getInstance();

        try {
            con.setAutoCommit(false);
            List<User> userList = userDAO.findAllUsers(con);
            LOG.trace("find all users to userList : --> " + userList);
            if (signUpValidator.checkForUniquenessLogin(userList, login)) {
                String message = "Этот логин уже существует. Введите другой.";
                session.setAttribute("message", message);
                LOG.trace("Set session attribute: message --> " + message);
                session.setAttribute("errors", errors);
                return Path.PAGE_ERROR_PAGE;
            }
            if (signUpValidator.checkForUniquenessEmail(userList, email)) {
                String message = "Пользователь с таким адресом электронной почты уже существует. Введите другой адрес.";
                session.setAttribute("message", message);
                LOG.trace("Set session attribute: message --> " + message);
                session.setAttribute("errors", errors);
                LOG.trace("Set session attribute: errors --> " + errors);
                return Path.PAGE_ERROR_PAGE;
            }
            accountDAO.addAccountsDb(con, 0);
            List<Account> listAccounts = accountDAO.findAllAccounts(con);
            LOG.trace("Add new account on DB: --> ");
            int accountId = listAccounts.stream().map(Entity::getId).max(Integer::compare).get();
            LOG.trace("Last generated Id table Accounts DB: --> " + accountId);
            userDAO.addToUsersDb(con, login, password, firstName, lastName, email, phone, 2, accountId);
            LOG.trace("add user : --> ");

            LOG.debug("SignUpCommand finished");

            con.commit();

            return "login.jsp";

        } catch (Exception ex) {
            DBManager.rollback(con);
        } finally {
            DBManager.close(con);
        }//END TRANSACTION

        return Path.PAGE_ERROR_PAGE;
    }

    public String adminAddNewUserAndAddNewItemToAccountDb(HttpServletRequest request, String login, String password,
                                 String firstName, String lastName, String email, String phone) throws DBException {

        HttpSession session = request.getSession();
        // TRANSACTION
        Connection con = DBManager.getInstance().getConnection();
        userDAO = MySqlUserDAO.getInstance();
        accountDAO = MySqlAccountDAO.getInstance();

        try {
            con.setAutoCommit(false);
            List<User> userList = userDAO.findAllUsers(con);
            LOG.trace("find all users to userList : --> " + userList);
            SignUpValidator validator = new SignUpValidator();
            if (validator.checkForUniquenessLogin(userList, login)) {
                String message = "Этот логин уже существует. Введите другой.";
                session.setAttribute("message", message);
                return Path.PAGE_ERROR_PAGE;
            }
            if (validator.checkForUniquenessEmail(userList, email)) {
                String message = "Пользователь с таким адресом электронной почты уже существует. Введите другой адрес.";
                session.setAttribute("message", message);

                return Path.PAGE_ERROR_PAGE;
            }
            accountDAO.addAccountsDb(con, 0);
            List<Account> listAccounts = accountDAO.findAllAccounts(con);
            LOG.trace("Add new account on DB: --> ");
            int accountId = listAccounts.stream().map(Entity::getId).max(Integer::compare).get();
            LOG.trace("Last generated Id table Accounts DB: --> " + accountId);
            userDAO.addToUsersDb(con, login, password, firstName, lastName, email, phone, 2, accountId);
            LOG.trace("add user : --> ");
            LOG.debug("SignUpCommand finished");

            con.commit();

            return Path.PAGE_WELCOME_REGISTERED_USER;

        } catch (Exception ex) {
            DBManager.rollback(con);
        } finally {
            DBManager.close(con);
        }//END TRANSACTION

        return Path.PAGE_ERROR_PAGE;
    }
}
