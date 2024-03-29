package ua.nure.hrynko.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.client.ClientPageGoToUpdatingAccountPageCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ClientPageGoToUpdatingAccountPageCommandTest {
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
        ClientPageGoToUpdatingAccountPageCommand clientPageGoToUpdatingAccountPageCommand =
                new ClientPageGoToUpdatingAccountPageCommand();
        String pagePath = clientPageGoToUpdatingAccountPageCommand.execute(httpServletRequest, httpServletResponse);
        assertEquals(Path.CLIENT_PAGE_UPDATE_ACCOUNT, pagePath);
    }
}