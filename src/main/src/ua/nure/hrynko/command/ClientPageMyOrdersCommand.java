package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.MySqlOrderViewDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.OrderView;
import ua.nure.hrynko.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * Lists orders items.
 */
public class ClientPageMyOrdersCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(ClientPageMyOrdersCommand.class);

    private final transient MySqlOrderViewDAO orderViewDAO;

    private List<OrderView> allItemOfOrdersViewWithLimit;

    public ClientPageMyOrdersCommand(MySqlOrderViewDAO orderViewDAO) {
        this.orderViewDAO = orderViewDAO;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("ClientPageMyOrdersCommand starts");
        HttpSession session = request.getSession();
        int numberPage = Integer.parseInt(request.getParameter("page"));
        LOG.trace("Request parameter: page --> " + numberPage);
        User user = (User)session.getAttribute("user");
        int id = user.getId();
        LOG.trace("Request parameter: userIdForOrder --> " + id);

        //get all item orders_view from DB to calculate the number of pagination pages
        List<OrderView> allOrdersViewByUserIdList = orderViewDAO.findAllIItemOfOrderViewByUserId(id);
        int countAllCruises = allOrdersViewByUserIdList.size();
        LOG.trace("total items in the list --> " + countAllCruises);
        if (numberPage > 0) {
            int total = 5;
            int start = (numberPage - 1) * total;
            allItemOfOrdersViewWithLimit = orderViewDAO.findAllIItemOfOrderViewByUserIdWithLimit(id,start, total);
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
        LOG.debug("ClientPageMyOrdersCommand finished");

        return Path.PAGE_CLIENT_MY_ORDERS;
    }

}