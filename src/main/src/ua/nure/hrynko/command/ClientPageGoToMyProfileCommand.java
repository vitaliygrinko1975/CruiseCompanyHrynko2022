package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.MySqlCruiseDAO;
import ua.nure.hrynko.dto.Cruise;
import ua.nure.hrynko.dto.User;
import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


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
//		HttpSession session = request.getSession();
//		User user = session.getAttribute("user");



		LOG.debug("ClientPageGoToMyProfileCommand finished");

		return Path.PAGE_CLIENT_MY_PROFILE;
	}

}