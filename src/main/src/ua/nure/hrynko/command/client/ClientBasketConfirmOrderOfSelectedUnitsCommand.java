package ua.nure.hrynko.command.client;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.models.Cruise;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.services.AllMethodsWithTransactions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * Lists cruises items.
 */
public class ClientBasketConfirmOrderOfSelectedUnitsCommand extends Command {
    private static final long serialVersionUID = 7732286214029478505L;
    private static final Logger LOG = Logger.getLogger(ClientBasketConfirmOrderOfSelectedUnitsCommand.class);
    private final transient AllMethodsWithTransactions allMethodsWithTransactions;


    public ClientBasketConfirmOrderOfSelectedUnitsCommand(AllMethodsWithTransactions addBasketToUsersHasCruisesDb) {
        this.allMethodsWithTransactions = addBasketToUsersHasCruisesDb;
    }

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("ClientBasketConfirmOrderOfSelectedUnitsCommand starts");
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(request.getParameter("userIdForBasketUsersHasCruisesButt"));
        LOG.trace("Request parameter: userId --> " + userId);


        HashMap<Cruise, Integer> mapForBasket = (HashMap<Cruise, Integer>) session.getAttribute("mapForBasket");

        int depositAmount = allMethodsWithTransactions.addBasketToOrdersDbReturnDepositAmount(userId, mapForBasket);

        session.setAttribute("depositAmount", depositAmount);
        LOG.trace("Set the session attribute: depositAmount --> " + depositAmount);
        mapForBasket.clear();

        LOG.debug("ClientBasketConfirmOrderOfSelectedUnitsCommand finished");


        return Path.CLIENT_PAGE_UPDATE_ACCOUNT;
    }
}

