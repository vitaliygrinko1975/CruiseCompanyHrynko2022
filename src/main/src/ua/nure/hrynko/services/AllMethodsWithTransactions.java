package ua.nure.hrynko.services;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.dao.MySqlCruiseDAO;
import ua.nure.hrynko.dao.MySqlUsersHasCruiseDAO;
import ua.nure.hrynko.dto.Cruise;
import ua.nure.hrynko.exception.DBException;
import java.sql.Connection;
import java.util.Map;

public class AllMethodsWithTransactions {
    private static final long serialVersionUID = 7732286214029478505L;
    private static final Logger LOG = Logger.getLogger(AllMethodsWithTransactions.class);

    MySqlUsersHasCruiseDAO usersHasCruiseDAO;
    MySqlCruiseDAO mySqlCruiseDAO;

    public void addBasketToUsersHasCruisesDb(Integer userId, Map<Cruise, Integer> mapForBasket) throws DBException {

        usersHasCruiseDAO = MySqlUsersHasCruiseDAO.getInstance();
        mySqlCruiseDAO = MySqlCruiseDAO.getInstance();

        //START TRANSACTION
        Connection con = DBManager.getInstance().getConnection();
        try {
            con.setAutoCommit(false);

            for (Map.Entry<Cruise, Integer> item : mapForBasket.entrySet()) {
                for (int i = 0; i < item.getValue(); i++) {
                    int cruiseId = item.getKey().getId();
                    usersHasCruiseDAO.addItemToUsersHasCruisesDb(con, userId, cruiseId, "не оплачено");
                    Cruise currentCruise = mySqlCruiseDAO.findCruiseById(con, cruiseId);
                    int newCapacity = currentCruise.getCapacity() - 1;
                    currentCruise.setCapacity(newCapacity);
                    mySqlCruiseDAO.addCruiseToCruisesDb(con,currentCruise);
                }
            }
            con.commit();
        } catch (Exception ex) {
            DBManager.rollback(con);
            LOG.error("cannot confirm order of selected units", ex);
            throw new DBException("cannot confirm order of selected units", ex);
        } finally {
            DBManager.close(con);
        }
        //END TRANSACTION
    }
}
