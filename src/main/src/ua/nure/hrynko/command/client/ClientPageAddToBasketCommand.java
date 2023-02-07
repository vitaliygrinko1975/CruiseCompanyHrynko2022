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
import java.util.*;

/**
 * Lists cruises items.
 */
public class ClientPageAddToBasketCommand extends Command {
    private static final long serialVersionUID = 7732286214029478505L;
    private static final Logger LOG = Logger.getLogger(ClientPageAddToBasketCommand.class);
    private final transient MySqlCruiseDAO cruiseDAO;
    public ClientPageAddToBasketCommand(MySqlCruiseDAO cruiseDAO) {
        this.cruiseDAO = cruiseDAO;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("ClientPageAddToBasketCommand starts");
        HttpSession session = request.getSession();

        int userId = Integer.parseInt(request.getParameter("userIdForBasketUsersHasCruisesButt"));
        LOG.trace("Request parameter: userId --> " + userId);
        int cruiseId = Integer.parseInt(request.getParameter("cruiseIdForBasketUsersHasCruisesButt"));
        LOG.trace("Request parameter: cruiseId --> " + cruiseId);

        HashMap<Cruise, Integer> mapForBasket = (HashMap<Cruise, Integer>) session.getAttribute("mapForBasket");

        Cruise currentСruise = cruiseDAO.findCruiseById(cruiseId);

        if (mapForBasket.size() < 1 || !mapForBasket.containsKey(currentСruise)) {
            mapForBasket.put(currentСruise, 1);
            session.setAttribute("mapForBasket", mapForBasket);
            LOG.trace("Set the session attribute: mapForBasket --> " + mapForBasket);
            // get cruises items list
            List<Cruise> allCruises = cruiseDAO.findAllCruises();
            LOG.trace("Found in DB: allCruises --> " + allCruises);
            // put cruises items list to the request
            request.setAttribute("allCruises", allCruises);
            LOG.trace("Set the request attribute: allCruises --> " + allCruises);
            LOG.debug("ClientPageAddToBasketCommand finished");
            return Path.PAGE_CLIENT;
        }
        if (mapForBasket.containsKey(currentСruise)) {
            int tempCount = mapForBasket.get(currentСruise);
            tempCount++;
            mapForBasket.put(currentСruise, tempCount);
            session.setAttribute("mapForBasket", mapForBasket);
            LOG.trace("Set the session attribute: mapForBasket --> " + mapForBasket);
            // get cruises items list
            List<Cruise> allCruises = cruiseDAO.findAllCruises();
            LOG.trace("Found in DB: allCruises --> " + allCruises);
            // put cruises items list to the request
            request.setAttribute("allCruises", allCruises);
            LOG.trace("Set the request attribute: allCruises --> " + allCruises);
            LOG.debug("ClientPageAddToBasketCommand finished");
            return Path.PAGE_CLIENT;
        }
        return Path.PAGE_CLIENT;
    }
}

