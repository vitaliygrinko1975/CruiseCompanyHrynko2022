package ua.nure.hrynko.services;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.dao.MySqlAccountDAO;
import ua.nure.hrynko.dao.MySqlCruiseDAO;
import ua.nure.hrynko.dao.MySqlUserDAO;
import ua.nure.hrynko.dao.MySqlOrderDAO;
import ua.nure.hrynko.models.Account;
import ua.nure.hrynko.models.Cruise;
import ua.nure.hrynko.models.User;
import ua.nure.hrynko.exception.DBException;

import java.sql.Connection;
import java.util.Map;

public class AllMethodsWithTransactions {
    private static final long serialVersionUID = 7732286214029478505L;
    private static final Logger LOG = Logger.getLogger(AllMethodsWithTransactions.class);

    private MySqlOrderDAO orderDAO;
    private   MySqlCruiseDAO cruiseDAO;
    private   MySqlUserDAO mySqlUserDAO;
    private   MySqlAccountDAO mySqlAccountDAO;


    public int addBasketToUsersHasCruisesDbReturnDepositAmount(Integer userId, Map<Cruise, Integer> mapForBasket) throws DBException {

        orderDAO = MySqlOrderDAO.getInstance();
        cruiseDAO = MySqlCruiseDAO.getInstance();
        int depositAmount = 0;

        //START TRANSACTION
        Connection con = DBManager.getInstance().getConnection();
        try {
            con.setAutoCommit(false);

            for (Map.Entry<Cruise, Integer> item : mapForBasket.entrySet()) {
                depositAmount += item.getKey().getPrice() * item.getValue();
                for (int i = 0; i < item.getValue(); i++) {
                    int cruiseId = item.getKey().getId();
                    orderDAO.addItemToOrdersDb(con, userId, cruiseId, "Не оплачено");
                    Cruise currentCruise = cruiseDAO.findCruiseById(con, cruiseId);
                    int newCapacity = currentCruise.getCapacity() - 1;
                    currentCruise.setCapacity(newCapacity);
                    cruiseDAO.updateCruisesDb(con, currentCruise);
                }
            }
            con.commit();
        } catch (Exception ex) {
            DBManager.rollback(con);
            LOG.error("cannot confirm order of selected units", ex);
            throw new DBException("cannot confirm order of selected units", ex);
        } finally {
            DBManager.close(con);
        }//END TRANSACTION
        return depositAmount;

    }

    public String changeStatusWithWithdrawalFromDeposit(int usersIdForWithdrawalFromDeposit,
                                                        int cruiseId, int usersHasCruisesIdForUpdate, String status)throws DBException{
        orderDAO = MySqlOrderDAO.getInstance();
        cruiseDAO = MySqlCruiseDAO.getInstance();
        mySqlUserDAO = MySqlUserDAO.getInstance();
        mySqlAccountDAO = MySqlAccountDAO.getInstance();

        String message = "Транзакция прошла успешно" ;
        //START TRANSACTION
        Connection con = DBManager.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            Cruise cruiseWithOrder = cruiseDAO.findCruiseById(con, cruiseId);
            User userWithOrder = mySqlUserDAO.findUserById(con, usersIdForWithdrawalFromDeposit);
            int accountsId = userWithOrder.getAccountsId();
            Account usersAccount = mySqlAccountDAO.findAccountById(con, accountsId);
            double currentBalance = usersAccount.getBalance();
            double depositReducer = cruiseWithOrder.getPrice();
            double newPrice = currentBalance - depositReducer;

            if (newPrice >= 0 && !status.equals("Оплачено") ) {
                mySqlAccountDAO.updateAccountToDb(con, usersIdForWithdrawalFromDeposit, newPrice);
                orderDAO.updateItemOfUsersHasCruise(con, usersHasCruisesIdForUpdate,"Оплачено");
            }
            if (newPrice < 0 && !status.equals("Оплачено")){
                message = "На счете не достаточно средств";
                orderDAO.updateItemOfUsersHasCruise(con, usersHasCruisesIdForUpdate,message);
            }

            con.commit();
        } catch(Exception ex) {
            DBManager.rollback(con);
            LOG.error("cannot change status of selected unit", ex);
            throw new DBException("cannot change status of selected unit", ex);
        } finally

        {
            DBManager.close(con);
        }//END TRANSACTION

        return message;
    }
}
