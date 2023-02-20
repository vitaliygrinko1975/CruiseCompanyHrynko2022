package ua.nure.hrynko.command.client;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.MySqlCruiseDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.Cruise;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Map for basket.
 */
public class ClientPageGoToBasketCommand extends Command {
    private static final long serialVersionUID = 7732286214029478505L;
    private static final Logger LOG = Logger.getLogger(ClientPageGoToBasketCommand.class);

    private final transient MySqlCruiseDAO cruiseDAO;

    public ClientPageGoToBasketCommand(MySqlCruiseDAO cruiseDAO) {
        this.cruiseDAO = cruiseDAO;
    }


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("ClientPageGoToBasketCommand starts");
        List<Cruise> allCruises = cruiseDAO.findAllCruises();
        LOG.trace("Found in DB: allCruises --> " + allCruises);
        // put cruises items list to the request
        request.setAttribute("allCruises", allCruises);
        LOG.trace("Set the request attribute: allCruises --> " + allCruises);

        LOG.debug("ClientPageGoToBasketCommand finished");

        return Path.BASKET;
    }

}