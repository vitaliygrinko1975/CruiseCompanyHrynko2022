package ua.nure.hrynko.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.models.Role;
import ua.nure.hrynko.exception.DBException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MySqlRoleDAOTest {

    @Mock
    Connection mockCon;
    @Mock
    MySqlRoleDAO mySqlRoleMock;
    @Mock
    Role mockRole;
    @Mock
    ResultSet mockResultSet;


    @BeforeEach
    public void setUp() {
        mySqlRoleMock = mock(MySqlRoleDAO.class);
        mockRole = mock(Role.class);
        mockCon = mock(Connection.class);
        mockResultSet = mock(ResultSet.class);

    }

    @Test
    void getInstanceTest() {
        try (MockedStatic<MySqlRoleDAO> mySqlAccountsDAOMockedStatic = Mockito.mockStatic(MySqlRoleDAO.class)) {
            mySqlAccountsDAOMockedStatic.when(MySqlRoleDAO::getInstance).thenReturn(mySqlRoleMock);
            assertEquals(mySqlRoleMock,MySqlRoleDAO.getInstance());
        }
    }
    @Test
    void findAllRolesTest() throws SQLException, DBException {
        List<Role> allRoleList = new ArrayList<>();
        when(mySqlRoleMock.findAllRoles()).thenReturn(allRoleList);
        doThrow(new SQLException()).when(mockCon).close();
        assertEquals(allRoleList,mySqlRoleMock.findAllRoles());
    }

    @Test
    void extractRolesTest() throws SQLException {
        Role role = new Role();
        role.setId(mockResultSet.getInt(Fields.ENTITY_ID));
        role.setRoleName(mockResultSet.getString(Fields.ROLE_NAME));
        when(mySqlRoleMock.extractRoles(mockResultSet)).thenReturn(role);
        assertEquals(null,role.getRoleName());
        assertEquals(0,role.getId());
        doThrow(new SQLException()).when(mockCon).close();
    }
}