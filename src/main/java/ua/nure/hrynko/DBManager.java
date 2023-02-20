package ua.nure.hrynko;

import org.apache.log4j.Logger;
import ua.nure.hrynko.exception.DBException;
import ua.nure.hrynko.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;


public final class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    // //////////////////////////////////////////////////////////
    // singleton
    // //////////////////////////////////////////////////////////

    private static DBManager instance;

    public static synchronized DBManager getInstance() throws DBException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            // provider - the name of data source
            ds = (DataSource) envContext.lookup("jdbc/cruises");
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    public static DataSource ds;

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in
     * your WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return DB connection.
     */
    public  Connection getConnection() throws DBException {
        Connection con;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return con;
    }

     // //////////////////////////////////////////////////////////
    // DB methods for transaction
    // //////////////////////////////////////////////////////////







    // //////////////////////////////////////////////////////////
    // DB util methods
    // //////////////////////////////////////////////////////////

    /**
     * Closes a connection.
     *
     * @param con Connection to be closed.
     */
    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    /**
     * Closes a statement object.
     */
    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }
    /**
     * Closes a prepare statement object.
     */
    public static void close(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }


    /**
     * Closes a result set object.
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULT_SET, ex);
            }
        }
    }

    /**
     * Closes resources.
     */
    public static void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }
    public static void close(ResultSet rs, Statement stmt) {
        close(rs);
        close(stmt);
    }


    /**
     * Rollbacks a connection.
     *
     * @param con Connection to be rollbacked.
     */
    public static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }

}
