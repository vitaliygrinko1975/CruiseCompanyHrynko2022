package ua.nure.hrynko.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.nure.hrynko.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AdminGoToPageForAddingCruiseCommandTest {
    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @BeforeEach
    public void setUp() {
        httpServletRequest = mock(HttpServletRequest.class);
        httpServletResponse = mock(HttpServletResponse.class);

    }

    @Test
    void execute() {
        AdminGoToPageForAddingCruiseCommand adminGoToPageForAddingCruiseCommand =
                new AdminGoToPageForAddingCruiseCommand();
        String pagePath = adminGoToPageForAddingCruiseCommand.execute(httpServletRequest, httpServletResponse);
        assertEquals(Path.ADMIN_PAGE_ADD_CRUISE, pagePath);
    }
}