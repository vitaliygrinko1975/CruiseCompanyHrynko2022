package ua.nure.hrynko;

/**
 * Path holder (jsp pages, controller commands).

 */
public final class Path {

	// pages
	public static final String PAGE_WELCOME = "/welcome.jsp";
	public static final String PAGE_WELCOME_REGISTERED_USER = "welcome_ registered_ user.jsp";
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_SIGN_UP = "/WEB-INF/jsp/client/sign_up_page.jsp";
	public static final String PAGE_ERROR_PAGE = "error_page.jsp";
	public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
	public static final String PAGE_MESSAGE = "message.jsp";

	// client pages
	public static final String PAGE_CLIENT = "/WEB-INF/jsp/client/client_page.jsp";
	public static final String BASKET = "basket.jsp";
	public static final String PAGE_CLIENT_TARIFF = "/WEB-INF/jsp/client/client_page_tariff.jsp";
	public static final String PAGE_CLIENT_TOP_UP_YOUR_ACCOUNT = "/WEB-INF/jsp/client/client_page_top_up_your_account.jsp";
	public static final String CLIENT_PAGE_UPDATE_ACCOUNT = "/WEB-INF/jsp/client/client_page_update_account.jsp";


	// admin pages
	public static final String PAGE_ADMIN = "/WEB-INF/jsp/admin/admin_page.jsp";
	public static final String PAGE_ADMIN_TARIFFS = "/WEB-INF/jsp/admin/admin_page_tariffs.jsp";
	public static final String ADMIN_PAGE_UPDATE_USER = "/WEB-INF/jsp/admin/admin_page_update_user.jsp";
	public static final String ADMIN_PAGE_ADD_USER = "/WEB-INF/jsp/admin/admin_page_add_user.jsp";
	public static final String ADMIN_PAGE_ADD_TARIFF = "/WEB-INF/jsp/admin/admin_page_add_tariff.jsp";
	public static final String ADMIN_PAGE_UPDATE_TARIFF = "/WEB-INF/jsp/admin/admin_page_update_tariff.jsp";


	// commands
	public static final String COMMAND_PAGE_ADMIN = "/controller?command=adminPage";
	public static final String COMMAND_PAGE_CLIENT = "/controller?command=clientPage";
	public static final String COMMAND_PAGE_CLIENT_GO_TO_TOP_UP_YOUR =
			"/controller?command=ClientPageGoToTopUpYourAccount";
	public static final String COMMAND_PAGE_ADMIN_TARIFFS = "/controller?command=pageAdminTariffs";


}