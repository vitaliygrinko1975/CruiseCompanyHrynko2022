package ua.nure.hrynko.command.admin;
import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.interfaces.CruiseDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.Cruise;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminUpdatingPageUpdateCruiseCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminUpdatingPageUpdateCruiseCommand.class);

    private  final transient CruiseDAO cruiseDAO;

    public AdminUpdatingPageUpdateCruiseCommand(CruiseDAO cruiseDAO) {
        this.cruiseDAO = cruiseDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("AdminUpdatingPageUpdateCruiseCommand starts");
        HttpSession session = request.getSession();

        int id = Integer.parseInt(request.getParameter("cruiseIdForUpdateButt"));
        LOG.trace("Request parameter: id --> " + id);
        String name = request.getParameter("updateNameCruise");
        LOG.trace("Request parameter: name --> " + name);
        String description =  request.getParameter("updateDescriptionCruise");
        LOG.trace("Request parameter: description --> " + description);
        double price = Double.parseDouble(request.getParameter("updatePriceCruise"));
        LOG.trace("Request parameter: price --> " + price);
        int capacity = Integer.parseInt(request.getParameter("updateCapacityCruise"));
        LOG.trace("Request parameter: capacity --> " + capacity);
        String startOfCruise = request.getParameter("updateStartOfCruise");
        LOG.trace("Request parameter: startOfCruise --> " + startOfCruise);
        int duration = Integer.parseInt(request.getParameter("updateDurationCruise"));
        LOG.trace("Request parameter: duration --> " + duration);
        String status = request.getParameter("updateStatusCruise");
        LOG.trace("Request parameter: status --> " + status);

        cruiseDAO.updateCruiseDb(id,name,description,price,capacity,startOfCruise,duration,status);
        LOG.trace("update cruise  by ID: --> " );

        List<Cruise> allCruises = cruiseDAO.findAllCruises();
        LOG.trace("Found in DB: allCruises --> " + allCruises);

        // put cruises items list to the request
        session.setAttribute("allCruises", allCruises);
        LOG.trace("Set session attribute: allCruises --> " + allCruises);

        return Path.PAGE_ADMIN_CRUISES;
    }

}

