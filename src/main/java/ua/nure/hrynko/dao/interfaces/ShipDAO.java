package ua.nure.hrynko.dao.interfaces;

import ua.nure.hrynko.models.Ship;

import ua.nure.hrynko.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ShipDAO {

    List<Ship> findAllShips() throws DBException;

    Ship findShipById(int id) throws DBException;

    void addToShipDb(String name, String description, int capacity) throws DBException;

    void updateShipDb(int id, String name, String description, int capacity) throws DBException;

    void removeShipFromDb(int id) throws DBException;
    Ship extractShipView(ResultSet rs) throws SQLException;



}
