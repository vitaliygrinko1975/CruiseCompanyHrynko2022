package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.interfaces.AccountDAO;
import ua.nure.hrynko.dto.Account;
import ua.nure.hrynko.exception.AppException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientUpdatingPageUpdateAccountCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(ClientUpdatingPageUpdateAccountCommand.class);

    private final transient AccountDAO accountDAO;

    public ClientUpdatingPageUpdateAccountCommand(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        LOG.debug("ClientUpdatingPageUpdateAccountCommand starts");

        int id = Integer.parseInt(request.getParameter("accountForUpdateButt"));

        LOG.trace("Request parameter: accountForUpdateButt--> " + id);

        double increaseTheBalanceBy = Double.parseDouble(request.getParameter("increaseTheBalanceBy"));

        LOG.trace("Request parameter: balance --> " + increaseTheBalanceBy);

        Account account = accountDAO.findAccountById(id);

        LOG.trace("find account by ID: --> ");

        double currentBalance = account.getBalance();

        LOG.trace("current balance: --> " + currentBalance);

        account.setBalance(currentBalance + increaseTheBalanceBy);

        LOG.trace("account for update: --> " + account);
        
        accountDAO.updateAccountToDb(account);

        LOG.trace("update account by ID: --> " + account.getId());


        String message = "Транзакции прошли успешно" ;

        request.getSession().setAttribute("messageAboutPay", message);



        LOG.debug("ClientUpdatingPageUpdateAccountCommand finished");


        return Path.PAGE_ERROR_PAGE;

    }

}

