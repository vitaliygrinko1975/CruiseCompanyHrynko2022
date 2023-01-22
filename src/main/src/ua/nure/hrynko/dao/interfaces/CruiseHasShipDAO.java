package ua.nure.hrynko.dao.interfaces;

import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.CruiseHasShip;
import ua.nure.hrynko.models.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CruiseHasShipDAO {


    void addItemToCruiseHasShipDb(Connection con, int cruiseId, int shipId, String startOfContract,
                                  String endOfContract, String status) throws SQLException;
    void addItemToCruiseHasShipDb(int cruiseId,int shipId, String startOfContract,
                                  String endOfContract, String status) throws SQLException, DBException;

    List<CruiseHasShip> findAllItemsOfCruiseHasShip() throws DBException;

    CruiseHasShip extractOrder(ResultSet rs) throws SQLException;
}
