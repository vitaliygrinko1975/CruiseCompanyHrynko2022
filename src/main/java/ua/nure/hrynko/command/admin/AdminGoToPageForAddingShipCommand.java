package ua.nure.hrynko.command.admin;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminGoToPageForAddingShipCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminGoToPageForAddingShipCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("AdminGoToPageForAddingShipCommand starts");

        LOG.debug("AdminGoToPageForAddingShipCommand finished");

        return Path.ADMIN_PAGE_ADD_SHIP;
    }

}

