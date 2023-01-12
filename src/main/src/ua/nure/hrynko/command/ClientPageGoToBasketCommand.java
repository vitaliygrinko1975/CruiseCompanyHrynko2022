package ua.nure.hrynko.command;

import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.MySqlCruiseDAO;
import ua.nure.hrynko.dto.Cruise;
import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Map for basket.
 */
public class ClientPageGoToBasketCommand extends Command {
    private static final long serialVersionUID = 7732286214029478505L;
    private static final Logger LOG = Logger.getLogger(ClientPageGoToBasketCommand.class);


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("ClientPageGoToBasketCommand starts");


        LOG.debug("ClientPageGoToBasketCommand finished");

        return Path.BASKET;
    }

}