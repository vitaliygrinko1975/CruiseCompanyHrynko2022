package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientPageGoToUpdatingAccountPageCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(ClientPageGoToUpdatingAccountPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("ClientPageGoToUpdatingAccountPageCommand starts");

        LOG.debug("ClientPageGoToUpdatingAccountPageCommand finished");
        return Path.CLIENT_PAGE_UPDATE_ACCOUNT;
    }
}

