package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.exception.AppException;

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



	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) throws IOException, ServletException, AppException {

		LOG.debug("ClientPageGoToMyProfileCommand starts");

		LOG.debug("ClientPageGoToMyProfileCommand finished");

		return Path.PAGE_CLIENT_MY_PROFILE;
	}

}