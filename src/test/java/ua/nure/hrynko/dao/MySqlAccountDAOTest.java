package ua.nure.hrynko.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MySqlAccountDAOTest {

    @Mock
    Connection mockCon;
    @Mock
    MySqlAccountDAO mySqlAccountMock;
    @Mock
    Account accountsMock;
    @Mock
    ResultSet mockResultSet;

    @BeforeEach
    public void setUp() {
        mySqlAccountMock = mock(MySqlAccountDAO.class);
        accountsMock = mock(Account.class);
        mockCon = mock(Connection.class);
        mockResultSet = mock(ResultSet.class);
    }

    @Test
    void getInstance() {
        try (MockedStatic<MySqlAccountDAO> mySqlAccountsDAOMockedStatic = Mockito.mockStatic(MySqlAccountDAO.class)) {
            mySqlAccountsDAOMockedStatic.when(MySqlAccountDAO::getInstance).thenReturn(mySqlAccountMock);
            assertEquals(mySqlAccountMock, MySqlAccountDAO.getInstance());
        }
    }

    @Test
    void findAllAccounts() throws DBException, SQLException {
        List<Account> allAccountsList = Arrays.asList(accountsMock);
        when(mySqlAccountMock.findAllAccounts()).thenReturn(allAccountsList);
        assertEquals(allAccountsList, mySqlAccountMock.findAllAccounts());
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void testFindAllAccounts() throws  SQLException {
        List<Account> allAccountsList = Arrays.asList(accountsMock);
        when(mySqlAccountMock.findAllAccounts(mockCon)).thenReturn(allAccountsList);
        assertEquals(allAccountsList, mySqlAccountMock.findAllAccounts(mockCon));
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void findAccountById() throws DBException, SQLException {
        when(mySqlAccountMock.findAccountById(anyInt())).thenReturn(accountsMock);
        assertEquals(accountsMock, mySqlAccountMock.findAccountById(anyInt()));
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void removeAccountFromDb() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void updateAccountToDb() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void testUpdateAccountToDb() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void testUpdateAccountToDb1() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void addAccountsDb() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void extractAccount() throws SQLException {
        Account accounts = new Account();
        accounts.setId(mockResultSet.getInt(Fields.ENTITY_ID));
        accounts.setBalance(mockResultSet.getDouble(Fields.ACCOUNTS_BALANCE));
        when(mySqlAccountMock.extractAccount(mockResultSet)).thenReturn(accounts);
        assertEquals(0, accounts.getId());
        assertEquals(0, accounts.getBalance());
        doThrow(new SQLException()).when(mockCon).close();
    }
}