package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.services.AllMethodsWithTransactions;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignUpCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(SignUpCommand.class);

    AllMethodsWithTransactions allMethodsWithTransactions;

    public SignUpCommand(AllMethodsWithTransactions allMethodsWithTransactions) {
        this.allMethodsWithTransactions = allMethodsWithTransactions;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException, SQLException {
        LOG.debug("SignUpCommand starts");
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

        return allMethodsWithTransactions.signUpUserAndAddNewItemToAccountDb(request,login, password,
                firstName,lastName,email,phone);
    }
}

