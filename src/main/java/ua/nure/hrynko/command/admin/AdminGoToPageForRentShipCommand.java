package ua.nure.hrynko.command.admin;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.interfaces.ShipViewDAO;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.ShipView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdminGoToPageForRentShipCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(AdminGoToPageForRentShipCommand.class);

    private final transient ShipViewDAO shipsViewDAO;

    public AdminGoToPageForRentShipCommand(ShipViewDAO shipsViewDAO) {
        this.shipsViewDAO = shipsViewDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, DBException {
        LOG.debug("AdminGoToPageForRentShipCommand starts");
        int cruiseId = Integer.parseInt(request.getParameter("cruiseIdForRent"));
        LOG.trace("Request parameter: shipIdForRent --> " + cruiseId);
        String startOfCruise = request.getParameter("startOfCruiseForRent");
        LOG.trace("Request parameter: startOfCruiseForRent --> " + startOfCruise);
        int durationOfCruise = Integer.parseInt(request.getParameter("durationOfCruiseForRent"));
        LOG.trace("Request parameter: durationOfCruiseForRent --> " + durationOfCruise);
        int capacityOfCruise = Integer.parseInt(request.getParameter("capacityOfCruiseForRent"));
        LOG.trace("Request parameter: capacityOfCruiseForRent --> " + capacityOfCruise);

        // Расчет даты конца круиза по дате начала и продолжительности
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-mm-dd");
        Date startOfCruiseDate= format.parse(startOfCruise);
        LOG.trace("Converting String  startOfCruise:" + startOfCruise + " in Date startOfCruise-->" + startOfCruiseDate);
        calendar.setTime(startOfCruiseDate);
        LOG.trace("Set Set calendar point to startOfCruiseDate --> " + startOfCruiseDate);
        calendar.add(Calendar.DAY_OF_MONTH, durationOfCruise);
        LOG.trace("Add durationOfCruise in calendar --> " + durationOfCruise);
        Date endOfCruiseDate = calendar.getTime();
        LOG.trace("Void endOfCruiseDate in format Date --> " + endOfCruiseDate);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String startOfCruiseInTheRightFormat = formatter.format(startOfCruiseDate);
        LOG.trace("startOfCruise in the right format --> " + startOfCruiseInTheRightFormat);
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-mm-dd");
        String endOfCruise = formatter1.format(endOfCruiseDate);
        LOG.trace("Converting Data endOfCruiseDate in String --> " + endOfCruise);

        List<ShipView> allFreeShips = shipsViewDAO.findAllFreeShip(startOfCruiseInTheRightFormat,endOfCruise);

        request.setAttribute("allFreeShips", allFreeShips);
        LOG.trace("Set request parameter: allFreeShips --> " + allFreeShips);
        request.setAttribute("cruiseIdForRent", cruiseId);
        LOG.trace("Set request parameter:cruiseIdForRent --> " + cruiseId);
        request.setAttribute("startOfCruiseForRent", startOfCruiseInTheRightFormat);
        LOG.trace("Set request parameter:startOfCruiseForRent --> " + startOfCruiseInTheRightFormat);
        request.setAttribute("endOfCruiseForRent", endOfCruise);
        LOG.trace("Set request parameter:endOfCruiseForRent --> " + endOfCruise);
        request.setAttribute("capacityOfCruiseForRent", capacityOfCruise);
        LOG.trace("Set request parameter:capacityOfCruiseForRent --> " + capacityOfCruise);
        LOG.debug("AdminGoToPageForRentShipCommand finished");

        return Path.ADMIN_PAGE_RENT_SHIP;
    }
}

