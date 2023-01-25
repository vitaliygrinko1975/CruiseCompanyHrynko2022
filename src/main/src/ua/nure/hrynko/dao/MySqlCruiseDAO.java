package ua.nure.hrynko.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.Querys;
import ua.nure.hrynko.dao.interfaces.CruiseDAO;
import ua.nure.hrynko.models.Cruise;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.exception.Messages;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class MySqlCruiseDAO implements CruiseDAO {
    private static final Logger LOG = Logger.getLogger(MySqlCruiseDAO.class);

    private static MySqlCruiseDAO instance;

    public static synchronized MySqlCruiseDAO getInstance() {
        if (instance == null) {
            instance = new MySqlCruiseDAO();
        }
        return instance;
    }

    @Override
    public List<Cruise> findAllCruises() throws DBException {
        List<Cruise> allCruisesList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_CRUISES);
            while (rs.next()) {
                allCruisesList.add(extractCruises(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all cruises", ex);
            throw new DBException("cannot select all cruises", ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return allCruisesList;
    }

    @Override
    public List<Cruise> findAllCruises(Connection con) throws  SQLException {
        List<Cruise> allCruisesList = new ArrayList<>();
        Statement stmt;
        ResultSet rs;

            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_CRUISES);
            while (rs.next()) {
                allCruisesList.add(extractCruises(rs));
            }
            LOG.trace("find all item of table Orders succesful--> ");
        DBManager.close(rs,stmt);

        return allCruisesList;
    }

    @Override
    public Cruise findCruiseById(int id) throws DBException {
        Cruise cruise = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_CRUISE_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                cruise = extractCruises(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CRUISE_BY_ID, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return cruise;
    }


    @Override
    public Cruise findCruiseById(Connection con, int id) throws SQLException {
        Cruise cruise = null;
        PreparedStatement pstmt;
        ResultSet rs;

        pstmt = con.prepareStatement(Querys.SQL_FIND_CRUISE_BY_ID);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            cruise = extractCruises(rs);
        }
        DBManager.close(rs,pstmt);
        return cruise;
    }

    @Override
    public List<Cruise> findCruiseByStartOfCruise(String date) throws DBException {
        List<Cruise> allCruisesList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_CRUISE_BY_START_OF_CRUISE);
            pstmt.setString(1,date);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                allCruisesList.add(extractCruises(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CRUISE_BY_START_OF_CRUISE, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return allCruisesList;
    }

    @Override
    public List<Cruise> findCruiseByDuration(int duration) throws DBException {
        List<Cruise> allCruisesList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_CRUISE_BY_DURATION);
            pstmt.setInt(1,duration);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                allCruisesList.add(extractCruises(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CRUISE_BY_START_OF_CRUISE, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return allCruisesList;
    }




    @Override
    public void updateCruisesDb(Connection con, Cruise cruise) throws SQLException {
        PreparedStatement pstmt;
        ResultSet rs = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

        String stringStartOfCruise = formatter.format(cruise.getStartOfCruise());

        pstmt = con.prepareStatement(Querys.SQL_UPDATE_CRUISE_BY_ID);
        pstmt.setString(1, cruise.getName());
        pstmt.setString(2, cruise.getDescription());
        pstmt.setDouble(3, cruise.getPrice());
        pstmt.setInt(4, cruise.getCapacity());
        pstmt.setString(5, stringStartOfCruise);
        pstmt.setInt(6, cruise.getDuration());
        pstmt.setString(7, cruise.getStatus());
        pstmt.setInt(8, cruise.getId());

        pstmt.executeUpdate();

        DBManager.close(rs,pstmt);

    }


    @Override
    public void updateCruiseDb(int id, String name, String description, double price, int capacity,
                               String startOfCruise, int duration, String status) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_UPDATE_CRUISE_BY_ID);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, capacity);
            pstmt.setString(5, startOfCruise);
            pstmt.setInt(6, duration);
            pstmt.setString(7, status);
            pstmt.setInt(8, id);
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
    public void addToCruiseDb(String name, String description, double price,int capacity,String startOfCruise,
                              int duration,String status) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_INSERT_CRUISE);

            pstmt.setString(1, name);
            pstmt.setString(2, description);

            pstmt.setDouble(3, price);
            pstmt.setInt(4, capacity);
            pstmt.setString(5, startOfCruise);
            pstmt.setInt(6, duration);
            pstmt.setString(7, status);
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
    public void removeCruiseFromDb(int id) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.prepareStatement(Querys.SQL_DELETE_CRUISE_BY_ID);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, stmt, rs);
        }
    }


    @Override
    public Cruise extractCruises(ResultSet rs) throws SQLException {
        Cruise cruise = new Cruise();
        cruise.setId(rs.getInt(Fields.ENTITY_ID));
        cruise.setName(rs.getString(Fields.CRUISE_NAME));
        cruise.setDescription(rs.getString(Fields.CRUISE_DESCRIPTION));
        cruise.setPrice(rs.getDouble(Fields.CRUISE_PRICE));
        cruise.setCapacity(rs.getInt(Fields.CRUISE_CAPACITY));
        cruise.setStartOfCruise(rs.getTimestamp(Fields.CRUISE_START_OF_CRUISE));
        cruise.setDuration(rs.getInt(Fields.CRUISE_DURATION));
        cruise.setStatus(rs.getString(Fields.CRUISE_STATUS));

        return cruise;
    }
}