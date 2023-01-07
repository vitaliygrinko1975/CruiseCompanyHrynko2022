package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.MySqlUsersHasCruiseDAO;
import ua.nure.hrynko.dto.UserHasCruise;
import ua.nure.hrynko.exception.AppException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Lists cruises items.
 */
public class AdminPageOrderCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(AdminPageOrderCommand.class);

    private final transient MySqlUsersHasCruiseDAO usersHasCruiseDAO;

    public AdminPageOrderCommand(MySqlUsersHasCruiseDAO usersHasCruiseDAO) {
        this.usersHasCruiseDAO = usersHasCruiseDAO;
    }


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("AdminPageOrderCommand starts");


        List<UserHasCruise> allUsersHasCruises = usersHasCruiseDAO.findAllItemOfUsersHasCruises();

        int countAllCruises = allUsersHasCruises.size();

        int numberPage = Integer.parseInt(request.getParameter("page"));
        LOG.trace("Request parameter: numberPage --> " + numberPage);

        if (numberPage > 0) {
            int start = (numberPage - 1) * 5;
            int total = 5;
            allUsersHasCruises = usersHasCruiseDAO.findAllIItemOfUsersHasCruiseWithLimit(start, total);
        }
        // put count of cruises  to the request
        request.setAttribute("countAllCruises", countAllCruises);
        LOG.trace("Set the request attribute: countAllCruises --> " + countAllCruises);

        // put cruises items list to the request
        request.setAttribute("allUsersHasCruises", allUsersHasCruises);
        LOG.trace("Set the request attribute: allUsersHasCruises --> " + allUsersHasCruises);


        LOG.debug("AdminPageOrderCommand finished");

        return Path.PAGE_ADMIN_ORDERS;
    }

}