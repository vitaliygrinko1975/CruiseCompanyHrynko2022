package ua.nure.hrynko.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.Account;
import ua.nure.hrynko.models.Cruise;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class MySqlCruiseDAOTest {


    @Mock
    Connection mockCon;
    @Mock
    MySqlCruiseDAO mySqlCruiseMock;
    @Mock
    Cruise cruiseMock;
    @Mock
    ResultSet mockResultSet;

    @BeforeEach
    public void setUp() {
        mySqlCruiseMock = mock(MySqlCruiseDAO.class);
        cruiseMock = mock(Cruise.class);
        mockCon = mock(Connection.class);
        mockResultSet = mock(ResultSet.class);
    }

    @Test
    void getInstance() {
        try (MockedStatic<MySqlCruiseDAO> mySqlCruiseDAOMockedStatic = Mockito.mockStatic(MySqlCruiseDAO.class)) {
            mySqlCruiseDAOMockedStatic.when(MySqlCruiseDAO::getInstance).thenReturn(mySqlCruiseMock);
            assertEquals(mySqlCruiseMock, MySqlCruiseDAO.getInstance());
        }
    }
    @Test
    void findAllCruises() throws DBException, SQLException {
        List<Cruise> allAccountsList = Arrays.asList(cruiseMock);
        when(mySqlCruiseMock.findAllCruises()).thenReturn(allAccountsList);
        assertEquals(allAccountsList, mySqlCruiseMock.findAllCruises());
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void testFindAllCruises() throws SQLException {
        List<Cruise> allCruisesList = Arrays.asList(cruiseMock);
        when(mySqlCruiseMock.findAllCruises(mockCon)).thenReturn(allCruisesList);
        assertEquals(allCruisesList, mySqlCruiseMock.findAllCruises(mockCon));
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void findCruiseById() throws DBException, SQLException {
        when(mySqlCruiseMock.findCruiseById(anyInt())).thenReturn(cruiseMock);
        assertEquals(cruiseMock, mySqlCruiseMock.findCruiseById(anyInt()));
        doThrow(new SQLException()).when(mockCon).close();
    }



    @Test
    void findCruiseByStartOfCruise() throws DBException, SQLException {
        List<Cruise> allCruisesList = Arrays.asList(cruiseMock);
        when(mySqlCruiseMock.findCruiseByStartOfCruise(anyString())).thenReturn(allCruisesList);
        assertEquals(allCruisesList, mySqlCruiseMock.findCruiseByStartOfCruise(anyString()));
        doThrow(new SQLException()).when(mockCon).close();

    }

    @Test
    void findCruiseByDuration() throws DBException, SQLException {
        List<Cruise> allCruisesList = Arrays.asList(cruiseMock);
        when(mySqlCruiseMock.findCruiseByDuration(anyInt())).thenReturn(allCruisesList);
        assertEquals(allCruisesList, mySqlCruiseMock.findCruiseByDuration(anyInt()));
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void updateCruisesDb() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void updateCruiseDb() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void addToCruiseDb() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void removeCruiseFromDb() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void extractCruises() throws SQLException {
        Cruise cruise = new Cruise();
        cruise.setId(mockResultSet.getInt(Fields.ENTITY_ID));
        cruise.setName(mockResultSet.getString(Fields.CRUISE_NAME));
        cruise.setDescription(mockResultSet.getString(Fields.CRUISE_DESCRIPTION));
        cruise.setPrice(mockResultSet.getDouble(Fields.CRUISE_PRICE));
        cruise.setCapacity(mockResultSet.getInt(Fields.CRUISE_CAPACITY));
        cruise.setStartOfCruise(mockResultSet.getTimestamp(Fields.CRUISE_START_OF_CRUISE));
        cruise.setDuration(mockResultSet.getInt(Fields.CRUISE_DURATION));
        cruise.setStatus(mockResultSet.getString(Fields.CRUISE_STATUS));
        when(mySqlCruiseMock.extractCruises(mockResultSet)).thenReturn(cruise);
        assertEquals(0, cruise.getId());
        assertEquals(0, cruise.getId());
        assertEquals(0, cruise.getPrice());
        assertEquals(0, cruise.getDuration());
        doThrow(new SQLException()).when(mockCon).close();
    }
}