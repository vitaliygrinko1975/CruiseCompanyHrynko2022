package ua.nure.hrynko.command.client;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.MySqlCruiseDAO;
import ua.nure.hrynko.models.Cruise;
import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Lists cruises items.
 */
public class ClientBasketAddOneUnitCommand extends Command {
    private static final long serialVersionUID = 7732286214029478505L;
    private static final Logger LOG = Logger.getLogger(ClientBasketAddOneUnitCommand.class);
    private final transient MySqlCruiseDAO cruiseDAO;


    public ClientBasketAddOneUnitCommand(MySqlCruiseDAO cruiseDAO) {
        this.cruiseDAO = cruiseDAO;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("ClientBasketRemoveOneUnitCommand starts");
        HttpSession session = request.getSession();

        int cruiseId = Integer.parseInt(request.getParameter("cruiseIdForBasketUsersHasCruisesButt"));
        LOG.trace("Request parameter: cruiseId --> " + cruiseId);

        HashMap<Integer, Integer> mapForBasket = (HashMap<Integer, Integer>) session.getAttribute("mapForBasket");

        int tempCount = mapForBasket.get(cruiseId);

        mapForBasket.put(cruiseId, tempCount + 1);
        session.setAttribute("mapForBasket", mapForBasket);
        LOG.trace("Set the session attribute: mapForBasket --> " + mapForBasket);
        List<Cruise> allCruises = cruiseDAO.findAllCruises();
        LOG.trace("Found in DB: allCruises --> " + allCruises);
        // put cruises items list to the session
        session.setAttribute("allCruises", allCruises);
        LOG.trace("Set the request attribute: allCruises --> " + allCruises);

        LOG.debug("ClientBasketRemoveOneUnitCommand finished");

        return Path.BASKET;
    }
}

