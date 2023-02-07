package ua.nure.hrynko.command.admin;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.MySqlOrderViewDAO;
import ua.nure.hrynko.models.OrderView;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.services.Paginations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * Lists cruises items.
 */
public class AdminPageOrderCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(AdminPageOrderCommand.class);

    private final transient MySqlOrderViewDAO orderViewDAO;

    public AdminPageOrderCommand(MySqlOrderViewDAO orderViewDAO) {
        this.orderViewDAO = orderViewDAO;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("AdminPageOrderCommand starts");
        Paginations paginations = new Paginations();
        HttpSession session = request.getSession();

        //get all item orders_view from DB to calculate the number of pagination pages
//        List<OrderView> allOrdersViewList = orderViewDAO.findAllItemOfOrdersView();
//        int countAllOrders = allOrdersViewList.size();

        int countAllOrders = orderViewDAO.countingTheNumberOfRecordsToOrderViewDb();


        int numberPage = Integer.parseInt(request.getParameter("page"));
        LOG.trace("Request parameter: numberPage --> " + numberPage);

        //get list with limit
        List<OrderView> allItemOfOrdersViewWithLimit = paginations.makePaginationForOrders(numberPage);

        // put count of cruises  to the request
        request.setAttribute("countAllOrders", countAllOrders);
        LOG.trace("Set the request attribute: countAllOrders --> " + countAllOrders);

        // put cruises items list to the request
        request.setAttribute("allItemOfOrdersViewWithLimit", allItemOfOrdersViewWithLimit);
        LOG.trace("Set the request attribute: allItemOfOrdersViewWithLimit --> " + allItemOfOrdersViewWithLimit);

        // put numberPage to the session
        session.setAttribute("numberPage", numberPage);
        LOG.trace("Set the request attribute: numberPage --> " + numberPage);

        LOG.debug("AdminPageOrderCommand finished");


        return Path.PAGE_ADMIN_ORDERS;
    }

}