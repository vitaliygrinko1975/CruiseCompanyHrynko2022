package ua.nure.hrynko.command.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.dao.MySqlCruiseHasShipDAO;
import ua.nure.hrynko.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class AdminGoToPageCruiseHasShipCommandTest {
    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @Mock
    MySqlCruiseHasShipDAO mySqlCruiseHasShipMock;

    @BeforeEach
    public void setUp() {
        httpServletRequest = mock(HttpServletRequest.class);
        httpServletResponse = mock(HttpServletResponse.class);
        mySqlCruiseHasShipMock = mock(MySqlCruiseHasShipDAO.class);
    }
    @Test
    void execute() throws DBException, ParseException {
        AdminGoToPageCruiseHasShipCommand adminPageCruisesCommand =
                new AdminGoToPageCruiseHasShipCommand(mySqlCruiseHasShipMock);
        String pagePath = adminPageCruisesCommand.execute(httpServletRequest, httpServletResponse);
        assertEquals(Path.PAGE_ADMIN_CONTRACTS, pagePath);

    }
}