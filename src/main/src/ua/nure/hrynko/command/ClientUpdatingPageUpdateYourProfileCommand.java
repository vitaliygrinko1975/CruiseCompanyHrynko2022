package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.interfaces.CruiseDAO;
import ua.nure.hrynko.dao.interfaces.UserDAO;
import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ClientUpdatingPageUpdateYourProfileCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(ClientUpdatingPageUpdateYourProfileCommand.class);

    private  final transient UserDAO  userDAO;

    public ClientUpdatingPageUpdateYourProfileCommand(UserDAO  userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("ClientUpdatingPageUpdateYourProfileCommand starts");

        int id = Integer.parseInt(request.getParameter("userIdForUpdateButt"));
        LOG.trace("Request parameter: id --> " + id);
        String firstName = request.getParameter("updateFirstNameUser");
        LOG.trace("Request parameter: firstName --> " + firstName);

        String lastName =  request.getParameter("updateLastNameUser");
        LOG.trace("Request parameter: lastName --> " + lastName);

        String email = request.getParameter("updateEmailUser");
        LOG.trace("Request parameter: email --> " + email);

        String phone = request.getParameter("updatePhoneUser");
        LOG.trace("Request parameter: phone --> " + phone);

        userDAO.updateUserToDb(id,firstName,lastName,email,phone);

        String messageAboutUpdate = "Успешно.Данные обновлены";

        request.setAttribute("messageAboutUpdate", messageAboutUpdate);

        LOG.trace("add cruise: --> " );

        LOG.debug("ClientUpdatingPageUpdateYourProfileCommand finished");

        return Path.PAGE_WELCOME_REGISTERED_USER;

    }

}

