package ua.nure.hrynko.command.client;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.MySqlCruiseDAO;
import ua.nure.hrynko.models.Cruise;
import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Lists cruises items.
 */
public class ClientPageCruiseFindByDurationCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(ClientPageCruiseFindByDurationCommand.class);

	private final transient MySqlCruiseDAO cruiseDAO;

	public ClientPageCruiseFindByDurationCommand(MySqlCruiseDAO cruiseDAO) {
		this.cruiseDAO = cruiseDAO;
	}

	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) throws IOException, ServletException, AppException {

		LOG.debug("ClientPageCruiseFindByDurationCommand starts");
		int duration =  Integer.parseInt(request.getParameter("duration"));
		LOG.trace("Request parameter: duration --> " + duration);

		// get cruises items list
		List<Cruise> allCruises = cruiseDAO.findCruiseByDuration(duration);
		LOG.trace("Found in DB: allCruises --> " + allCruises);

		// put cruises items list to the request
		request.setAttribute("allCruises", allCruises);
		LOG.trace("Set the request attribute: allCruises --> " + allCruises);

		LOG.debug("ClientPageCruiseFindByDurationCommand finished");

		return Path.PAGE_CLIENT;
	}

}