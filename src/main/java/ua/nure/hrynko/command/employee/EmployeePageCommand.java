package ua.nure.hrynko.command.employee;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.interfaces.CruiseHasShipDAO;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.CruiseHasShip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

public class EmployeePageCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(EmployeePageCommand.class);

    private final transient CruiseHasShipDAO cruiseHasShipDAO;

    public EmployeePageCommand(CruiseHasShipDAO cruiseHasShipDAO) {
        this.cruiseHasShipDAO = cruiseHasShipDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, DBException {
        LOG.debug("EmployeePageCommand starts");

        List<CruiseHasShip> allCruisesHasShipList = cruiseHasShipDAO.findAllItemsOfCruiseHasShip();
        LOG.trace("Found in DB: allCruisesHasShipList --> " + allCruisesHasShipList);

        // put cruises items list to the request
        request.setAttribute("allCruisesHasShipList", allCruisesHasShipList);

        LOG.trace("Set session attribute: allCruisesHasShipList --> " + allCruisesHasShipList);

        LOG.trace("add cruise: --> ");
        LOG.debug("EmployeePageCommand finished");

        return Path.PAGE_EMPLOYEE;
    }
}

