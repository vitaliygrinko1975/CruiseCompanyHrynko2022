package ua.nure.hrynko.command.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.admin.AdminGoToPageForAddingShipCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AdminGoToPageForAddingShipCommandTest {

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
        AdminGoToPageForAddingShipCommand adminGoToPageForAddingShipCommand =
                new AdminGoToPageForAddingShipCommand();
        String pagePath = adminGoToPageForAddingShipCommand.execute(httpServletRequest, httpServletResponse);
        assertEquals(Path.ADMIN_PAGE_ADD_SHIP, pagePath);
    }
}