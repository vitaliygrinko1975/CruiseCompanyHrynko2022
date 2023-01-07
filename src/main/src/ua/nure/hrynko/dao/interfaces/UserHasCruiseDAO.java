package ua.nure.hrynko.dao.interfaces;

import ua.nure.hrynko.dto.UserHasCruise;
import ua.nure.hrynko.exception.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserHasCruiseDAO {


    void addItemToUsersHasCruisesDb(Connection con, int usersId, int cruiseId, String status) throws SQLException;

    List<UserHasCruise> findAllItemOfUsersHasCruises() throws DBException;
    UserHasCruise extractUsersHasCruise(ResultSet rs) throws SQLException;
    List<UserHasCruise> findAllIItemOfUsersHasCruiseWithLimit(int start, int total) throws DBException;

//
//    List<UsersHasCruise> findItemOnUsersHasTariffsByUserId(int id) throws DBException;
//
//    List<UsersHasCruise> findItemOnUsersHasTariffsByUserId(Connection con, int id) throws DBException, SQLException;
//
//    void removeItemFromDb(Connection con, int userId, int tariffId) throws DBException, SQLException;
//
//    UsersHasCruise extractUsersHasTariffs(ResultSet rs) throws SQLException;
//
//    void updateItemOfUsersHasTariffs(Connection con, UsersHasCruise itemUsersHasTariffs) throws SQLException;

}
