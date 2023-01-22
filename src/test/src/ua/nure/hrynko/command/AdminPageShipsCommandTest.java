package ua.nure.hrynko.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.MySqlShipDAO;
import ua.nure.hrynko.dao.MySqlUserDAO;
import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

class AdminPageShipsCommandTest {
    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @Mock
    MySqlShipDAO mySqlShipMock;

    @BeforeEach
    public void setUp() {
        httpServletRequest = mock(HttpServletRequest.class);
        httpServletResponse = mock(HttpServletResponse.class);
        mySqlShipMock = mock(MySqlShipDAO.class);
    }

    @Test
    void execute() throws ServletException, AppException, IOException {
        AdminPageShipsCommand adminPageShipsCommand =
                new AdminPageShipsCommand(mySqlShipMock);
        String pagePath = adminPageShipsCommand.execute(httpServletRequest, httpServletResponse);
        verify(httpServletRequest,times(1)).getSession();
        assertEquals(Path.PAGE_ADMIN_SHIPS, pagePath);
    }
}