package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
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
public class ClientPageCruiseFindByStartDateCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(ClientPageCruiseFindByStartDateCommand.class);

	private final transient MySqlCruiseDAO cruiseDAO;

	public ClientPageCruiseFindByStartDateCommand(MySqlCruiseDAO cruiseDAO) {
		this.cruiseDAO = cruiseDAO;
	}

	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) throws IOException, ServletException, AppException {

		LOG.debug("ClientPageCruiseFindByStartDateCommand starts");
		String startDate = request.getParameter("startDate");
		LOG.trace("Request parameter: startDate --> " + startDate);


		// get cruises items list
		List<Cruise> allCruises = cruiseDAO.findCruiseByStartOfCruise(startDate);
		LOG.trace("Found in DB: allCruises --> " + allCruises);


		// put cruises items list to the request
		request.setAttribute("allCruises", allCruises);
		LOG.trace("Set the request attribute: allCruises --> " + allCruises);

		LOG.debug("ClientPageCruiseFindByStartDateCommand finished");

		return Path.PAGE_CLIENT;
	}

}