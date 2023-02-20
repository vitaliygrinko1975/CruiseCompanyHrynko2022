package ua.nure.hrynko.services;

import ua.nure.hrynko.dao.MySqlCruiseDAO;
import ua.nure.hrynko.dao.MySqlOrderViewDAO;
import ua.nure.hrynko.dao.MySqlUserDAO;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.models.Cruise;
import ua.nure.hrynko.models.OrderView;
import ua.nure.hrynko.models.User;

import java.util.List;

public class Paginations {
    MySqlOrderViewDAO orderViewDAO = new MySqlOrderViewDAO();
    MySqlUserDAO userDAO = new MySqlUserDAO();
    MySqlCruiseDAO cruiseDAO = new MySqlCruiseDAO();
    List<OrderView> allItemOfOrdersViewWithLimit;
    List<User> allItemOfUserWithLimit;
    List<Cruise> allItemOfCruisesWithLimit;


    public List<OrderView> makePaginationForOrders(int numberPage) throws DBException {
        if (numberPage > 0) {
            int total = 5;
            int start = (numberPage - 1) * total;
            allItemOfOrdersViewWithLimit = orderViewDAO.findAllItemOfOrderViewWithLimit(start, total);
        }
        return allItemOfOrdersViewWithLimit;
    }
    public List<OrderView> makePaginationForMyOrders(int id, int numberPage) throws DBException {
        if (numberPage > 0) {
            int total = 5;
            int start = (numberPage - 1) * total;
            allItemOfOrdersViewWithLimit = orderViewDAO.findAllIItemOfOrderViewByUserIdWithLimit(id,start, total);
        }
        return allItemOfOrdersViewWithLimit;
    }

    public List<User> makePaginationForUsers(int numberPage) throws DBException {
        if (numberPage > 0) {
            int total = 2;
            int start = (numberPage - 1) * total;
            allItemOfUserWithLimit = userDAO.findAllItemOfUserWithLimit(start, total);
        }
        return allItemOfUserWithLimit;
    }
    public List<Cruise> makePaginationForCruises(int numberPage) throws DBException {
        if (numberPage > 0) {
            int total = 5;
            int start = (numberPage - 1) * total;
            allItemOfCruisesWithLimit = cruiseDAO.findAllItemOfCruisesWithLimit(start, total);
        }
        return  allItemOfCruisesWithLimit;
    }



}