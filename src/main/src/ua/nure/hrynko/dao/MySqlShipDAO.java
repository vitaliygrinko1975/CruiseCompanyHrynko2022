package ua.nure.hrynko.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.Querys;
import ua.nure.hrynko.dao.interfaces.ServicesDAO;
import ua.nure.hrynko.dto.Ship;
import ua.nure.hrynko.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlShipDAO implements ServicesDAO {
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
        List<Ship> allServicesList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_SERVICES);
            while (rs.next()) {
                allServicesList.add(extractShip(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all services", ex);
            throw new DBException("cannot select all services", ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return allServicesList;
    }


    @Override
    public Ship extractShip(ResultSet rs) throws SQLException {

        Ship ship = new Ship();
        ship.setId(rs.getInt(Fields.ENTITY_ID));
        ship.setName(rs.getString(Fields.SHIPS_NAME));
        ship.setDescription(rs.getString(Fields.SHIPS_DESCRIPTION));

        return ship;

    }
}