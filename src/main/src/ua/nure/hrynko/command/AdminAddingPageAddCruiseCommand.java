package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.interfaces.CruiseDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.Cruise;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AdminAddingPageAddCruiseCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminAddingPageAddCruiseCommand.class);

    private  final transient CruiseDAO  cruiseDAO;

    public AdminAddingPageAddCruiseCommand( CruiseDAO  cruiseDAO) {
        this.cruiseDAO = cruiseDAO;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("AdminAddingPageAddCruiseCommand starts");
        HttpSession session = request.getSession();
        String name = request.getParameter("addNameCruise");
        LOG.trace("Request parameter: name --> " + name);

        String description =  request.getParameter("addDescriptionCruise");
        LOG.trace("Request parameter: description --> " + description);

        double price = parseDouble(request.getParameter("addPriceCruise"));
        LOG.trace("Request parameter: price --> " + price);

        int capacity = parseInt(request.getParameter("addCapacityCruise"));
        LOG.trace("Request parameter: capacity --> " + capacity);

        String startOfCruise = request.getParameter("addStartOfCruise");
        LOG.trace("Request parameter: startOfCruise --> " + startOfCruise);

        int duration = parseInt(request.getParameter("addDurationCruise"));
        LOG.trace("Request parameter: duration --> " + duration);

        String status = "Не начался";

        cruiseDAO.addToCruiseDb(name,description, price,capacity,startOfCruise,duration,status);

        List<Cruise> allCruises = cruiseDAO.findAllCruises();
        LOG.trace("Found in DB: allCruises --> " + allCruises);

        // put cruises items list to the request
        session.setAttribute("allCruises", allCruises);
        LOG.trace("Set session attribute: allCruises --> " + allCruises);

        LOG.trace("add cruise: --> " );

        LOG.debug("AdminAddingPageAddCruiseCommand finished");

        return Path.PAGE_ADMIN_CRUISES;

    }

}

