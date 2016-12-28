package com.epam.ism.connection;

import com.epam.ism.utils.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * JdbcConnectionPool class represents Simple Connection Pool implementation.
 * JdbcConnectionPool instance is got once based on Singleton pattern.
 *
 *@author IDS.
 */

public class ConnectionPool implements DataSource {
    private final static Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    //Constants
    private static final String PROPERTIES_FILE = "connectionPool.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";
    private static final String PROPERTY_POOL_SIZE = "pool_size";

    private static ConnectionPool instance;
    private BlockingQueue<Connection> pool;
    private String url;
    private String username;
    private String password;

    public ConnectionPool() {
        PropertyManager properties = new PropertyManager(PROPERTIES_FILE);
        url = properties.getProperty(PROPERTY_URL);
        String driverClassName = properties.getProperty(PROPERTY_DRIVER);
        username = properties.getProperty(PROPERTY_USERNAME);
        password = properties.getProperty(PROPERTY_PASSWORD);
        int poolSize = Integer.parseInt(properties.getProperty(PROPERTY_POOL_SIZE));

        logger.info("Datasource properties was obtained from "+ PROPERTIES_FILE + " successfully.");

        pool = new LinkedBlockingDeque<>(poolSize);
        logger.info("Pool container was initialized. Its size: " + poolSize);

        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Driver class '" + driverClassName + "' " +
                    "is missing in classpath.", e);
        }

        logger.info("DriverClass was loaded successfully.");
        initConnections();
        logger.info("Pool of connections was filled up successfully.");
    }

    public static synchronized ConnectionPool getInstance() {

        if (instance == null) {
            instance =  new ConnectionPool();
            logger.info("New ConnectionPool instance was created successfully.");
        }

        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection;

        synchronized (PROPERTIES_FILE) {
            if (pool.peek() == null) {
                initConnections();
            }

            try {
                connection = pool.take();
            } catch (InterruptedException e) {
                throw new ConnectionPoolException("Taking a connection from the pool failed.", e);
            }
        }
        return connection;
    }


    private void initConnections() {
        for (int i = 0; i < pool.size(); i++) {
            pool.offer(openConnection());
        }
    }

    private Connection openConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Retrieving a connection from the DriverManager is failed.", e);
        }

        return connection;
    }

    public void returnConnection(Connection connection) {
        pool.offer(connection);
        logger.info("The connection was returned to the pool successfully.");
    }

    //Unsupported operations
    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
