package ua.nure.hrynko.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Querys;
import ua.nure.hrynko.dao.interfaces.UserHasCruiseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


//    @Override
//    public List<UsersHasCruise> findItemOnUsersHasTariffsByUserId(int id) throws DBException {
//        List<UsersHasCruise> listWithCurrentTariffsForThisUserId = new ArrayList<>();
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        try {
//            con = DBManager.getInstance().getConnection();
//            pstmt = con.prepareStatement(Querys.SQL_FIND_ITEM_ON_USER_HAS_TARIFFS_BY_USER_ID);
//            pstmt.setInt(1, id);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                listWithCurrentTariffsForThisUserId.add(extractUsersHasTariffs(rs));
//            }
//            con.commit();
//        } catch (SQLException ex) {
//            DBManager.rollback(con);
//            throw new DBException(Messages.ERR_CANNOT_OBTAIN_TARIFFS_ITEMS, ex);
//        } finally {
//            DBManager.close(con, pstmt, rs);
//        }
//        return listWithCurrentTariffsForThisUserId;


//    @Override
//    public List<UsersHasCruise> findItemOnUsersHasTariffsByUserId(Connection con, int id) throws SQLException {
//        List<UsersHasCruise> listWithCurrentTariffsForThisUserId = new ArrayList<>();
//        PreparedStatement pstmt;
//        ResultSet rs;
//
//        pstmt = con.prepareStatement(Querys.SQL_FIND_ITEM_ON_USER_HAS_TARIFFS_BY_USER_ID);
//        pstmt.setInt(1, id);
//        rs = pstmt.executeQuery();
//        while (rs.next()) {
//            listWithCurrentTariffsForThisUserId.add(extractUsersHasTariffs(rs));
//        }
//        DBManager.close(rs);
//        DBManager.close(pstmt);
//        return listWithCurrentTariffsForThisUserId;
//    }


//    public List<UsersHasCruise> findAllItemsOnUsersHasTariffs(Connection con) throws SQLException {
//        List<UsersHasCruise> listWithCurrentTariffsForAllUsers = new ArrayList<>();
//        Statement stmt;
//        ResultSet rs;
//        stmt = con.createStatement();
//        rs = stmt.executeQuery(Querys.SQL_FIND_ALL_ITEM_ON_USERS_HAS_TARIFFS);
//        while (rs.next()) {
//            listWithCurrentTariffsForAllUsers.add(extractUsersHasTariffs(rs));
//        }
//        DBManager.close(rs);
//        DBManager.close(stmt);
//        return listWithCurrentTariffsForAllUsers;
//    }
//
//
//
//    @Override
//    public void removeItemFromDb(Connection con, int userId, int tariffId) throws SQLException {
//        PreparedStatement pstmt;
//        ResultSet rs = null;
//        pstmt = con.prepareStatement(Querys.SQL_DELETE_USER_BY_USER_ID_AND_TARIFF_ID);
//        pstmt.setInt(1, userId);
//        pstmt.setInt(2, tariffId);
//        pstmt.executeUpdate();
//        DBManager.close(rs);
//        DBManager.close(pstmt);
//
//    }

//    public void updateItemOfUsersHasTariffs(Connection con, UsersHasCruise itemUsersHasTariffs) throws SQLException {
//        PreparedStatement pstmt;
//        ResultSet rs = null;
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//        String stringDate = formatter.format(itemUsersHasTariffs.getEndOfContract());
//            pstmt = con.prepareStatement(Querys.SQL_UPDATE_USERS_HAS_TARIFFS_BY_USERS_ID_AND_TARIFFS_ID);
//            pstmt.setInt(1,itemUsersHasTariffs.getUserId());
//            pstmt.setInt(2, itemUsersHasTariffs.getTariffId());
//            pstmt.setString(3, stringDate);
//            pstmt.setInt(4, itemUsersHasTariffs.getUserId());
//            pstmt.setInt(5, itemUsersHasTariffs.getTariffId());
//        pstmt.executeUpdate();
//        DBManager.close(rs);
//        DBManager.close(pstmt);
//    }


//    @Override
//    public UsersHasCruise extractUsersHasTariffs(ResultSet rs) throws SQLException {
//
//        UsersHasCruise usersHasTariffs = new UsersHasCruise();
//        usersHasTariffs.setUserId(rs.getInt(Fields.USERS_ID));
//        usersHasTariffs.setTariffId(rs.getInt(Fields.TARIFFS_ID));
//        usersHasTariffs.setEndOfContract(rs.getTimestamp(Fields.END_OF_CONTRACT));
//
//
//        return usersHasTariffs;
//
//    }
}

