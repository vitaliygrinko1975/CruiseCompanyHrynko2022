package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginPageCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(LoginPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        LOG.debug("LoginPageCommand starts");
        HttpSession session = request.getSession();
        String local = request.getParameter("local");
        session.setAttribute("local", local);
        LOG.trace("Set the session attribute: local --> " + local);
        LOG.debug("LoginPageCommand finished");

        return Path.PAGE_LOGIN;
    }

}

