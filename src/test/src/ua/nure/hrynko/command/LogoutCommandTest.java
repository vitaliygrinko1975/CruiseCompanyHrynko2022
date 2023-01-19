package ua.nure.hrynko.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.nure.hrynko.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class LogoutCommandTest {
    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @Mock
    HttpSession session;

    @BeforeEach
    public void setUp() {
        httpServletRequest = mock(HttpServletRequest.class);
        httpServletResponse = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    @Test
    void execute() {
        when(httpServletRequest.getSession()).thenReturn(session);
        LogoutCommand logoutCommand =
                new LogoutCommand();
        String pagePath = logoutCommand.execute(httpServletRequest, httpServletResponse);
        verify(httpServletRequest,times(1)).getSession(false);
        assertEquals(Path.PAGE_LOGIN, pagePath);

    }
}