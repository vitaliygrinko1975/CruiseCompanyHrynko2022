package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.interfaces.UserDAO;
import ua.nure.hrynko.exception.AppException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AdminUpdatingPageUpdateUserCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminUpdatingPageUpdateUserCommand.class);

    private  final transient UserDAO userDAO;

    public AdminUpdatingPageUpdateUserCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("AdminUpdatingPageUpdateUserCommand starts");

        int id = Integer.parseInt(request.getParameter("userForUpdateButt"));
        LOG.trace("Request parameter: id --> " + id);

        String login = request.getParameter("updateLoginUser");
        LOG.trace("Request parameter: login --> " + login);

        String password =  request.getParameter("updatePasswordUser");
        LOG.trace("Request parameter: password --> " + password);

        String firstName = request.getParameter("updateFirstNameUser");
        LOG.trace("Request parameter: firstName --> " + firstName);

        String lastName = request.getParameter("updateLastNameUser");
        LOG.trace("Request parameter: lastName --> " + lastName);

        String email = request.getParameter("updateEmailUser");
        LOG.trace("Request parameter: email --> " + email);

        String phone = request.getParameter("updatePhoneUser");
        LOG.trace("Request parameter: phone --> " + phone);


        int accountsId = Integer.parseInt(request.getParameter("updateAccountsIdUser"));
        LOG.trace("Request parameter: accountsId --> " + accountsId);

       userDAO.updateUserToDb(id,login,password,firstName,lastName,email,phone,2,accountsId);

        LOG.trace("update user by ID: --> " );

        LOG.debug("AdminUpdatingPageUpdateUserCommand finished");

        return Path.PAGE_WELCOME_REGISTERED_USER;
    }

}

