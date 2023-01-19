package ua.nure.hrynko.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.MySqlUserDAO;
import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AdminPageCommandTest {
    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @Mock
    MySqlUserDAO mySqlUserMock;

    @BeforeEach
    public void setUp() {
        httpServletRequest = mock(HttpServletRequest.class);
        httpServletResponse = mock(HttpServletResponse.class);
        mySqlUserMock = mock(MySqlUserDAO.class);
    }

    @Test
    void execute() throws ServletException, AppException, IOException {
        AdminPageCommand adminPageCommand =
                new AdminPageCommand(mySqlUserMock);
        String pagePath = adminPageCommand.execute(httpServletRequest, httpServletResponse);
        assertEquals(Path.PAGE_ADMIN, pagePath);
    }

}