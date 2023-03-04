package ua.nure.hrynko.command.admin;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.MySqlOrderViewDAO;
import ua.nure.hrynko.models.OrderView;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.services.AllMethodsWithTransactions;
import ua.nure.hrynko.services.MyEmailSender;
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
public class AdminPageChangeStatusWithWithdrawalFromDepositCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(AdminPageChangeStatusWithWithdrawalFromDepositCommand.class);

    private final transient AllMethodsWithTransactions allMethodsWithTransactions;

    private final transient MySqlOrderViewDAO orderViewDAO;


    public AdminPageChangeStatusWithWithdrawalFromDepositCommand(AllMethodsWithTransactions allMethodsWithTransactions,
                                                                 MySqlOrderViewDAO orderViewDAO) {

        this.allMethodsWithTransactions = allMethodsWithTransactions;
        this.orderViewDAO = orderViewDAO;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("AdminPageChangeStatusWithWithdrawalFromDepositCommand starts");
        HttpSession session = request.getSession();
        Paginations paginations = new Paginations();
        int orderIdForUpdate = Integer.parseInt(request.getParameter("ordersViewIdForUpdateButt"));
        LOG.trace("Request parameter: ordersViewIdForUpdateButt --> " + orderIdForUpdate);
        String status = request.getParameter("status");
        LOG.trace("Request parameter: status --> " + status);
        String usersEmail = request.getParameter("usersEmail");
        LOG.trace("Request parameter: usersEmail --> " + usersEmail);
        int numberPage = (int) session.getAttribute("numberPage");
        LOG.trace("Get session attribute: numberPage --> " + numberPage);

        allMethodsWithTransactions.changeStatusWithWithdrawalFromDeposit(orderIdForUpdate,status);

        //get all item orders_view from DB to calculate the number of pagination pages
        int countAllCruises = orderViewDAO.countingTheNumberOfRecordsToOrderViewDb();

    // put count of cruises  to the  session
        session.setAttribute("countAllCruises",countAllCruises);
        LOG.trace("Set the session attribute: countAllCruises --> "+ countAllCruises);

        //get list with limit
        List<OrderView> allItemOfOrdersViewWithLimit = paginations.makePaginationForOrders(numberPage);

    // put cruises items list to the request
        session.setAttribute("allItemOfOrdersViewWithLimit",allItemOfOrdersViewWithLimit);
        LOG.trace("Set the session attribute: allItemOfOrdersViewWithLimit --> " + allItemOfOrdersViewWithLimit);
        MyEmailSender myEmailSender = new MyEmailSender();
        myEmailSender.generateReport("Недостаточно средств для оплаты круиза", usersEmail);
        LOG.trace("Send email with text --> " + "Недостаточно средств для оплаты круиза");

        LOG.debug("AdminPageChangeStatusWithWithdrawalFromDepositCommand finished");

        return Path.PAGE_ADMIN_ORDERS;
}

}