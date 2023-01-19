package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.interfaces.ShipsDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.Ship;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Lists ships items.
 */
public class AdminPageShipsCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(AdminPageShipsCommand.class);

	private final transient ShipsDAO shipsDAO;

	public AdminPageShipsCommand(ShipsDAO shipsDAO) {
		this.shipsDAO = shipsDAO;
	}

	@Override
	public String execute(HttpServletRequest request,
						  HttpServletResponse response) throws IOException, ServletException, AppException {

		LOG.debug("AdminPageShipsCommand starts");

		// get all ships items list
		List<Ship> allShips = shipsDAO.findAllShips();
		LOG.trace("Found in DB: allShips --> " + allShips);


		// put all ships items list to the request
		request.setAttribute("allShips", allShips);
		LOG.trace("Set the request attribute: allShips --> " + allShips);

		LOG.debug("AdminPageShipsCommand finished");

		return Path.PAGE_ADMIN_SHIPS;
	}

}