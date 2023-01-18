package ua.nure.hrynko.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.Querys;
import ua.nure.hrynko.dao.interfaces.OrderViewDAO;
import ua.nure.hrynko.models.OrderView;
import ua.nure.hrynko.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlOrderViewDAO implements OrderViewDAO {

    private static final Logger LOG = Logger.getLogger(MySqlOrderViewDAO.class);

    private static MySqlOrderViewDAO instance;

    public static synchronized MySqlOrderViewDAO getInstance() {
        if (instance == null) {
            instance = new MySqlOrderViewDAO();
        }
        return instance;

    }

    @Override
    public List<OrderView> findAllItemOfOrdersView() throws DBException {
        List<OrderView> allItemOfOrderViewList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_ITEM_ON_ORDERS_VIEW);
            while (rs.next()) {
                allItemOfOrderViewList.add(extractOrdersView(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all items orders view", ex);
            throw new DBException("cannot select all items orders view", ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return allItemOfOrderViewList;
    }
    @Override
    public List<OrderView> findAllIItemOfOrderViewByUserId(int userId) throws DBException {
        List<OrderView> listAllIItemOfOrderViewByUserId = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_ALL_ITEM_ON_ORDERS_VIEW_BY_USER_ID);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                listAllIItemOfOrderViewByUserId.add(extractOrdersView(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all items orders view by userId", ex);
            throw new DBException("cannot select all items orders view by userId", ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return listAllIItemOfOrderViewByUserId;
    }



    @Override
    public List<OrderView> findAllIItemOfOrderViewWithLimit(int start, int total) throws DBException {
        List<OrderView> listAllIItemOfOrderViewWithLimit = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_ALL_ITEM_ON_ORDERS_VIEW_WITH_LIMIT);
            pstmt.setInt(1, start);
            pstmt.setInt(2, total);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                listAllIItemOfOrderViewWithLimit.add(extractOrdersView(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all items orders view with limit ", ex);
            throw new DBException("cannot select all items orders view with limit", ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return listAllIItemOfOrderViewWithLimit;
    }

    @Override
    public List<OrderView> findAllIItemOfOrderViewByUserIdWithLimit(int id, int start, int total) throws DBException {
        List<OrderView> listAllIItemOfOrderViewWithLimit = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_ALL_ITEM_ON_ORDERS_VIEW_BY_USER_ID_WITH_LIMIT);
            pstmt.setInt(1, id);
            pstmt.setInt(2, start);
            pstmt.setInt(3, total);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                listAllIItemOfOrderViewWithLimit.add(extractOrdersView(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all items orders view with limit ", ex);
            throw new DBException("cannot select all items orders view with limit", ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return listAllIItemOfOrderViewWithLimit;
    }
    @Override
    public OrderView extractOrdersView(ResultSet rs) throws SQLException {

        OrderView orderView = new OrderView();
        orderView.setId(rs.getInt(Fields.ENTITY_ID));
        orderView.setUsersFirstName(rs.getString(Fields.USER_FIRST_NAME));
        orderView.setUsersLastName(rs.getString(Fields.USER_LAST_NAME));
        orderView.setUsersEmail(rs.getString(Fields.USER_EMAIL));
//        orderView.setId(rs.getInt(Fields.USERS_ID));
        orderView.setCruisesDescription(rs.getString(Fields.CRUISE_DESCRIPTION));
        orderView.setStatusOfCruises(rs.getString(Fields.STATUS_OF_CRUISES));
        orderView.setStatus(rs.getString(Fields.STATUS));


        return orderView;

    }
}

