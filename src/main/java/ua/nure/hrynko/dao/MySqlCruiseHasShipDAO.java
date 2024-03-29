package ua.nure.hrynko.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.Querys;
import ua.nure.hrynko.dao.interfaces.CruiseHasShipDAO;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.CruiseHasShip;;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MySqlCruiseHasShipDAO implements CruiseHasShipDAO {

    private static final Logger LOG = Logger.getLogger(MySqlCruiseHasShipDAO.class);

    private static MySqlCruiseHasShipDAO instance;

    public static synchronized MySqlCruiseHasShipDAO getInstance() {
        if (instance == null) {
            instance = new MySqlCruiseHasShipDAO();
        }
        return instance;

    }

    @Override
    public void addItemToCruiseHasShipDb(Connection con, int cruiseId, int shipId, String startOfContract,
                                         String endOfContract, String status) throws SQLException {
        PreparedStatement pstmt;
        ResultSet rs = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String stringStartOfContract = formatter.format(startOfContract);
        String stringEndOfContract = formatter.format(endOfContract);

        pstmt = con.prepareStatement(Querys.SQL_INSERT_TO_CRUISES_HAS_SHIPS);
        pstmt.setInt(1, cruiseId);
        pstmt.setInt(2, cruiseId);
        pstmt.setString(3, stringStartOfContract);
        pstmt.setString(4, stringEndOfContract);
        pstmt.setString(5, status);

        pstmt.executeUpdate();
        LOG.trace("add item to table usersHasCruises  succesful--> ");
        DBManager.close(rs);
        DBManager.close(pstmt);
    }

    @Override
    public void addItemToCruiseHasShipDb(int cruiseId, int shipId, String startOfContract,
                                         String endOfContract, String status) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_INSERT_TO_CRUISES_HAS_SHIPS);
            pstmt.setInt(1, cruiseId);
            pstmt.setInt(2, shipId);
            pstmt.setString(3, startOfContract);
            pstmt.setString(4, endOfContract);
            pstmt.setString(5, status);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot add items to cruise_Has_Ship  DB", ex);
            throw new DBException("cannot add items to cruise_Has_Ship  DB", ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
    }


    @Override
    public List<CruiseHasShip> findAllItemsOfCruiseHasShip() throws DBException {
        List<CruiseHasShip> allItemOfCruiseHasShipList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_ITEM_OF_CRUISE_HAS_SHIPS);
            while (rs.next()) {
                allItemOfCruiseHasShipList.add(extractOrder(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all items orders", ex);
            throw new DBException("cannot select all items orders", ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return allItemOfCruiseHasShipList;
    }

    @Override
    public int countingTheNumberOfRecordsToCruiseHasShipDb(String startOfContract) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int result;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_COUNT_ITEMS_IN_CRUISE_HAS_SHIPS_BY_START_OF_CONTRACT);
            pstmt.setString(1, startOfContract);
            rs = pstmt.executeQuery();
            rs.next();
            result = rs.getInt(1);
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select count all items cruises_has_ships", ex);
            throw new DBException("cannot select count all items cruises_has_ships", ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return result;
    }

    @Override
    public void updateCruiseHasShipDb(Connection con, CruiseHasShip cruiseHasShip) throws SQLException {
        PreparedStatement pstmt;
        ResultSet rs = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String stringStartOfContract = formatter.format(cruiseHasShip.getStartOfContract());
        String stringEndOfContract = formatter.format(cruiseHasShip.getEndOfContract());

        pstmt = con.prepareStatement(Querys.SQL_UPDATE_CRUISE_HAS_SHIP_BY_ID);
        pstmt.setInt(1, cruiseHasShip.getCruiseId());
        pstmt.setInt(2, cruiseHasShip.getShipId());
        pstmt.setString(3, stringStartOfContract);
        pstmt.setString(4, stringEndOfContract);
        pstmt.setString(5, cruiseHasShip.getStatus());
        pstmt.setInt(6, cruiseHasShip.getId());

        pstmt.executeUpdate();

        DBManager.close(rs, pstmt);

    }
    @Override
    public void removeCruiseHasShipFromDb(int id) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_DELETE_CRUISES_HAS_SHIPS_BY_ID);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
    }


    @Override
    public CruiseHasShip extractOrder(ResultSet rs) throws SQLException {
        CruiseHasShip cruiseHasShip = new CruiseHasShip();
        cruiseHasShip.setId(rs.getInt(Fields.ENTITY_ID));
        cruiseHasShip.setCruiseId(rs.getInt(Fields.CRUISES_ID));
        cruiseHasShip.setShipId(rs.getInt(Fields.SHIPS_ID));
        cruiseHasShip.setStartOfContract(rs.getTimestamp(Fields.START_OF_CONTRACT));
        cruiseHasShip.setEndOfContract(rs.getTimestamp(Fields.END_OF_CONTRACT));
        cruiseHasShip.setStatus(rs.getString(Fields.STATUS));

        return cruiseHasShip;

    }
}

