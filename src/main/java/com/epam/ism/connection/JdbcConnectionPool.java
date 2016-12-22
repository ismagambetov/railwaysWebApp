package com.epam.ism.connection;

import com.epam.ism.dao.DAOProperties;
import com.epam.ism.dao.jdbc.JdbcDAOFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * JdbcConnectionPool class represents Simple Connection Pool implementation,
 * extended by {@link JdbcDAOFactory} abstract JdbcDAOFactory class.
 * JdbcConnectionPool instance is got once based on Singleton pattern.
 *
 *@author IDS.
 */
public class JdbcConnectionPool extends JdbcDAOFactory {

    private final static Logger logger = LoggerFactory.getLogger(JdbcConnectionPool.class);

    //Constants
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";
    private static final String PROPERTY_POOL_SIZE = "poolsize";

    //Properties
    private static JdbcConnectionPool instance;
    private BlockingDeque<Connection> pool;
    private int poolSize;
    private String url;
    private String username;
    private String password;

    /**
     * Constructs a JdbcConnectionPool with given the database name.
     * @param name The database name.
    */
    private JdbcConnectionPool(String name) {

        DAOProperties properties = new DAOProperties(name);
        url = properties.getProperty(PROPERTY_URL);
        String driverClassName = properties.getProperty(PROPERTY_DRIVER);
        username = properties.getProperty(PROPERTY_USERNAME);
        password = properties.getProperty(PROPERTY_PASSWORD);
        poolSize = Integer.parseInt(properties.getProperty(PROPERTY_POOL_SIZE));

        logger.info("Datasource properties was obtained from property file successfully.");

        pool = new LinkedBlockingDeque<>(poolSize);
        logger.info("Pool container was initialized. Its size: " + poolSize);

        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Driver class '" + driverClassName + "' " +
                    "is missing in classpath.", e);
        }

        logger.info("Driver class was loaded successfully.");

        initConnections();
        logger.info("Pool of connections was filled up successfully.");

    }

    /**
     * Method getInstance() returns new instance of JdbcConnectionPool, if the instance == null,
     * otherwise returns current object.
     * @param name Database name to be given to constructor.
     * @return JdbcConnectionPool instance.
     */
    public static synchronized JdbcConnectionPool getInstance(String name) {
        if (instance == null) {
            instance = new JdbcConnectionPool(name);
            logger.info("New JdbcConnectionPool instance was created successfully.");
        }

        return instance;
    }

    /**
     * Method initConnections() fills Pool up with new connections.
     */
    private void initConnections() {
        for (int i = 0; i < poolSize; i++) {
            pool.offer(openConnection());
        }
    }

    /**
     * Method openConnection() attempts to load the driver class
     * @return New connection.
     */
    private Connection openConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Retrieving a connection from the DriverManager is failed.",e);
        }

        return connection;
    }

    @Override
    public Connection getConnection() {
        synchronized (pool) {
            if (pool.peek() == null) {
                initConnections();
            }
            try {
                return pool.take();
            } catch (InterruptedException e) {
                throw new ConnectionPoolException("Process was interrupted while taking " +
                                                                        "a connection from the pool.", e);
            }
        }
    }

    // TODO: 14.12.2016 don't know how to implement this in the right way.
    @Override
    public void returnConnection(Connection connection) {
        pool.offer(connection);
        logger.info("The connection was returned to the pool successfully.");
    }
}
