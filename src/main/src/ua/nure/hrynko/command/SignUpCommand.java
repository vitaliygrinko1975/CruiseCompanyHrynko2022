package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.services.AllMethodsWithTransactions;
import ua.nure.hrynko.services.EncodePassword;
import ua.nure.hrynko.services.SignUpValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

        LOG.debug("SignUpCommand finished");
        return allMethodsWithTransactions.signUpUserAndAddNewItemToAccountDb(request);
    }
}

