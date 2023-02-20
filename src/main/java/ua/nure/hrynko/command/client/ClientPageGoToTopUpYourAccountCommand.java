package ua.nure.hrynko.command.client;
import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.interfaces.AccountDAO;
import ua.nure.hrynko.dao.interfaces.UserDAO;
import ua.nure.hrynko.models.Account;
import ua.nure.hrynko.models.User;
import ua.nure.hrynko.exception.AppException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ClientPageGoToTopUpYourAccountCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(ClientPageGoToTopUpYourAccountCommand.class);

    private final transient UserDAO userDAO;

    private final transient AccountDAO accountDAO;

    public ClientPageGoToTopUpYourAccountCommand(UserDAO userDAO, AccountDAO accountDAO) {
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("ClientPageGoToTopUpYourAccountCommand starts");

        String id = request.getParameter("userIdForTopUpYourAccount");

        LOG.trace("Request parameter: id --> " + id);

        User user = userDAO.findUserById(Integer.parseInt(id));

        int accountsId = user.getAccountsId();

        Account account = accountDAO.findAccountById(accountsId);

        LOG.trace("Account for top up : --> " + account);

        request.setAttribute("account", account);

        LOG.trace("Account set to Attribute: --> " + account);

        LOG.debug("ClientPageGoToTopUpYourAccountCommand finished");

        return Path.PAGE_CLIENT_TOP_UP_YOUR_ACCOUNT;


    }

}