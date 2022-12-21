package ua.nure.hrynko.dao.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserHasCruiseDAO {


    void addItemToUsersHasCruisesDb(Connection con, int usersId, int cruiseId, String status) throws SQLException;
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
