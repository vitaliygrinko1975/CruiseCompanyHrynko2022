package ua.nure.hrynko.command.client;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.interfaces.AccountDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Lists cruises items.
 */
public class ClientPageGoToMyProfileCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(ClientPageGoToMyProfileCommand.class);

	private final transient AccountDAO accountDAO;

	public ClientPageGoToMyProfileCommand(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) throws IOException, ServletException, AppException {

		LOG.debug("ClientPageGoToMyProfileCommand starts");
		int accountsId = Integer.parseInt(request.getParameter("accountsId"));
		LOG.trace("Request parameter: accountsId --> " +accountsId);
		Account account = accountDAO.findAccountById(accountsId);
		LOG.trace("Find by id from accountDb  : account --> " + account);
		String fileName = account.getFileName();
		request.setAttribute("fileName", fileName);
		LOG.trace("Set the request attribute: fileName --> " + fileName);
		LOG.debug("ClientPageGoToMyProfileCommand finished");

		return Path.PAGE_CLIENT_MY_PROFILE;
	}

}