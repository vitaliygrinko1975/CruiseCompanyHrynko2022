package ua.nure.hrynko.dao.interfaces;

import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.ShipView;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ShipViewDAO {


    List<ShipView> findAllItemFromShipsViewDB() throws DBException;

    List<ShipView> findAllFreeShip(String startOfContract, String endOfContract) throws DBException;

    ShipView extractShipView(ResultSet rs) throws SQLException;
}
