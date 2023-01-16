package ua.nure.hrynko.dao.interfaces;

import ua.nure.hrynko.models.Cruise;

import ua.nure.hrynko.exception.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface CruiseDAO {

    List<Cruise> findAllCruises() throws DBException;
    Cruise findCruiseById(int id) throws DBException;
    Cruise findCruiseById(Connection con, int id) throws DBException, SQLException;
    List<Cruise> findCruiseByStartOfCruise(String date) throws DBException;
    List<Cruise> findCruiseByDuration(int duration) throws DBException;
    void updateCruisesDb(Connection con, Cruise cruise) throws SQLException;
    void updateCruiseDb(int id, String name, String description, double price, int shipId, int capacity,
                        String startOfCruise, int duration) throws DBException;
    void addToCruiseDb(String name, String description, double price, int shipsId,int capacity,String startOfCruise,
                       int duration,String status) throws DBException;
    void removeCruiseFromDb(int id) throws DBException;
    Cruise extractCruises(ResultSet rs) throws SQLException;

}
