package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.MySqlOrderDAO;
import ua.nure.hrynko.dao.MySqlOrderViewDAO;
import ua.nure.hrynko.dto.OrderView;
import ua.nure.hrynko.exception.AppException;

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

    private List<OrderView> allItemOfOrdersViewWithLimit;

    public AdminPageOrderCommand(MySqlOrderViewDAO orderViewDAO) {
        this.orderViewDAO = orderViewDAO;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("AdminPageOrderCommand starts");
        HttpSession session = request.getSession();

        //get all item orders_view from DB to calculate the number of pagination pages
        List<OrderView> allOrdersViewList = orderViewDAO.findAllItemOfOrdersView();
        int countAllCruises = allOrdersViewList.size();

        int numberPage = Integer.parseInt(request.getParameter("page"));
        LOG.trace("Request parameter: numberPage --> " + numberPage);

        if (numberPage > 0) {
            int total = 5;
            int start = (numberPage - 1) * total;
            allItemOfOrdersViewWithLimit = orderViewDAO.findAllIItemOfOrderViewWithLimit(start, total);
        }
        // put count of cruises  to the request
        request.setAttribute("countAllCruises", countAllCruises);
        LOG.trace("Set the request attribute: countAllCruises --> " + countAllCruises);

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