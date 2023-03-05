package ua.nure.hrynko.command.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.nure.hrynko.Path;
import ua.nure.hrynko.command.admin.AdminPageCruisesCommand;
import ua.nure.hrynko.dao.MySqlCruiseDAO;
import ua.nure.hrynko.dao.MySqlCruiseHasShipDAO;
import ua.nure.hrynko.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AdminPageCruisesCommandTest {
    @Mock
    HttpServletRequest httpServletRequest;

    @Mock
    HttpServletResponse httpServletResponse;

    @Mock
    MySqlCruiseDAO mySqlCruiseMock;

    @Mock
    MySqlCruiseHasShipDAO mySqlCruiseHasShipMock;

    @BeforeEach
    public void setUp() {
        httpServletRequest = mock(HttpServletRequest.class);
        httpServletResponse = mock(HttpServletResponse.class);
        mySqlCruiseMock = mock(MySqlCruiseDAO.class);
        mySqlCruiseHasShipMock = mock(MySqlCruiseHasShipDAO.class);
    }
//
//    @Test
//    void execute() throws ServletException, AppException, IOException {
//        AdminPageCruisesCommand adminPageCruisesCommand =
//                new AdminPageCruisesCommand(mySqlCruiseMock,mySqlCruiseHasShipMock);
//        String pagePath = adminPageCruisesCommand.execute(httpServletRequest, httpServletResponse);
//        assertEquals(Path.PAGE_ADMIN_CRUISES, pagePath);
//    }
}