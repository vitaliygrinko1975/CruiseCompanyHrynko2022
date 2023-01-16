package ua.nure.hrynko.dao.interfaces;

import ua.nure.hrynko.models.Ship;

import ua.nure.hrynko.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ShipsDAO {

    List<Ship> findAllShips() throws DBException;

    Ship extractShip(ResultSet rs) throws SQLException;



}
