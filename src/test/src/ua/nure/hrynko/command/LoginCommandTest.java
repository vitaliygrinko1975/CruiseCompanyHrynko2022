package ua.nure.hrynko.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.MySqlUserDAO;
import ua.nure.hrynko.exception.AppException;
import ua.nure.hrynko.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginCommandTest {

    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @Mock
    MySqlUserDAO usersDAO;

    @Mock
    HttpSession sessionTest;

    private static final int ADMIN_ROLE_ID = 1;
    private static final int CLIENT_ROLE_ID = 2;

    @BeforeEach
    public void setUp() {
        httpServletRequest = mock(HttpServletRequest.class);
        httpServletResponse = mock(HttpServletResponse.class);
        sessionTest = mock(HttpSession.class);
        usersDAO = mock(MySqlUserDAO.class);
        when(httpServletRequest.getSession())
                .thenReturn(sessionTest);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionIfPasswordIsBlankOrNull(String password) {
        when(httpServletRequest.getParameter("login")).thenReturn("logon");
        when(httpServletRequest.getParameter("password")).thenReturn(password);

        LoginCommand loginCommand = new LoginCommand(MySqlUserDAO.getInstance());
        Assertions.assertThrows(AppException.class, () -> loginCommand.execute(httpServletRequest, httpServletResponse),
                "Login/password cannot be empty");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionIfLoginIsBlankOrNull(String login) {
        when(httpServletRequest.getParameter("login")).thenReturn(login);
        when(httpServletRequest.getParameter("password")).thenReturn("password");

        LoginCommand loginCommand = new LoginCommand(MySqlUserDAO.getInstance());
        Assertions.assertThrows(AppException.class, () -> loginCommand.execute(httpServletRequest, httpServletResponse));
    }

    @Test
    void shouldThrowExceptionIfUserWasNotFoundByLogin() throws Exception {
        when(httpServletRequest.getParameter("login")).thenReturn("login");
        when(httpServletRequest.getParameter("password")).thenReturn("password");
        when(usersDAO.findUserByLogin(anyString())).thenReturn(null);

        try (MockedStatic<MySqlUserDAO> mySqlUsersDAOMockedStatic = Mockito.mockStatic(MySqlUserDAO.class)) {
            mySqlUsersDAOMockedStatic.when(MySqlUserDAO::getInstance).thenReturn(usersDAO);
            LoginCommand loginCommand = new LoginCommand(MySqlUserDAO.getInstance());
            Assertions.assertThrows(AppException.class, () -> loginCommand.execute(httpServletRequest, httpServletResponse));
        }
    }

    @Test
    void shouldThrowExceptionIfPasswordIsIncorrect() throws Exception {
        when(httpServletRequest.getParameter("login")).thenReturn("login");
        when(httpServletRequest.getParameter("password")).thenReturn("password");
        User user = new User();
        user.setPassword("differentPassword");
        when(usersDAO.findUserByLogin(anyString())).thenReturn(user);

        try (MockedStatic<MySqlUserDAO> mySqlUsersDAOMockedStatic = Mockito.mockStatic(MySqlUserDAO.class)) {
            mySqlUsersDAOMockedStatic.when(MySqlUserDAO::getInstance).thenReturn(usersDAO);
            LoginCommand loginCommand = new LoginCommand(MySqlUserDAO.getInstance());
            Assertions.assertThrows(AppException.class, () -> loginCommand.execute(httpServletRequest, httpServletResponse));
        }
    }

    @Test
    void shouldReturnAdminPageIfUserRoleIsAdmin() throws Exception {
        when(httpServletRequest.getParameter("login")).thenReturn("login");
        when(httpServletRequest.getParameter("password")).thenReturn("password");
        User user = new User();
        user.setPassword("password");
        user.setRoleId(ADMIN_ROLE_ID);
        when(usersDAO.findUserByLogin(anyString())).thenReturn(user);

        try (MockedStatic<MySqlUserDAO> mySqlUsersDAOMockedStatic = Mockito.mockStatic(MySqlUserDAO.class)) {
            mySqlUsersDAOMockedStatic.when(MySqlUserDAO::getInstance).thenReturn(usersDAO);
            LoginCommand loginCommand = new LoginCommand(MySqlUserDAO.getInstance());

            String pagePath = loginCommand.execute(httpServletRequest, httpServletResponse);
            assertEquals(Path.PAGE_WELCOME_REGISTERED_USER, pagePath);
        }
    }

    @Test
    void shouldReturnClientPageIfUserRoleIsClient() throws Exception {
        when(httpServletRequest.getParameter("login")).thenReturn("login");
        when(httpServletRequest.getParameter("password")).thenReturn("password");
        User user = new User();
        user.setPassword("password");
        user.setRoleId(CLIENT_ROLE_ID);
        when(usersDAO.findUserByLogin(anyString())).thenReturn(user);

        try (MockedStatic<MySqlUserDAO> mySqlUsersDAOMockedStatic = Mockito.mockStatic(MySqlUserDAO.class)) {
            mySqlUsersDAOMockedStatic.when(MySqlUserDAO::getInstance).thenReturn(usersDAO);
            LoginCommand loginCommand = new LoginCommand(MySqlUserDAO.getInstance());

            String pagePath = loginCommand.execute(httpServletRequest, httpServletResponse);
            assertEquals(Path.PAGE_WELCOME_REGISTERED_USER, pagePath);
        }
    }
}