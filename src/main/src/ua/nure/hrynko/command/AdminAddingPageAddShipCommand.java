package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.interfaces.ShipDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.Ship;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

public class AdminAddingPageAddShipCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminAddingPageAddShipCommand.class);

    private  final transient ShipDAO shipDAO;

    public AdminAddingPageAddShipCommand(ShipDAO shipDAO) {
        this.shipDAO = shipDAO;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("AdminAddingPageAddShipCommand starts");
        HttpSession session = request.getSession();
        String name = request.getParameter("addNameShip");
        LOG.trace("Request parameter: name --> " + name);
        String description =  request.getParameter("addDescriptionShip");
        LOG.trace("Request parameter: description --> " + description);
        int capacity = parseInt(request.getParameter("addCapacityShip"));
        LOG.trace("Request parameter: capacity --> " + capacity);
        shipDAO.addToShipDb(name,description,capacity);
        LOG.trace("add ship: --> " );
        List<Ship> allShips = shipDAO.findAllShips();
        LOG.trace("Found in DB: allCruises --> " + allShips);

        // put ships items list to the request
        session.setAttribute("allShips", allShips);
        LOG.trace("Set session attribute: allCruises --> " +allShips);

        LOG.debug("AdminAddingPageAddShipCommand finished");

        return Path.PAGE_ADMIN_SHIPS;

    }

}

