package ua.nure.hrynko.dao.interfaces;

import ua.nure.hrynko.models.Order;
import ua.nure.hrynko.exception.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    void addItemToOrdersDb(Connection con, int usersId, int cruiseId, String status) throws SQLException;
    List<Order> findAllItemOfOrder() throws DBException;
    Order extractOrder(ResultSet rs) throws SQLException;
    List<Order> findAllItemOfOrder(Connection con) throws DBException, SQLException;
    List<Order> findAllIItemOfOrderWithLimit(int start, int total) throws DBException;
    Order findOrderById(Connection con, int id) throws DBException, SQLException;
    void updateItemOrder(Connection con, Order itemUserHasCruise) throws SQLException;
    void updateItemOrder(Connection con, int id, String status) throws SQLException;
    Order findOrderById(int id) throws DBException;


}
