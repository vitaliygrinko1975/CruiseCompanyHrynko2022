package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.interfaces.AccountDAO;
import ua.nure.hrynko.dao.interfaces.UserDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminPageRemoveUserCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminPageRemoveUserCommand.class);

    private final transient UserDAO userDAO;

    private final transient AccountDAO accountDAO;

    public AdminPageRemoveUserCommand(UserDAO userDAO, AccountDAO accountDAO) {
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("AdminPageRemoveUserCommand starts");
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("removeButt"));

        LOG.trace("Request parameter: id --> " + id);


        User userWhichWillBeDeleted = userDAO.findUserById(id);

        userDAO.removeUserFromDb(id);

        LOG.trace("Remove user to id: --> " + id);

        accountDAO.removeAccountFromDb(userWhichWillBeDeleted.getAccountsId());

        LOG.trace("Remove account to id: --> " + id);

        String messageAboutDelete = "Успешно.";
        LOG.debug("AdminPageRemoveUserCommand finished");
        session.setAttribute("messageAboutDelete", messageAboutDelete);
        LOG.trace("Set the session attribute: messageAboutDelete --> " + messageAboutDelete);

        return Path.PAGE_WELCOME_REGISTERED_USER;
    }

}

