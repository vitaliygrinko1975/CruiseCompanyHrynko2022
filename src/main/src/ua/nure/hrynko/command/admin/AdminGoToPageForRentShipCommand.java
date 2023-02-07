package ua.nure.hrynko.command.admin;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminGoToPageForRentShipCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminGoToPageForRentShipCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("AdminGoToPageForRentShipCommand starts");
        int shipId = Integer.parseInt(request.getParameter("shipIdForRentButt"));
        LOG.trace("Request parameter: shipIdForRentButt --> " + shipId);

        request.setAttribute("shipId", shipId);
        LOG.trace("Id ship set to Attribute: --> " + shipId);
        LOG.debug("AdminGoToPageForRentShipCommand finished");

        return Path.ADMIN_PAGE_RENT_SHIP;
    }
}

