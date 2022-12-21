package ua.nure.hrynko.dao.interfaces;

import ua.nure.hrynko.dto.Account;
import ua.nure.hrynko.exception.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface AccountDAO {

    List<Account> findAllAccounts() throws DBException;
    Account extractAccount(ResultSet rs) throws SQLException;
    List<Account> findAllAccounts(Connection con) throws  SQLException;
    Account findAccountById(int id) throws DBException;
    Account findAccountById(Connection con, int id) throws DBException, SQLException;
    void removeAccountFromDb(int id) throws DBException;
    void updateAccountToDb(Account account) throws DBException;
    void updateAccountToDb(Connection con, Account account) throws DBException, SQLException;
    void addAccountsDb(int number, double balance) throws DBException;
    void addAccountsDb(Connection con, int number, double balance) throws SQLException;


}
