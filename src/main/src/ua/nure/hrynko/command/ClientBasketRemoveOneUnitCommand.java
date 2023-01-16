package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.MySqlCruiseDAO;
import ua.nure.hrynko.models.Cruise;
import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * Lists cruises items.
 */
public class ClientBasketRemoveOneUnitCommand extends Command {
    private static final long serialVersionUID = 7732286214029478505L;
    private static final Logger LOG = Logger.getLogger(ClientBasketRemoveOneUnitCommand.class);
    private final transient MySqlCruiseDAO cruiseDAO;


    public ClientBasketRemoveOneUnitCommand(MySqlCruiseDAO cruiseDAO) {
        this.cruiseDAO = cruiseDAO;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("ClientBasketRemoveOneUnitCommand starts");
        HttpSession session = request.getSession();

        int cruiseId = Integer.parseInt(request.getParameter("cruiseIdForBasketUsersHasCruisesButt"));
        LOG.trace("Request parameter: cruiseId --> " + cruiseId);

        HashMap<Cruise, Integer> mapForBasket = (HashMap<Cruise, Integer>) session.getAttribute("mapForBasket");

        Cruise currentСruise = cruiseDAO.findCruiseById(cruiseId);

        int tempCount = mapForBasket.get(currentСruise);

        mapForBasket.put(currentСruise, tempCount - 1);
        session.setAttribute("mapForBasket", mapForBasket);
        LOG.trace("Set the session attribute: mapForBasket --> " + mapForBasket);
        LOG.debug("ClientBasketRemoveOneUnitCommand finished");
        return Path.BASKET;
    }
}

