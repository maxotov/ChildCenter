package kz.enu.fit.pool;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class ConnectionPool {

    public static final Logger log = Logger.getLogger(ConnectionPool.class);
    private static final String DATASOURCE_NAME = "jdbc/mysql";
    private static DataSource dataSource;

    static {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            //get an object by its name
            dataSource = (DataSource) envContext.lookup(DATASOURCE_NAME);
        } catch (NamingException ex) {
            log.error(ex);
        }
    }

    /**
     * Returns connection from the connection pool
     *
     * @return connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }
}
