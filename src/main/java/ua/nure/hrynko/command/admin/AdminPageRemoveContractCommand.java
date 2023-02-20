package ua.nure.hrynko.command.admin;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.interfaces.CruiseHasShipDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.CruiseHasShip;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminPageRemoveContractCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminPageRemoveContractCommand.class);

    private final transient CruiseHasShipDAO cruiseHasShipDAO;

    public AdminPageRemoveContractCommand(CruiseHasShipDAO cruiseHasShipDAO) {
        this.cruiseHasShipDAO = cruiseHasShipDAO;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("AdminPageRemoveContractCommand starts");
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("cruisesHasShipIdForRemoveCruisesHasShipButt"));
        LOG.trace("Request parameter: id --> " + id);
        cruiseHasShipDAO.removeCruiseHasShipFromDb(id);
        LOG.trace("Remove cruise by id: --> " + id);
        List<CruiseHasShip> allCruisesHasShipList = cruiseHasShipDAO.findAllItemsOfCruiseHasShip();
        LOG.trace("Found in DB: allCruisesHasShipList --> " + allCruisesHasShipList);

        // put cruises items list to the request
        session.setAttribute("allCruisesHasShipList", allCruisesHasShipList);
        LOG.debug("AdminPageRemoveContractCommand finished");
        return Path.PAGE_ADMIN_CONTRACTS;
    }

}

