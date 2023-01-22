package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.dao.interfaces.ShipDAO;
import ua.nure.hrynko.models.Ship;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminUpdatingPageUpdateShipCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;
    private static final Logger LOG = Logger.getLogger(AdminUpdatingPageUpdateShipCommand.class);
    private  final transient ShipDAO shipDAO;
    public AdminUpdatingPageUpdateShipCommand(ShipDAO shipDAO) {this.shipDAO = shipDAO;}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("AdminUpdatingPageUpdateCruiseCommand starts");
        HttpSession session = request.getSession();

        int id = Integer.parseInt(request.getParameter("shipIdForUpdateButt"));
        LOG.trace("Request parameter: id --> " + id);
        String name = request.getParameter("updateNameShip");
        LOG.trace("Request parameter: name --> " + name);
        String description =  request.getParameter("updateDescriptionShip");
        LOG.trace("Request parameter: description --> " + description);
        int capacity = Integer.parseInt(request.getParameter("updateCapacityShip"));
        LOG.trace("Request parameter: capacity --> " + capacity);

        shipDAO.updateShipDb(id,name,description,capacity);
        LOG.trace("update cruise  by ID: --> " );

        List<Ship> allShips = shipDAO.findAllShips();
        LOG.trace("Found in DB: allCruises --> " + allShips);

        // put cruises items list to the request
        session.setAttribute("allShips", allShips);
        LOG.trace("Set session attribute: allShips --> " + allShips);

        return Path.PAGE_ADMIN_SHIPS;
    }

}

