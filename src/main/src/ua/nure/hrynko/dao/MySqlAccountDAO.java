package ua.nure.hrynko.dao;

import org.apache.log4j.Logger;
import ua.nure.hrynko.DBManager;
import ua.nure.hrynko.Fields;
import ua.nure.hrynko.Querys;
import ua.nure.hrynko.dao.interfaces.AccountDAO;
import ua.nure.hrynko.models.Account;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlAccountDAO implements AccountDAO {

    private static final Logger LOG = Logger.getLogger(MySqlAccountDAO.class);

    private static MySqlAccountDAO instance;

    public static synchronized MySqlAccountDAO getInstance() {
        if (instance == null) {
            instance = new MySqlAccountDAO();
        }
        return instance;

    }

    public List<Account> findAllAccounts() throws DBException {
        List<Account> allAccountsList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(Querys.SQL_FIND_ALL_ACCOUNT);
            while (rs.next()) {
                allAccountsList.add(extractAccount(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            LOG.error("cannot select all accounts", ex);
            throw new DBException("cannot select all accounts", ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return allAccountsList;
    }

    @Override
    public List<Account> findAllAccounts(Connection con) throws SQLException {
        List<Account> allAccountsList = new ArrayList<>();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Querys.SQL_FIND_ALL_ACCOUNT);
            while (rs.next()) {
                allAccountsList.add(extractAccount(rs));
            }

            DBManager.close(rs);
            DBManager.close(stmt);

        return allAccountsList;
    }

    @Override
    public Account findAccountById(int id) throws DBException {
        Account account = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(Querys.SQL_FIND_ACCOUNT_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                account = extractAccount(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_ACCOUNT_BY_ID, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return account;
    }

    @Override
    public Account findAccountById(Connection con, int id) throws SQLException {
        Account account = null;
        PreparedStatement pstmt;
        ResultSet rs ;
        pstmt = con.prepareStatement(Querys.SQL_FIND_ACCOUNT_BY_ID);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            account = extractAccount(rs);
            DBManager.close(rs);
            DBManager.close(pstmt);
        }
        return account;
    }

    @Override
    public void removeAccountFromDb(int id) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.prepareStatement(Querys.SQL_DELETE_ACCOUNT_BY_ID);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            LOG.trace("ERRor--> remove account from Db");
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, stmt, rs);
        }

    }

    @Override
    public void updateAccountToDb(Account account) throws DBException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.prepareStatement(Querys.SQL_UPDATE_ACCOUNT_BY_ID);
            stmt.setDouble(1, account.getBalance());
            stmt.setInt(2, account.getId());
            stmt.executeUpdate();
            con.commit();
            LOG.trace("update Account to SQL seccesful--> ");
        } catch (SQLException ex) {
            LOG.trace("ERRor--> ");
            ex.printStackTrace();
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, stmt, rs);
        }
    }

    @Override
    public void updateAccountToDb(Connection con, Account account) throws SQLException {
        PreparedStatement stmt;
        ResultSet rs = null;
        stmt = con.prepareStatement(Querys.SQL_UPDATE_ACCOUNT_BY_ID);
        stmt.setDouble(1, account.getBalance());
        stmt.setInt(2, account.getId());
        stmt.executeUpdate();
        con.commit();
        LOG.trace("update Account to SQL seccesful--> ");
        DBManager.close(rs);
        DBManager.close(stmt);
    }

    @Override
    public void updateAccountToDb(Connection con, int accountId, double balance) throws SQLException {
        PreparedStatement stmt;
        ResultSet rs = null;
        stmt = con.prepareStatement(Querys.SQL_UPDATE_ACCOUNT_BY_ID);
        stmt.setDouble(1, balance);
        stmt.setInt(2, accountId);
        stmt.executeUpdate();
        con.commit();
        LOG.trace("update Account to SQL seccesful--> ");
        DBManager.close(rs);
        DBManager.close(stmt);
    }

    @Override
    public void addAccountsDb(double balance) throws DBException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.prepareStatement(Querys.SQL_INSERT_ACCOUNT);
            stmt.setDouble(1, balance);

            stmt.executeUpdate();


            LOG.trace("get last id from account to SQL succesful--> ");


            LOG.trace(" id inserted account--> ");
        } catch (SQLException ex) {
            LOG.trace("ERRor--> ");
            ex.printStackTrace();
            DBManager.rollback(con);
        } finally {
            DBManager.close(con, stmt, rs);
        }

    }


    @Override
    public void addAccountsDb(Connection con, double balance) throws SQLException {
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(Querys.SQL_INSERT_ACCOUNT);
        pstmt.setDouble(1, balance);
        pstmt.executeUpdate();
        DBManager.close(pstmt);
    }

    @Override
    public Account extractAccount(ResultSet rs) throws SQLException {
        Account accounts = new Account();
        accounts.setId(rs.getInt(Fields.ENTITY_ID));
        accounts.setBalance(rs.getDouble(Fields.ACCOUNTS_BALANCE));

        return accounts;
    }
}