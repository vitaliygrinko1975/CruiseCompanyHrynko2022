package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.dao.interfaces.CruiseHasShipDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.CruiseHasShip;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AdminRentingPageAddShipToCruiseHasShipCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminRentingPageAddShipToCruiseHasShipCommand.class);

    private final transient CruiseHasShipDAO cruiseHasShipDAO;

    public AdminRentingPageAddShipToCruiseHasShipCommand(CruiseHasShipDAO cruiseHasShipDAO) {
        this.cruiseHasShipDAO = cruiseHasShipDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException, SQLException {
        LOG.debug("AdminRentingPageAddShipToCruiseHasShipCommand starts");
        HttpSession session = request.getSession();

        int shipId = parseInt(request.getParameter("shipIdForRent"));
        LOG.trace("Request parameter: shipIdForRent --> " + shipId);

        int cruiseId = parseInt(request.getParameter("cruiseIdForRent"));
        LOG.trace("Request parameter: cruiseIdForRent --> " + cruiseId);

        String startOfContract = request.getParameter("startOfContract");
        LOG.trace("Request parameter: startOfContract --> " + startOfContract);
        String endOfContract = request.getParameter("endOfContract");
        LOG.trace("Request parameter: endOfContract --> " + endOfContract);
        String status = "Действующий";
        LOG.trace("Status --> " + status);
        cruiseHasShipDAO.addItemToCruiseHasShipDb(cruiseId, shipId, startOfContract, endOfContract, status);

        List<CruiseHasShip> allCruisesHasShipList = cruiseHasShipDAO.findAllItemsOfCruiseHasShip();
        LOG.trace("Found in DB: allCruisesHasShipList --> " + allCruisesHasShipList);

        // put cruises items list to the request
        session.setAttribute("allCruisesHasShipList", allCruisesHasShipList);

        LOG.trace("Set session attribute: allCruisesHasShipList --> " + allCruisesHasShipList);

        LOG.trace("add cruise: --> ");

        LOG.debug("AdminRentingPageAddShipToCruiseHasShipCommand finished");

        return "admin_page_contracts.jsp";
    }
}

