package ua.nure.hrynko.command.admin;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.MySqlCruiseDAO;
import ua.nure.hrynko.dao.MySqlCruiseHasShipDAO;
import ua.nure.hrynko.models.Cruise;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.CruiseHasShip;
import ua.nure.hrynko.services.Paginations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Lists cruises items.
 */
public class AdminPageCruisesCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(AdminPageCruisesCommand.class);

    private final transient MySqlCruiseDAO cruiseDAO;

    private final transient MySqlCruiseHasShipDAO cruiseHasShipDAO;

    public AdminPageCruisesCommand(MySqlCruiseDAO cruiseDAO, MySqlCruiseHasShipDAO cruiseHasShipDAO) {
        this.cruiseDAO = cruiseDAO;
        this.cruiseHasShipDAO = cruiseHasShipDAO;
    }


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("AdminPageCruisesCommand starts");
        Paginations paginations = new Paginations();
        int countAllCruises = cruiseDAO.countingTheNumberOfRecordsToCruisesDb();

        List<CruiseHasShip> allItemOfCruisesHasShips = cruiseHasShipDAO.findAllItemsOfCruiseHasShip();
        int numberPage = Integer.parseInt(request.getParameter("page"));
        LOG.trace("Request parameter: numberPage --> " + numberPage);
        //get list with limit
        List<Cruise> allItemOfCruisesWithLimit = paginations.makePaginationForCruises(numberPage);
        Map<Integer, Boolean> mapWhereKeyIdCruise = new HashMap<>();
        for (CruiseHasShip itemContract : allItemOfCruisesHasShips) {
            if (itemContract.getStatus().equalsIgnoreCase("Действующий")) {
                mapWhereKeyIdCruise.put(itemContract.getCruiseId(), true);
            }
        }

        // put cruiseHasShips items list to the request
        request.setAttribute("mapWhereKeyIdCruise", mapWhereKeyIdCruise);
        LOG.trace("Set the request attribute:mapWhereKeyIdCruise --> " + mapWhereKeyIdCruise);

        // put cruises items list to the request
        request.setAttribute("allItemOfCruisesWithLimit", allItemOfCruisesWithLimit);
        LOG.trace("Set the request attribute:allItemOfCruisesWithLimit --> " + allItemOfCruisesWithLimit);

        // put count of cruises  to the request
        request.setAttribute("countAllCruises", countAllCruises);
        LOG.trace("Set the request attribute: countAllCruises --> " + countAllCruises);

        LOG.debug("AdminPageCruisesCommand finished");

        return Path.PAGE_ADMIN_CRUISES;
    }
}