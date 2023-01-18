package ua.nure.hrynko.dao;
import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.Querys;
import ua.nure.hrynko.dao.interfaces.OrderDAO;
import ua.nure.hrynko.models.Order;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlOrderDAO implements OrderDAO {

    private static final Logger LOG = Logger.getLogger(MySqlOrderDAO.class);

    private static MySqlOrderDAO instance;

    public static synchronized MySqlOrderDAO getInstance() {
        if (instance == null) {
            instance = new MySqlOrderDAO();
        }
        return instance;

    }


    @Override
    public void addItemToOrdersDb(Connection con, int usersId, int cruiseId, String status) throws SQLException {
        PreparedStatement pstmt;
        ResultSet rs = null;
        pstmt = con.prepareStatement(Querys.SQL_INSERT_TO_ORDERS);
        pstmt.setInt(1, usersId);
        pstmt.setInt(2, cruiseId);
        pstmt.setString(3, status);

        pstmt.executeUpdate();
        LOG.trace("add item to table usersHasCruises  succesful--> ");
        DBManager.close(rs);
        DBManager.close(pstmt);
    }


    @Override
    public List<Order> findAllItemOfOrder() throws DBException {
        List<Order> allItemOfOrdersList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_ITEM_ON_ORDERS);
            while (rs.next()) {
                allItemOfOrdersList.add(extractOrder(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all items orders", ex);
            throw new DBException("cannot select all items orders", ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return allItemOfOrdersList;
    }

    @Override
    public List<Order> findAllItemOfOrder(Connection con) throws DBException, SQLException {
        List<Order> allItemOfOrdersList = new ArrayList<>();
        Statement stmt;
        ResultSet rs;

            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_ITEM_ON_ORDERS);
            while (rs.next()) {
                allItemOfOrdersList.add(extractOrder(rs));
            }
        LOG.trace("find all item of table Orders succesful--> ");
        DBManager.close(rs);
        DBManager.close(stmt);
        return allItemOfOrdersList;
    }


    @Override
    public List<Order> findAllIItemOfOrderWithLimit(int start, int total) throws DBException {
        List<Order> listAllIItemOfUsersHasCruiseWithLimit = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_ALL_ITEM_ON_ORDERS_WITH_LIMIT);
            pstmt.setInt(1, start);
            pstmt.setInt(2, total);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                listAllIItemOfUsersHasCruiseWithLimit.add(extractOrder(rs));
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
    public Order findOrderById(int id) throws DBException {
        Order order = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_ORDER_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                order = extractOrder(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDER_BY_ID, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return order;
    }

    @Override
    public Order findOrderById(Connection con,int id) throws SQLException {
        Order order = null;
        PreparedStatement pstmt;
        ResultSet rs;

            pstmt = con.prepareStatement(Querys.SQL_FIND_ORDER_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                order = extractOrder(rs);
            }
        LOG.trace("find order by id  succesful--> ");
        DBManager.close(rs);
        DBManager.close(pstmt);
        return order;
    }
    @Override
    public void updateItemOrder(Connection con, Order itemOrder) throws SQLException {
        PreparedStatement pstmt;
        ResultSet rs = null;
        pstmt = con.prepareStatement(Querys.SQL_UPDATE_ORDERS_BY_OBJECT_OF_ORDERS);
        pstmt.setString(1,itemOrder.getStatus());
        pstmt.setInt(2, itemOrder.getId());
        pstmt.executeUpdate();
        DBManager.close(rs);
        DBManager.close(pstmt);
    }
    @Override
    public void updateItemOrder(Connection con, int id, String status) throws SQLException {
        PreparedStatement pstmt;
        ResultSet rs = null;
        pstmt = con.prepareStatement(Querys.SQL_UPDATE_ORDERS_BY_ID);
        pstmt.setString(1,status);
        pstmt.setInt(2,id);
        pstmt.executeUpdate();
        DBManager.close(rs);
        DBManager.close(pstmt);
    }

    @Override
    public Order extractOrder(ResultSet rs) throws SQLException {

        Order usersHasCruises = new Order();
        usersHasCruises.setId(rs.getInt(Fields.ENTITY_ID));
        usersHasCruises.setUserId(rs.getInt(Fields.USERS_ID));
        usersHasCruises.setCruiseId(rs.getInt(Fields.CRUISES_ID));
        usersHasCruises.setStatus(rs.getString(Fields.STATUS));


        return usersHasCruises;

    }
}

