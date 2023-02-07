package ua.nure.hrynko.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;

import ua.nure.hrynko.Fields;
import ua.nure.hrynko.Querys;

import ua.nure.hrynko.dao.interfaces.UserDAO;
import ua.nure.hrynko.models.OrderView;
import ua.nure.hrynko.models.User;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDAO implements UserDAO {
    private static final Logger LOG = Logger.getLogger(MySqlUserDAO.class);
    private static final String ERROR = "ERRor-->";

    private static MySqlUserDAO instance;

    public static synchronized MySqlUserDAO getInstance() {
        if (instance == null) {
            instance = new MySqlUserDAO();
        }
        return instance;
    }

    @Override
    public User findUserById(int id) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return user;
    }

    @Override
    public User findUserById(Connection con, int id) throws  SQLException {
        User user = null;
        PreparedStatement pstmt;
        ResultSet rs;
            pstmt = con.prepareStatement(Querys.SQL_FIND_USER_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
                DBManager.close(rs);
                DBManager.close(pstmt);
        }
        return user;
    }


    @Override
    public User findUserByLogin(String login) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() throws DBException {
        List<User> allUserList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_USER);
            while (rs.next()) {
                allUserList.add(extractUser(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all user", ex);
            throw new DBException("cannot select all user", ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return allUserList;
    }

    @Override
    public List<User> findAllItemOfUserWithLimit(int start, int total) throws DBException {
        List<User> listAllIItemOfUserWithLimit = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_ALL_ITEM_ON_USER_WITH_LIMIT);
            pstmt.setInt(1, start);
            pstmt.setInt(2, total);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                listAllIItemOfUserWithLimit.add(extractUser(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all items user with limit ", ex);
            throw new DBException("cannot select all items user with limit", ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return listAllIItemOfUserWithLimit;
    }



    // //////////////////////////////////////////////////////////
    // Entity access methods (for transactions)
    // //////////////////////////////////////////////////////////

    @Override
    public List<User> findAllUsers(Connection con) throws SQLException {
        List<User> allUserList = new ArrayList<>();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Querys.SQL_FIND_ALL_USER);
            while (rs.next()) {
                allUserList.add(extractUser(rs));
            }

            DBManager.close(rs);
            DBManager.close(stmt);

        return allUserList;
    }
    @Override
    public int countingTheNumberOfRecordsToUserDb() throws DBException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        int result;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_COUNT_ITEMS_IN_USER);
            rs.next();
            result = rs.getInt(1);
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select count all items user", ex);
            throw new DBException("cannot select count all items user", ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return result;
    }


    @Override
    public void addToUsersDb(String login, String password, String firstName, String lastName, String email, String phone,
                             boolean blocked, int roleId, int accountsId) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.prepareStatement(Querys.SQL_INSERT_USER);

            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, email);
            stmt.setString(6, phone);
            stmt.setInt(7, roleId);
            stmt.setInt(8, accountsId);
            stmt.executeUpdate();
            con.commit();

            LOG.trace("add user to SQL succesful--> ");
        } catch (SQLException ex) {
            LOG.trace(ERROR);
            ex.printStackTrace();
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, stmt, rs);
        }
    }

    @Override
    public void addToUsersDb(Connection con, String login, String password, String firstName, String lastName,
                             String email, String phone, int roleId, int accountsId) throws  SQLException {

        PreparedStatement stmt = con.prepareStatement(Querys.SQL_INSERT_USER);

        stmt.setString(1, login);
        stmt.setString(2, password);
        stmt.setString(3, firstName);
        stmt.setString(4, lastName);
        stmt.setString(5, email);
        stmt.setString(6, phone);
        stmt.setInt(7, roleId);
        stmt.setInt(8, accountsId);
        stmt.executeUpdate();


        LOG.trace("add user to SQL succesful--> ");

    }

    @Override
    public void removeUserFromDb(Integer id) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.prepareStatement(Querys.SQL_DELETE_USER_BY_ID);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            LOG.trace(ERROR + "when removed");
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, stmt, rs);
        }

    }

    @Override
    public void updateUserToDb(int id, String login, String password, String firstName, String lastName, String email,
                               String phone, int roleId, int accountsId) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.prepareStatement(Querys.SQL_UPDATE_USERS_BY_ID);
            stmt.setString(1, login);
            stmt.setString(2, password);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, email);
            stmt.setString(6, phone);
            stmt.setInt(7, roleId);
            stmt.setInt(8, accountsId);
            stmt.setInt(9, id);
            stmt.executeUpdate();
            con.commit();
            LOG.trace("update to SQL seccesful--> ");
        } catch (SQLException ex) {
            LOG.trace(ERROR);
            ex.printStackTrace();
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, stmt, rs);
        }
    }
    @Override
    public void updateUserToDb(int id, String firstName, String lastName, String email,
                               String phone) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_UPDATE_FOUR_PARAMETERS_OF_USERS_BY_ID);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, phone);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            con.commit();
            LOG.trace("update to SQL seccesful--> ");
        } catch (SQLException ex) {
            LOG.trace(ERROR);
            ex.printStackTrace();
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
    }

    @Override
    public void updateUserToDb(User user) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.prepareStatement(Querys.SQL_UPDATE_USERS_BY_ID);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getLastName());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPhone());
            stmt.setInt(7, user.getRoleId());
            stmt.setInt(8, user.getAccountsId());
            stmt.setInt(9, user.getId());
            stmt.executeUpdate();
            con.commit();
            LOG.trace("update to SQL seccesful--> ");
        } catch (SQLException ex) {
            LOG.trace(ERROR);
            ex.printStackTrace();
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, stmt, rs);
        }
    }

    /**
     * Extracts a user entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return User entity
     */

    @Override
    public User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setEmail(rs.getString(Fields.USER_EMAIL));
        user.setPhone(rs.getString(Fields.USER_PHONE));
        user.setRoleId(rs.getInt(Fields.USER_ROLES_ID));
        user.setAccountsId(rs.getInt(Fields.ACCOUNTS_ID));
        return user;
    }

}