package ua.nure.hrynko.command.admin;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.Command;
import ua.nure.hrynko.dao.interfaces.UserDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.User;
import ua.nure.hrynko.services.Paginations;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

    public  class AdminPageCommand extends Command {

        private static final long serialVersionUID = 7732286214029478505L;

        private static final Logger LOG = Logger.getLogger(AdminPageCommand.class);

        private  final transient UserDAO userDAO;

        public AdminPageCommand(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
            LOG.debug("Command starts");
            Paginations paginations = new Paginations();
            HttpSession session = request.getSession();

            int numberPage = Integer.parseInt(request.getParameter("page"));
            LOG.trace("Request parameter: numberPage --> " + numberPage);

            List<User> allItemOfUserWithLimit = paginations.makePaginationForUsers(numberPage);
            LOG.trace("Found in DB: userList --> " + allItemOfUserWithLimit);

            int countAllUsers = userDAO.countingTheNumberOfRecordsToUserDb();
            // put user order beans list to request

            request.setAttribute("allItemOfUserWithLimit", allItemOfUserWithLimit);
            LOG.trace("Set the request attribute: allItemOfUserWithLimit --> " + allItemOfUserWithLimit);

            request.setAttribute("countAllUsers",countAllUsers);
            LOG.trace("Set the request attribute: countAllUsers --> " + countAllUsers);

            session.setAttribute("numberPage", numberPage);
            LOG.trace("Set the request attribute: numberPage --> " + numberPage);

            LOG.debug("Commands finished");

            return Path.PAGE_ADMIN;

        }
    }
