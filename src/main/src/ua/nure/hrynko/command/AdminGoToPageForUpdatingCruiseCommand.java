package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;

import ua.nure.hrynko.dao.interfaces.CruiseDAO;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.Cruise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminGoToPageForUpdatingCruiseCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminGoToPageForUpdatingCruiseCommand.class);


    private final transient CruiseDAO cruiseDAO;

    public AdminGoToPageForUpdatingCruiseCommand(CruiseDAO cruiseDAO) {
        this.cruiseDAO = cruiseDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DBException {
        LOG.debug("AdminGoToPageForUpdatingCruiseCommand starts");
        int id = Integer.parseInt(request.getParameter("cruiseIdForUpdateCruiseButt"));
        LOG.trace("Request parameter: id --> " + id);
        Cruise cruise = cruiseDAO.findCruiseById(id);
        LOG.trace("Update cruise with DB  from update: --> " + cruise);
        request.setAttribute("cruise", cruise);
        LOG.trace("User set to Attribute: --> " + cruise);
        LOG.debug("AdminGoToPageForUpdatingCruiseCommand finished");

        return Path.ADMIN_PAGE_UPDATE_CRUISE;
    }

}

