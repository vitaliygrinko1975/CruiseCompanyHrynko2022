package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;

import ua.nure.hrynko.dao.interfaces.UserDAO;
import ua.nure.hrynko.models.User;
import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class AdminPageCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(AdminPageCommand.class);

    private  final transient UserDAO userDAO;

    public AdminPageCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        List<User> userList = userDAO.findAllUsers();
        LOG.trace("Found in DB: userList --> " + userList);
        // put user order beans list to request
        request.setAttribute("userList", userList);
        LOG.trace("Set the request attribute: userList --> " + userList);

        LOG.debug("Commands finished");

        return Path.PAGE_ADMIN;

    }

}