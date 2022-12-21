package ua.nure.hrynko.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.Querys;
import ua.nure.hrynko.dao.interfaces.RoleDAO;

import ua.nure.hrynko.dto.Role;

import ua.nure.hrynko.exception.DBException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlRoleDAO implements RoleDAO {
    private static final Logger LOG = Logger.getLogger(MySqlRoleDAO.class);


    private static MySqlRoleDAO instance;

    public static synchronized MySqlRoleDAO getInstance() {
        if (instance == null) {
            instance = new MySqlRoleDAO();
        }
        return instance;
    }

    @Override
    public List<Role> findAllRoles() throws DBException {
        List<Role> allRoleList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_ROLE);
            while (rs.next()) {
                allRoleList.add(extractRoles(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all role", ex);
            throw new DBException("cannot select all role", ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return allRoleList;
    }

    // //////////////////////////////////////////////////////////
    // Entity access methods (for transactions)
    // //////////////////////////////////////////////////////////


    /**
     * Extracts a role entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return Role entity
     */
    @Override
    public Role extractRoles(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt(Fields.ENTITY_ID));
        role.setRoleName(rs.getString(Fields.ROLE_NAME));

        return role;
    }

}