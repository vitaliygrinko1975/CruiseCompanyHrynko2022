package ua.nure.hrynko.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.Order;
import ua.nure.hrynko.models.OrderView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MySqlOrderViewDAOTest {
    @Mock
    Connection mockCon;
    @Mock
    MySqlOrderViewDAO mySqlOrderViewMock;
    @Mock
    OrderView orderViewMock;
    @Mock
    ResultSet mockResultSet;

    @BeforeEach
    public void setUp() {
        mySqlOrderViewMock = mock(MySqlOrderViewDAO.class);
        orderViewMock = mock(OrderView.class);
        mockCon = mock(Connection.class);
        mockResultSet = mock(ResultSet.class);
    }
    @Test
    void getInstance() {
        try (MockedStatic<MySqlOrderViewDAO> mySqlOrderViewDAOMockedStatic = Mockito.mockStatic(MySqlOrderViewDAO.class)) {
            mySqlOrderViewDAOMockedStatic.when(MySqlOrderViewDAO::getInstance).thenReturn (mySqlOrderViewMock);
            assertEquals(mySqlOrderViewMock, MySqlOrderViewDAO.getInstance());
        }
    }
    @Test
    void findAllItemOfOrdersView() throws DBException, SQLException {
        List<OrderView> listWithAllOrder = new ArrayList<>();
        when(mySqlOrderViewMock.findAllItemOfOrdersView()).thenReturn(listWithAllOrder);
        doThrow(new SQLException()).when(mockCon).close();
        assertEquals(listWithAllOrder, mySqlOrderViewMock.findAllItemOfOrdersView());
    }
    @Test
    void findAllIItemOfOrderViewByUserId() throws DBException, SQLException {
        List<OrderView> listWithAllOrder = new ArrayList<>();
        when(mySqlOrderViewMock.findAllIItemOfOrderViewByUserId(anyInt())).thenReturn(listWithAllOrder);
        assertEquals(listWithAllOrder, mySqlOrderViewMock.findAllIItemOfOrderViewByUserId(anyInt()));
        doThrow(new SQLException()).when(mockCon).close();
    }
    @Test
    void findAllItemOfOrderViewWithLimit() throws SQLException, DBException {
        List<OrderView> listWithAllOrder = new ArrayList<>();
        when(mySqlOrderViewMock.findAllItemOfOrderViewWithLimit(anyInt(),anyInt())).thenReturn(listWithAllOrder);
        doThrow(new SQLException()).when(mockCon).close();
        assertEquals(listWithAllOrder, mySqlOrderViewMock.findAllItemOfOrderViewWithLimit(anyInt(),anyInt()));
    }
    @Test
    void findAllIItemOfOrderViewByUserIdWithLimit() throws DBException, SQLException {
        List<OrderView> listWithAllOrder = new ArrayList<>();
        when(mySqlOrderViewMock.findAllIItemOfOrderViewByUserIdWithLimit(anyInt(),anyInt(),anyInt())).thenReturn(listWithAllOrder);
        doThrow(new SQLException()).when(mockCon).close();
        assertEquals(listWithAllOrder, mySqlOrderViewMock.findAllIItemOfOrderViewByUserIdWithLimit(anyInt(),anyInt(),anyInt()));

    }

    @Test
    void extractOrdersView() throws SQLException {
        OrderView orderView = new OrderView();
        orderView.setId(mockResultSet.getInt(Fields.ENTITY_ID));
        orderView.setUsersFirstName(mockResultSet.getString(Fields.USER_FIRST_NAME));
        orderView.setUsersLastName(mockResultSet.getString(Fields.USER_LAST_NAME));
        orderView.setUsersEmail(mockResultSet.getString(Fields.USER_EMAIL));
        orderView.setCruisesDescription(mockResultSet.getString(Fields.CRUISE_DESCRIPTION));
        orderView.setStatusOfCruises(mockResultSet.getString(Fields.STATUS_OF_CRUISES));
        orderView.setStatus(mockResultSet.getString(Fields.STATUS));
    }
}