package ua.nure.hrynko.command.admin;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.interfaces.ShipDAO;
import ua.nure.hrynko.exception.AppException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPageShipsFindFreeShipFromRangeCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminPageShipsFindFreeShipFromRangeCommand.class);

    private final transient ShipDAO shipDAO;

    public AdminPageShipsFindFreeShipFromRangeCommand(ShipDAO shipDAO) {
        this.shipDAO = shipDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        LOG.debug("AdminPageShipsFindFreeShipFromRangeCommand starts");

//        HttpSession session = request.getSession();

        String startDate = request.getParameter("startDate");
        LOG.trace("Request parameter: login --> " + startDate);

        String endDate = request.getParameter("endDate");
        LOG.trace("Request parameter: password --> " + endDate);


        // get cruises items list
//        List<Cruise> allCruises = shipDAO.findAllCruises();
//        LOG.trace("Found in DB: allCruises --> " + allCruises);
//
//
//        // put cruises items list to the request
//        request.setAttribute("allCruises", allCruises);
//        LOG.trace("Set the request attribute: allCruises --> " + allCruises);

        LOG.debug("AdminPageCruisesCommand finished");

        return Path.PAGE_ADMIN_CRUISES;

    }
}