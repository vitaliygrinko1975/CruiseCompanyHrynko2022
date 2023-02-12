package ua.nure.hrynko.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.Querys;
import ua.nure.hrynko.dao.interfaces.ShipDAO;
import ua.nure.hrynko.exception.Messages;
import ua.nure.hrynko.models.Ship;
import ua.nure.hrynko.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlShipDAO implements ShipDAO {
    private static final Logger LOG = Logger.getLogger(MySqlShipDAO.class);

    private static MySqlShipDAO instance;

    public static synchronized MySqlShipDAO getInstance() {
        if (instance == null) {
            instance = new MySqlShipDAO();
        }
        return instance;

    }

    @Override
    public List<Ship> findAllShips() throws DBException {
        List<Ship> shipList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_SHIPS);
            while (rs.next()) {
                shipList.add(extractShipView(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all ships", ex);
            throw new DBException("cannot select all ships", ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return shipList;
    }


    @Override
    public Ship findShipById(int id) throws DBException {
        Ship ship = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_SHIP_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ship = extractShipView(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_SHIP_BY_ID, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return ship;
    }

    @Override
    public void addToShipDb(String name, String description, int capacity) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_INSERT_SHIP);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setInt(3, capacity);
            pstmt.executeUpdate();
            con.commit();
            LOG.trace("add ship to SQL succesful--> ");
        } catch (SQLException ex) {
            LOG.trace("ERRor--> ");
            ex.printStackTrace();
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
    }

    @Override
    public void updateShipDb(int id, String name, String description, int capacity) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_UPDATE_SHIP_BY_ID);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setInt(3, capacity);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            con.commit();
            LOG.trace("update to SQL seccesful--> ");
        } catch (SQLException ex) {
            LOG.trace("ERRor--> ");
            ex.printStackTrace();
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
    }

    @Override
    public void removeShipFromDb(int id) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_DELETE_SHIP_BY_ID);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            con.commit();
            LOG.trace("Remove to SQL seccesful--> ");
        } catch (SQLException ex) {
            LOG.trace("ERRor--> ");
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
    }

    @Override
    public Ship extractShipView(ResultSet rs) throws SQLException {

        Ship ship = new Ship();
        ship.setId(rs.getInt(Fields.ENTITY_ID));
        ship.setName(rs.getString(Fields.SHIPS_NAME));
        ship.setDescription(rs.getString(Fields.SHIPS_DESCRIPTION));
        ship.setCapacity(rs.getInt(Fields.SHIPS_CAPACITY));

        return ship;

    }
}