package ua.nure.hrynko.dao.interfaces;

import ua.nure.hrynko.models.OrderView;
import ua.nure.hrynko.exception.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderViewDAO {

    List<OrderView> findAllIItemOfOrderViewWithLimit(int start, int total) throws DBException;
    List<OrderView> findAllItemOfOrdersView() throws DBException;
    OrderView extractOrdersView(ResultSet rs) throws SQLException;
}
