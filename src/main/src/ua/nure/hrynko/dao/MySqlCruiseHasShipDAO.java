package ua.nure.hrynko.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.Querys;
import ua.nure.hrynko.dao.interfaces.CruiseHasShipDAO;
import ua.nure.hrynko.dao.interfaces.OrderDAO;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.exception.Messages;
import ua.nure.hrynko.models.CruiseHasShip;
import ua.nure.hrynko.models.Order;

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
    public void addItemToCruiseHasShipDb(int cruiseId,int shipId, String startOfContract,
                                         String endOfContract, String status) throws DBException {
        PreparedStatement pstmt=null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
        pstmt = con.prepareStatement(Querys.SQL_INSERT_TO_CRUISES_HAS_SHIPS);
        pstmt.setInt(1, cruiseId);
        pstmt.setInt(2, cruiseId);
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
//
//    @Override
//    public List<Order> findAllItemOfOrder(Connection con) throws DBException, SQLException {
//        List<Order> allItemOfOrdersList = new ArrayList<>();
//        Statement stmt;
//        ResultSet rs;
//
//            con = DBManager.getInstance().getConnection();
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_ITEM_ON_ORDERS);
//            while (rs.next()) {
//                allItemOfOrdersList.add(extractOrder(rs));
//            }
//        LOG.trace("find all item of table Orders succesful--> ");
//        DBManager.close(rs);
//        DBManager.close(stmt);
//        return allItemOfOrdersList;
//    }
//
//
//    @Override
//    public List<Order> findAllIItemOfOrderWithLimit(int start, int total) throws DBException {
//        List<Order> listAllIItemOfUsersHasCruiseWithLimit = new ArrayList<>();
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        try {
//            con = DBManager.getInstance().getConnection();
//            pstmt = con.prepareStatement(Querys.SQL_FIND_ALL_ITEM_ON_ORDERS_WITH_LIMIT);
//            pstmt.setInt(1, start);
//            pstmt.setInt(2, total);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                listAllIItemOfUsersHasCruiseWithLimit.add(extractOrder(rs));
//            }
//            con.commit();
//        } catch (SQLException ex) {
//            DBManager.rollback(con);
//            LOG.error("cannot select all items users has cruises with limit ", ex);
//            throw new DBException("cannot select all items users has cruises with limit", ex);
//        } finally {
//            DBManager.close(con, pstmt, rs);
//        }
//        return listAllIItemOfUsersHasCruiseWithLimit;
//    }
//    @Override
//    public Order findOrderById(int id) throws DBException {
//        Order order = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        try {
//            con = DBManager.getInstance().getConnection();
//            pstmt = con.prepareStatement(Querys.SQL_FIND_ORDER_BY_ID);
//            pstmt.setInt(1, id);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                order = extractOrder(rs);
//            }
//            con.commit();
//        } catch (SQLException ex) {
//            DBManager.rollback(con);
//            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDER_BY_ID, ex);
//        } finally {
//            DBManager.close(con, pstmt, rs);
//        }
//        return order;
//    }
//
//    @Override
//    public Order findOrderById(Connection con,int id) throws SQLException {
//        Order order = null;
//        PreparedStatement pstmt;
//        ResultSet rs;
//
//            pstmt = con.prepareStatement(Querys.SQL_FIND_ORDER_BY_ID);
//            pstmt.setInt(1, id);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                order = extractOrder(rs);
//            }
//        LOG.trace("find order by id  succesful--> ");
//        DBManager.close(rs);
//        DBManager.close(pstmt);
//        return order;
//    }
//    @Override
//    public void updateItemOrder(Connection con, Order itemOrder) throws SQLException {
//        PreparedStatement pstmt;
//        ResultSet rs = null;
//        pstmt = con.prepareStatement(Querys.SQL_UPDATE_ORDERS_BY_OBJECT_OF_ORDERS);
//        pstmt.setString(1,itemOrder.getStatus());
//        pstmt.setInt(2, itemOrder.getId());
//        pstmt.executeUpdate();
//        DBManager.close(rs);
//        DBManager.close(pstmt);
//    }
//    @Override
//    public void updateItemOrder(Connection con, int id, String status) throws SQLException {
//        PreparedStatement pstmt;
//        ResultSet rs = null;
//        pstmt = con.prepareStatement(Querys.SQL_UPDATE_ORDERS_BY_ID);
//        pstmt.setString(1,status);
//        pstmt.setInt(2,id);
//        pstmt.executeUpdate();
//        DBManager.close(rs);
//        DBManager.close(pstmt);
//    }

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

