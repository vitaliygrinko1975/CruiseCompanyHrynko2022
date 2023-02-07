package ua.nure.hrynko.dao.interfaces;

import ua.nure.hrynko.models.OrderView;
import ua.nure.hrynko.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderViewDAO {
    List<OrderView> findAllIItemOfOrderViewByUserId(int userId) throws DBException;
    List<OrderView> findAllItemOfOrderViewWithLimit(int start, int total) throws DBException;
    List<OrderView> findAllIItemOfOrderViewByUserIdWithLimit(int id, int start, int total) throws DBException;
    List<OrderView> findAllItemOfOrdersView() throws DBException;

    int countingTheNumberOfRecordsToOrderViewDb() throws DBException;

    OrderView extractOrdersView(ResultSet rs) throws SQLException;
}
