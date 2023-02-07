package ua.nure.hrynko.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.client.ClientPageGoToBasketCommand;
import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ClientPageGoToBasketCommandTest {
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
    void execute() throws ServletException, AppException, IOException {
        ClientPageGoToBasketCommand clientPageGoToBasketCommand =
                new ClientPageGoToBasketCommand();
        String pagePath = clientPageGoToBasketCommand.execute(httpServletRequest, httpServletResponse);
        assertEquals(Path.BASKET, pagePath);
    }
}