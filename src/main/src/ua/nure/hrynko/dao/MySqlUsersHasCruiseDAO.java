package ua.nure.hrynko.dao;
import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.Querys;
import ua.nure.hrynko.dao.interfaces.UserHasCruiseDAO;
import ua.nure.hrynko.dto.UserHasCruise;
import ua.nure.hrynko.exception.DBException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUsersHasCruiseDAO implements UserHasCruiseDAO {

    private static final Logger LOG = Logger.getLogger(MySqlUsersHasCruiseDAO.class);

    private static MySqlUsersHasCruiseDAO instance;

    public static synchronized MySqlUsersHasCruiseDAO getInstance() {
        if (instance == null) {
            instance = new MySqlUsersHasCruiseDAO();
        }
        return instance;

    }


    @Override
    public void addItemToUsersHasCruisesDb(Connection con, int usersId, int cruiseId, String status) throws SQLException {
        PreparedStatement pstmt;
        ResultSet rs = null;
        pstmt = con.prepareStatement(Querys.SQL_INSERT_USERS_HAS_CRUISES);
        pstmt.setInt(1, usersId);
        pstmt.setInt(2, cruiseId);
        pstmt.setString(3, status);

        pstmt.executeUpdate();
        LOG.trace("add item to table usersHasCruises  succesful--> ");
        DBManager.close(rs);
        DBManager.close(pstmt);
    }

    @Override
    public List<UserHasCruise> findAllItemOfUsersHasCruises() throws DBException {
        List<UserHasCruise> allItemOfUsersHasCruisesList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_ITEM_ON_USERS_HAS_CRUISES);
            while (rs.next()) {
                allItemOfUsersHasCruisesList.add(extractUsersHasCruise(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all items users has cruises", ex);
            throw new DBException("cannot select all items users has cruises", ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return allItemOfUsersHasCruisesList;
    }



    @Override
    public List<UserHasCruise> findAllIItemOfUsersHasCruiseWithLimit(int start, int total) throws DBException {
        List<UserHasCruise> listAllIItemOfUsersHasCruiseWithLimit = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_ALL_ITEM_ON_USERS_HAS_CRUISES_WITH_LIMIT);
            pstmt.setInt(1, start);
            pstmt.setInt(2, total);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                listAllIItemOfUsersHasCruiseWithLimit.add(extractUsersHasCruise(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all items users has cruises with limit ", ex);
            throw new DBException("cannot select all items users has cruises with limit", ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return listAllIItemOfUsersHasCruiseWithLimit;
    }


    @Override
    public UserHasCruise extractUsersHasCruise(ResultSet rs) throws SQLException {

        UserHasCruise usersHasCruises = new UserHasCruise();
        usersHasCruises.setUserId(rs.getInt(Fields.USERS_ID));
        usersHasCruises.setCruiseId(rs.getInt(Fields.CRUISES_ID));
        usersHasCruises.setStatus(rs.getString(Fields.STATUS));


        return usersHasCruises;

    }
}

