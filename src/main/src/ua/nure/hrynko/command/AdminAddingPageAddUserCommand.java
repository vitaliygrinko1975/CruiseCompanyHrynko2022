package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.interfaces.AccountDAO;
import ua.nure.hrynko.dao.interfaces.UserDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.Account;
import ua.nure.hrynko.models.Entity;
import ua.nure.hrynko.models.User;
import ua.nure.hrynko.services.SignUpValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class AdminAddingPageAddUserCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminAddingPageAddUserCommand.class);

    private final transient UserDAO userDAO;

    private final transient AccountDAO accountDAO;

    public AdminAddingPageAddUserCommand(UserDAO userDAO, AccountDAO accountDAO) {
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        LOG.debug("AdminAddingPageAddUserCommand starts");

        String login = request.getParameter("addLoginUser");
        LOG.trace("Request parameter: login --> " + login);

        String password = request.getParameter("addPasswordUser");
        LOG.trace("Request parameter: password --> " + password);

        String firstName = request.getParameter("addFirstNameUser");
        LOG.trace("Request parameter: firstName --> " + firstName);

        String lastName = request.getParameter("addLastNameUser");
        LOG.trace("Request parameter: lastName --> " + lastName);

        String email = request.getParameter("addEmailUser");
        LOG.trace("Request parameter: email --> " + email);

        String phone = request.getParameter("addPhoneUser");
        LOG.trace("Request parameter: phone --> " + phone);

        HttpSession session = request.getSession();
        //START TRANSACTION
        Connection con = DBManager.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            List<User> userList = userDAO.findAllUsers(con);
            SignUpValidator validator = new SignUpValidator();
            if (validator.checkForUniquenessLogin(userList, login)) {
                String message = "Этот логин уже существует. Введите другое.";
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
            int accountsId = listAccounts.stream().map(Entity::getId).max(Integer::compare).get();
            userDAO.addToUsersDb(con, login, password, firstName, lastName, email, phone, 2, accountsId);
            LOG.trace("add user to SQL succesful--> ");
            con.commit();
            LOG.debug("AdminAddingPageAddUserCommand finished");

            LOG.trace("add user by ID: --> ");
            return Path.PAGE_WELCOME_REGISTERED_USER;
        }
        //END TRANSACTION
        catch (Exception ex) {
            DBManager.rollback(con);
            LOG.error("cannot add user", ex);
            throw new DBException("cannot add user", ex);
        } finally {
            DBManager.close(con);
        }
    }
}

