package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.interfaces.AccountDAO;
import ua.nure.hrynko.dao.interfaces.UserDAO;
import ua.nure.hrynko.models.Account;
import ua.nure.hrynko.models.Entity;
import ua.nure.hrynko.models.User;
import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class SignUpCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(SignUpCommand.class);

    private  final transient UserDAO userDAO;

    private  final transient AccountDAO accountDAO;

    public SignUpCommand(UserDAO userDAO, AccountDAO accountDAO) {
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("SignUpCommand starts");
        HttpSession session = request.getSession();
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

        // TRANSACTION
        Connection con = DBManager.getInstance().getConnection();

        try {
            con.setAutoCommit(false);
            List<User> userList = userDAO.findAllUsers(con);
            LOG.trace("find all users to userList : --> " + userList);


            boolean matchToEmail = userList.stream().anyMatch(number -> number.getEmail().equalsIgnoreCase(email));
            boolean matchToLogins = userList.stream().anyMatch(number -> number.getLogin().equalsIgnoreCase(login));
            if (matchToLogins) {
                String message = "Этот логин уже существует. Введите другой.";
                session.setAttribute("message", message);
                return Path.PAGE_ERROR_PAGE;
            }
            if (matchToEmail) {
                String message = "Пользователь с таким адресом электронной почты уже существует. Введите другой адрес.";
                session.setAttribute("message", message);

                return Path.PAGE_ERROR_PAGE;
            }
            accountDAO.addAccountsDb(con,0);
            List<Account> listAccounts = accountDAO.findAllAccounts(con);
            LOG.trace("Add new account on DB: --> ");
            int accountId = listAccounts.stream().map(Entity::getId).max(Integer::compare).get();
            LOG.trace("Last generated Id table Accounts DB: --> " + accountId);
            userDAO.addToUsersDb(con,login, password, firstName, lastName, email, phone, 2, accountId);
            LOG.trace("add user : --> ");
            LOG.debug("SignUpCommand finished");

            con.commit();

            return "login.jsp";

        } catch (Exception ex) {
            DBManager.rollback(con);
        } finally {
            DBManager.close(con);
        }
        return Path.PAGE_ERROR_PAGE;
    }
}

