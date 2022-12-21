package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.RoleEnum;
import ua.nure.hrynko.dao.interfaces.UserDAO;
import ua.nure.hrynko.dto.Cruise;
import ua.nure.hrynko.dto.User;
import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Login command.
 */
public class LoginCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    private final transient UserDAO userDAO;

    public LoginCommand(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("LoginCommand starts");

        HttpSession session = request.getSession();

        // obtain login and password from a request

        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);

        String password = request.getParameter("password");


        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new AppException("Login/password cannot be empty");
        }

        User user = userDAO.findUserByLogin(login);
        LOG.trace("Found in DB: user --> " + user);


        if (user == null || !password.equals(user.getPassword())) {
            throw new AppException("Cannot find user with such login/password");
        }

        RoleEnum userRole = RoleEnum.getRole(user);
        LOG.trace("userRole --> " + userRole);

        String forward = Path.PAGE_ERROR_PAGE;


        if (userRole == RoleEnum.ADMIN || userRole == RoleEnum.CLIENT) {
            forward = Path.PAGE_WELCOME_REGISTERED_USER;
        }
        HashMap<Cruise, Integer> mapForBasket = new HashMap<>();

        session.setAttribute("user", user);
        LOG.trace("Set the session attribute: user --> " + user);
        session.setAttribute("userRole", userRole);
        LOG.trace("Set the session attribute: userRole --> " + userRole);
        session.setAttribute("mapForBasket", mapForBasket);
        LOG.trace("Set the session attribute: mapForBasket --> " + mapForBasket);
        LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        LOG.debug("LoginCommand finished");

        return forward;
    }

}