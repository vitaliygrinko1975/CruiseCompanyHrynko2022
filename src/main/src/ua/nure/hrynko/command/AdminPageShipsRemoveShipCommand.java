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

public class AdminPageShipsRemoveShipCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminPageShipsRemoveShipCommand.class);

    private  final transient ShipDAO shipsDAO;

    public AdminPageShipsRemoveShipCommand(ShipDAO shipsDAO) {
        this.shipsDAO = shipsDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        LOG.debug("AdminPageShipsRemoveShipCommand starts");
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("shipIdForRemoveShipButt"));
        LOG.trace("Request parameter: id --> " + id);
        shipsDAO.removeShipFromDb(id);
        LOG.trace("Remove ship by id: --> " + id);
        List<Ship> allShips = shipsDAO.findAllShips();
        LOG.trace("Found in DB: allShips --> " + allShips);
        // put cruises items list to the request
        session.setAttribute("allShips", allShips);
        LOG.debug("AdminPageShipsRemoveShipCommand finished");
        return Path.PAGE_ADMIN_SHIPS;
    }
}

