package ua.nure.hrynko.command.admin;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.interfaces.ShipDAO;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.Ship;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminGoToPageForUpdatingShipCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminGoToPageForUpdatingShipCommand.class);


    private final transient ShipDAO shipDAO;

    public AdminGoToPageForUpdatingShipCommand(ShipDAO shipDAO) {
        this.shipDAO = shipDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        LOG.debug("AdminGoToPageForUpdatingShipCommand starts");
        int id = Integer.parseInt(request.getParameter("shipIdForUpdateShipButt"));
        LOG.trace("Request parameter: id --> " + id);
        Ship shipForUpdate = shipDAO.findShipById(id);
        LOG.trace("Find ship with DB  from update : --> "+ shipForUpdate);
        request.setAttribute("shipForUpdate", shipForUpdate);
        LOG.trace("Ship set to Attribute: --> " + shipForUpdate);
        LOG.debug("AdminGoToPageForUpdatingShipCommand finished");

        return Path.ADMIN_PAGE_UPDATE_SHIP;
    }

}

