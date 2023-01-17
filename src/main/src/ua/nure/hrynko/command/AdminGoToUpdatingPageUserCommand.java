package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.interfaces.UserDAO;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminGoToUpdatingPageUserCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminGoToUpdatingPageUserCommand.class);

    private  final transient UserDAO userDAO;

    public AdminGoToUpdatingPageUserCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        LOG.debug("AdminGoToUpdatingPageCommand starts");

        String id = request.getParameter("userIdForUpdateButt");

        LOG.trace("Request parameter: id --> " + id);

        User user = userDAO.findUserById(Integer.parseInt(id));

        LOG.trace("Update user: --> " + user);

        request.setAttribute("user", user);

        LOG.trace("User set to Attribute: --> " + user);

        LOG.debug("AdminGoToUpdatingPageCommand finished");

        return Path.ADMIN_PAGE_UPDATE_USER;
    }

}

