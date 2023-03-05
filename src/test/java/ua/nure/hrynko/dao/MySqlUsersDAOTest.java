package ua.nure.hrynko.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.models.User;
import ua.nure.hrynko.exception.DBException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MySqlUsersDAOTest {

    @Mock
    Connection mockCon;
    @Mock
    MySqlUserDAO mySqlUsersMock;
    @Mock
    User userMock;
    @Mock
    ResultSet mockResultSet;


    @BeforeEach
    public void setUp() {
        mySqlUsersMock = mock(MySqlUserDAO.class);
        userMock = mock(User.class);
        mockCon = mock(Connection.class);
        mockResultSet = mock(ResultSet.class);
    }

    @Test
    void getInstance() {
        try (MockedStatic<MySqlUserDAO> mySqlUsersDAOMockedStatic = Mockito.mockStatic(MySqlUserDAO.class)) {
            mySqlUsersDAOMockedStatic.when(MySqlUserDAO::getInstance).thenReturn(mySqlUsersMock);
            assertEquals(mySqlUsersMock, MySqlUserDAO.getInstance());
        }
    }

    @Test
    void findUserById() throws DBException, SQLException {
        when(mySqlUsersMock.findUserById(anyInt())).thenReturn(userMock);
        assertEquals(userMock, mySqlUsersMock.findUserById(anyInt()));
        doThrow(new SQLException()).when(mockCon).close();
    }


    @Test
    void findUserByLoginTest() throws DBException, SQLException {
        when(mySqlUsersMock.findUserByLogin(anyString())).thenReturn(userMock);
        assertEquals(userMock, mySqlUsersMock.findUserByLogin(anyString()));
        doThrow(new SQLException()).when(mockCon).close();

    }

    @Test
    void findAllUsers() throws DBException, SQLException {
        List<User> allUserList = new ArrayList<>();
        when(mySqlUsersMock.findAllUsers()).thenReturn(allUserList);
        doThrow(new SQLException()).when(mockCon).close();
        assertEquals(allUserList, mySqlUsersMock.findAllUsers());
    }

    @Test
    void testFindAllUsers() throws SQLException {
        List<User> allUserList = new ArrayList<>();
        when(mySqlUsersMock.findAllUsers(mockCon)).thenReturn(allUserList);
        doThrow(new SQLException()).when(mockCon).close();
        assertEquals(allUserList, mySqlUsersMock.findAllUsers(mockCon));
    }

    @Test
    void removeUserFromDb() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }


    @Test
    void updateUserToDb() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void testUpdateUserToDb() throws SQLException {
        doThrow(new SQLException()).when(mockCon).close();
    }

    @Test
    void extractUser() throws SQLException {
        User user = new User();
        user.setId(mockResultSet.getInt(Fields.ENTITY_ID));
        user.setLogin(mockResultSet.getString(Fields.USER_LOGIN));
        user.setPassword(mockResultSet.getString(Fields.USER_PASSWORD));
        user.setFirstName(mockResultSet.getString(Fields.USER_FIRST_NAME));
        user.setLastName(mockResultSet.getString(Fields.USER_LAST_NAME));
        user.setEmail(mockResultSet.getString(Fields.USER_EMAIL));
        user.setPhone(mockResultSet.getString(Fields.USER_PHONE));
        user.setRoleId(mockResultSet.getInt(Fields.USER_ROLES_ID));
        user.setAccountsId(mockResultSet.getInt(Fields.ACCOUNTS_ID));
        when(mySqlUsersMock.extractUser(mockResultSet)).thenReturn(user);
        assertEquals(0, user.getId());
        assertEquals(null, user.getLogin());
        assertEquals(null, user.getPassword());
        assertEquals(null, user.getFirstName());
        assertEquals(null, user.getLastName());
        assertEquals(null, user.getEmail());
        assertEquals(null, user.getEmail());
        assertEquals(0, user.getRoleId());
        assertEquals(0, user.getAccountsId());
        doThrow(new SQLException()).when(mockCon).close();

    }
}
