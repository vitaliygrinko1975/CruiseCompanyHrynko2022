package ua.nure.hrynko.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MySqlOrderDAOTest {

    @Mock
    Connection mockCon;
    @Mock
    MySqlOrderDAO mySqlOrderMock;
    @Mock
    Order orderMock;
    @Mock
    ResultSet mockResultSet;

    @BeforeEach
    public void setUp() {
        mySqlOrderMock = mock(MySqlOrderDAO.class);
        orderMock = mock(Order.class);
        mockCon = mock(Connection.class);
        mockResultSet = mock(ResultSet.class);
    }

    @Test
    void getInstance() {
        try (MockedStatic<MySqlOrderDAO> sqlOrderDAOMockedStatic = Mockito.mockStatic(MySqlOrderDAO.class)) {
            sqlOrderDAOMockedStatic.when(MySqlOrderDAO::getInstance).thenReturn(mySqlOrderMock);
            assertEquals(mySqlOrderMock, MySqlOrderDAO.getInstance());
        }
    }

    @Test
    void addItemToOrdersDb() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void findAllItemOfOrder() throws DBException, SQLException {
        List<Order> listWithAllOrder = new ArrayList<>();
        when(mySqlOrderMock.findAllItemOfOrder(mockCon)).thenReturn(listWithAllOrder);
        doThrow(new SQLException()).when(mockCon).close();
        assertEquals(listWithAllOrder, mySqlOrderMock.findAllItemOfOrder(mockCon));
    }

    @Test
    void testFindAllItemOfOrder() throws DBException, SQLException {
        List<Order> listWithAllOrder = new ArrayList<>();
        when(mySqlOrderMock.findAllItemOfOrder(mockCon)).thenReturn(listWithAllOrder);
        doThrow(new SQLException()).when(mockCon).close();
        assertEquals(listWithAllOrder, mySqlOrderMock.findAllItemOfOrder(mockCon));

    }

    @Test
    void findAllIItemOfOrderWithLimit() throws DBException, SQLException {
        List<Order> listWithAllOrderWithLimit = new ArrayList<>();
        when(mySqlOrderMock.findAllIItemOfOrderWithLimit(anyInt(),anyInt())).thenReturn(listWithAllOrderWithLimit);
        doThrow(new SQLException()).when(mockCon).close();
        assertEquals(listWithAllOrderWithLimit, mySqlOrderMock.findAllIItemOfOrderWithLimit(anyInt(),anyInt()));
    }

    @Test
    void findOrderById() throws DBException {
        when(mySqlOrderMock.findOrderById(anyInt())).thenReturn((orderMock));

    }

    @Test
    void testFindOrderById() throws SQLException, DBException {
        when(mySqlOrderMock.findOrderById(anyInt())).thenReturn(orderMock);
        doThrow(new SQLException()).when(mockCon).close();
        assertEquals(orderMock, mySqlOrderMock.findOrderById(anyInt()));
    }

    @Test
    void updateItemOrder() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void testUpdateItemOrder() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void extractOrder() throws SQLException {
        Order order = new Order();
        order.setId(mockResultSet.getInt(Fields.ENTITY_ID));
        order.setUserId(mockResultSet.getInt(Fields.USERS_ID));
        order.setCruiseId(mockResultSet.getInt(Fields.CRUISES_ID));
        order.setStatus(mockResultSet.getString(Fields.STATUS));
        when(mySqlOrderMock.extractOrder(mockResultSet)).thenReturn(order);

        assertEquals(0, orderMock.getId());
        assertEquals(0, orderMock.getUserId());
        assertEquals(0, orderMock.getCruiseId());
        assertNull(orderMock.getStatus());
        doThrow(new SQLException()).when(mockCon).close();


    }
}