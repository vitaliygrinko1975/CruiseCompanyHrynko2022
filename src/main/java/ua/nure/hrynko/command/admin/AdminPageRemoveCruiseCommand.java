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

public class AdminPageRemoveCruiseCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminPageRemoveCruiseCommand.class);

    private  final transient CruiseDAO cruiseDAO;

    public AdminPageRemoveCruiseCommand(CruiseDAO cruiseDAO) {
        this.cruiseDAO = cruiseDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {

        LOG.debug("AdminPageRemoveCruiseCommand starts");
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("cruiseIdForRemoveCruiseButt"));
        LOG.trace("Request parameter: id --> " + id);
        cruiseDAO.removeCruiseFromDb(id);
        LOG.trace("Remove cruise by id: --> " + id);
        List<Cruise> allCruises = cruiseDAO.findAllCruises();
        LOG.trace("Found in DB: allCruises --> " + allCruises);
        // put cruises items list to the request
        session.setAttribute("allCruises", allCruises);
        LOG.debug("AdminPageRemoveCruiseCommand finished");
        return Path.PAGE_ADMIN_CRUISES;
    }

}

