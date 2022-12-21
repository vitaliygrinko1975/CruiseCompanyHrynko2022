package ua.nure.hrynko.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.Querys;
import ua.nure.hrynko.dao.interfaces.CruiseDAO;
import ua.nure.hrynko.dto.Cruise;
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
        DBManager.close(rs);
        DBManager.close(pstmt);
        return cruise;
    }

    @Override
    public void addCruiseToCruisesDb(Connection con, Cruise cruise) throws SQLException {
        PreparedStatement pstmt;
        ResultSet rs = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

        String stringStartOfCruise = formatter.format(cruise.getStartOfCruise());
        String stringEndOfCruise = formatter.format(cruise.getEndOfCruise());

        pstmt = con.prepareStatement(Querys.SQL_UPDATE_CRUISE_BY_ID);
        pstmt.setString(1, cruise.getName());
        pstmt.setString(2, cruise.getDescription());
        pstmt.setDouble(3, cruise.getPrice());
        pstmt.setInt(4, cruise.getShipId());
        pstmt.setInt(5, cruise.getCapacity());
        pstmt.setString(6, stringStartOfCruise);
        pstmt.setString(7, stringEndOfCruise);
        pstmt.setInt(8, cruise.getId());

        pstmt.executeUpdate();

        DBManager.close(rs);
        DBManager.close(pstmt);
    }


    @Override
    public Cruise extractCruises(ResultSet rs) throws SQLException {
        Cruise cruise = new Cruise();
        cruise.setId(rs.getInt(Fields.ENTITY_ID));
        cruise.setName(rs.getString(Fields.CRUISE_NAME));
        cruise.setDescription(rs.getString(Fields.CRUISE_DESCRIPTION));
        cruise.setPrice(rs.getDouble(Fields.CRUISE_PRICE));
        cruise.setShipId(rs.getInt(Fields.CRUISE_SHIPS_ID));
        cruise.setCapacity(rs.getInt(Fields.CRUISE_CAPACITY));
        cruise.setStartOfCruise(rs.getTimestamp(Fields.CRUISE_START_OF_CRUISE));
        cruise.setEndOfCruise(rs.getTimestamp(Fields.CRUISE_END_OF_CRUISE));

        return cruise;
    }
}