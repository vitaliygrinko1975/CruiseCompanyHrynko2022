package ua.nure.hrynko.dao.interfaces;

import ua.nure.hrynko.dto.Cruise;

import ua.nure.hrynko.exception.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CruiseDAO {

    List<Cruise> findAllCruises() throws DBException;
    Cruise findCruiseById(int id) throws DBException;
    Cruise findCruiseById(Connection con, int id) throws DBException, SQLException;
    void addCruiseToCruisesDb(Connection con, Cruise cruise) throws SQLException;
    Cruise extractCruises(ResultSet rs) throws SQLException;


}
