package ua.nure.hrynko.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.nure.hrynko.dao.MySqlOrderViewDAO;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.OrderView;
import ua.nure.hrynko.models.User;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class PaginationsTest {

    @Mock
    Paginations paginationMock;
    @Mock
    MySqlOrderViewDAO mySqlOrderViewMock;
    @Mock
    OrderView orderViewMock;
    @Mock
    User userMock;

    @BeforeEach
    public void setUp() {
        paginationMock = mock(Paginations.class);
        mySqlOrderViewMock = mock(MySqlOrderViewDAO.class);
        orderViewMock = mock(OrderView.class);
        userMock = mock(User.class);
    }

    @Test
    void makePaginationForOrders() throws DBException {
        List<OrderView> allItemOfOrdersViewWithLimit = Arrays.asList(orderViewMock);
        when(paginationMock.makePaginationForOrders(anyInt())).thenReturn(allItemOfOrdersViewWithLimit);
        assertEquals(allItemOfOrdersViewWithLimit, paginationMock.makePaginationForOrders(anyInt()));
    }

    @Test
    void makePaginationForMyOrders() throws DBException {
        List<OrderView> allItemOfOrdersViewWithLimit = Arrays.asList(orderViewMock);
        when(paginationMock.makePaginationForMyOrders(anyInt(),anyInt())).thenReturn(allItemOfOrdersViewWithLimit);
        assertEquals(allItemOfOrdersViewWithLimit, paginationMock.makePaginationForMyOrders(anyInt(),anyInt()));
    }

    @Test
    void makePaginationForUsers() throws DBException {
        List<User> allItemOfUserWithLimit = Arrays.asList(userMock);
        when(paginationMock.makePaginationForUsers(anyInt())).thenReturn(allItemOfUserWithLimit);
        assertEquals(allItemOfUserWithLimit, paginationMock.makePaginationForUsers(anyInt()));
    }
}