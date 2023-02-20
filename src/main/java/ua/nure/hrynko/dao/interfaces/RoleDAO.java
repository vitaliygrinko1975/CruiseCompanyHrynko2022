package ua.nure.hrynko.dao.interfaces;

import ua.nure.hrynko.models.Role;

import ua.nure.hrynko.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {

    List<Role> findAllRoles() throws DBException;

    Role extractRoles(ResultSet rs) throws SQLException;

}
