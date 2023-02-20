package ua.nure.hrynko.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.RoleEnum;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


/**
 * Security filter. Disabled by default. Uncomment Security filter
 * section in web.xml to enable.
 */
public class CommandAccessFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(CommandAccessFilter.class);

    // commands access
    private Map<RoleEnum, List<String>> accessMap = new HashMap<>();
    private List<String> commons = new ArrayList<>();
    private List<String> outOfControl = new ArrayList<>();

    public void destroy() {
        LOG.debug("Filter destruction starts");
        // do nothing
        LOG.debug("Filter destruction finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug("Filter starts");
        //get parameter "local" from request and set attribute "local" to session
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String local = request.getParameter("local");
        LOG.trace("Set the request attribute: local --> " + local);
        if (local != null) {
            session.setAttribute("local", local);
            LOG.trace("Set the session attribute: local --> " + local);
        }
        if (isAccessAllowed(request)) {
            LOG.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";

            request.setAttribute("errorMessage", errorMessage);
            LOG.trace("Set the request attribute: errorMessage --> " + errorMessage);

            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE)
                    .forward(request, response);
        }
    }

    private boolean isAccessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");

//		if (commandName == null || commandName.isEmpty()) {
//			return false;
//		}

        if (StringUtils.isBlank(commandName)) {
            return false;
        }

        if (outOfControl.contains(commandName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }

        RoleEnum userRole = (RoleEnum) session.getAttribute("userRole");
        if (userRole == null) {
            return false;
        }

        return accessMap.get(userRole).contains(commandName)
                || commons.contains(commandName);

    }

    public void init(FilterConfig fConfig){
        LOG.debug("Filter initialization starts");

        // roles
        accessMap.put(RoleEnum.ADMIN, asList(fConfig.getInitParameter("admin")));
        accessMap.put(RoleEnum.CLIENT, asList(fConfig.getInitParameter("client")));
        LOG.trace("Access map --> " + accessMap);

        // commons
        commons = asList(fConfig.getInitParameter("common"));
        LOG.trace("Common commands --> " + commons);

        // out of control
        outOfControl = asList(fConfig.getInitParameter("out-of-control"));
        LOG.trace("Out of control commands --> " + outOfControl);

        LOG.debug("Filter initialization finished");
    }

    /**
     * Extracts parameter values from string.
     *
     * @param str parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        //разделять строку на подстроки
        StringTokenizer st = new StringTokenizer(str);

        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }

}