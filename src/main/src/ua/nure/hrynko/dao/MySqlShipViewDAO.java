package ua.nure.hrynko.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.Querys;
import ua.nure.hrynko.dao.interfaces.ShipViewDAO;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.exception.Messages;
import ua.nure.hrynko.models.ShipView;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlShipViewDAO implements ShipViewDAO {
    private static final Logger LOG = Logger.getLogger(MySqlShipViewDAO.class);
    private static MySqlShipViewDAO instance;
    public static synchronized MySqlShipViewDAO getInstance() {
        if (instance == null) {
            instance = new MySqlShipViewDAO();
        }
        return instance;
    }

    @Override
    public List<ShipView> findAllItemFromShipsViewDB() throws DBException {
        List<ShipView> shipList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_SHIPS_FROM_SHIPS_VIEW);
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
    public List<ShipView> findAllFreeShip(String startOfContract, String endOfContract) throws DBException {
        List<ShipView> shipList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_ALL_FREE_SHIP_FROM_SHIPS_VIEW);
            pstmt.setString(1, startOfContract);
            pstmt.setString(2, endOfContract);
            pstmt.setString(3, startOfContract);
            pstmt.setString(4, endOfContract);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                shipList.add(extractShipView(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_SHIP_BY_ID, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return shipList;
    }



    @Override
    public ShipView extractShipView(ResultSet rs) throws SQLException {

        ShipView shipView = new ShipView();
        shipView.setId(rs.getInt(Fields.ENTITY_ID));
        shipView.setName(rs.getString(Fields.SHIPS_NAME));
        shipView.setDescription(rs.getString(Fields.SHIPS_DESCRIPTION));
        shipView.setCapacity(rs.getInt(Fields.SHIPS_CAPACITY));
        shipView.setStartOfContract(rs.getTimestamp(Fields.START_OF_CONTRACT));
        shipView.setEndOfContract(rs.getTimestamp(Fields.END_OF_CONTRACT));

        return shipView;

    }
}