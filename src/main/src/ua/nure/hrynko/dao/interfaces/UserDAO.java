package ua.nure.hrynko.dao.interfaces;

import ua.nure.hrynko.dto.User;
import ua.nure.hrynko.exception.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    User findUserByLogin(String login) throws DBException;

    User findUserById(int id) throws DBException;

    User findUserById(Connection con,int id) throws DBException, SQLException;

    List<User> findAllUsers() throws DBException;

    List<User> findAllUsers(Connection con) throws SQLException;

    void addToUsersDb(String login, String password, String firstName, String lastName, String email, String phone,
                      boolean blocked, int roleId, int accountsId) throws DBException;

    void addToUsersDb(Connection con, String login, String password, String firstName, String lastName, String email, String phone,
                      boolean blocked, int roleId, int accountsId) throws DBException, SQLException;

    void removeUserFromDb(Integer id) throws DBException;

    void updateUserToDb(int id, String login, String password, String firstName, String lastName, String email,
                        String phone, boolean blocked, int roleId, int accountsId) throws DBException;
    void updateUserToDb(User user) throws DBException;

    User extractUser(ResultSet rs) throws SQLException;

}
