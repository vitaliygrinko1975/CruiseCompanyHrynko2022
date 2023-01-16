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

public class AdminUpdatingPageUpdateCruiseCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminUpdatingPageUpdateCruiseCommand.class);

    private  final transient CruiseDAO cruiseDAO;

    private  final transient Cruise cruise;

    public AdminUpdatingPageUpdateCruiseCommand(CruiseDAO cruiseDAO, Cruise cruise) {
        this.cruiseDAO = cruiseDAO;
        this.cruise = cruise;
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
        int shipId = Integer.parseInt(request.getParameter("updateShipIdCruise"));
        LOG.trace("Request parameter: shipId --> " + shipId);
        int capacity = Integer.parseInt(request.getParameter("updateCapacityCruise"));
        LOG.trace("Request parameter: capacity --> " + capacity);
        String startOfCruise = request.getParameter("updateStartOfCruise");
        LOG.trace("Request parameter: startOfCruise --> " + startOfCruise);
        int duration = Integer.parseInt(request.getParameter("updateDurationCruise"));
        LOG.trace("Request parameter: duration --> " + duration);

        cruiseDAO.updateCruiseDb(id,name,description,price,shipId,capacity,startOfCruise,duration);

        LOG.trace("update cruise  by ID: --> " );
        String message = "Обновление прошло успешно" ;

        request.getSession().setAttribute("messageAboutUpdate", message);
        LOG.debug("AdminUpdatingPageUpdateCruiseCommand finished");

        return Path.PAGE_WELCOME_REGISTERED_USER;
    }

}

