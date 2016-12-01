package com.epam.ism.dao;

import com.epam.ism.dao.exception.DAOConfigurationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAOFactory {

    //Constants
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";

    /**
     * Returns a new instance of DAOFactory for the given database name.
     * @param name The database name to return a new DAOFactory instance for.
     * @return A new DAOFactory instance for the given database name.
     * @throws DAOConfigurationException If the database name is null, or if the properties file
     * is missing in the classpath or cannot be loaded, or if a required property is missing in the
     * property file, or if either the driver cannot be loaded or the datasource cannot be found.
     */
    public static DAOFactory getInstance(String name) throws DAOConfigurationException {
        if (name == null) {
            throw new DAOConfigurationException("Database name is null.");
        }

        DAOProperties properties = new DAOProperties(name);
        String url = properties.getProperty(PROPERTY_URL);
        String driverClassName = properties.getProperty(PROPERTY_DRIVER);
        String username = properties.getProperty(PROPERTY_USERNAME);
        String password = properties.getProperty(PROPERTY_PASSWORD);

        DAOFactory instance;
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Driver class '" + driverClassName + "' " +
                    "is missing in classpath.", e);
        }
        instance = new DriverManagerDAOFactory(url,username,password);

        return instance;

    }

    /**
     * Returns a connection to the database.
     * @return A connection to the database.
     * @throws SQLException If acquiring the connection fails.
     */
    abstract Connection getConnection() throws SQLException;

    /**
     * Returns the User DAO associated with the current DAOFactory.
     * @return The User DAO.
     */
    public UserDAO getUserDAO() {
        return new UserDAOJDBC(this);
    }

    /**
     * The DriverManager based DAOFactory.
     */
    public static class DriverManagerDAOFactory extends DAOFactory {

        private String url;
        private String username;
        private String password;

        public DriverManagerDAOFactory(String url, String username, String password) {
            this.url = url;
            this.username = username;
            this.password = password;
        }

        @Override
        Connection getConnection() throws SQLException {
            return DriverManager.getConnection(url,username,password);
        }
    }
}
