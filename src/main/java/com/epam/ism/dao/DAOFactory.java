package com.epam.ism.dao;

import com.epam.ism.connection.ConnectionPool;
import com.epam.ism.dao.exception.DAOConfigurationException;
import com.epam.ism.utils.PropertyManager;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {
    private static final String PROPERTIES_FILE = "dao.properties";
    private static final String PROPERTY_DAO_CLASS = "dao_class";

    private static ConnectionPool connectionPool;

    static {
        connectionPool = ConnectionPool.getInstance();
    }

    public static DAOFactory getFactory() {
        PropertyManager properties = new PropertyManager(PROPERTIES_FILE);
        String className = properties.getProperty(PROPERTY_DAO_CLASS);
        DAOFactory instance;
        try {
            Class<?> clazz = Class.forName(className);
            instance = (DAOFactory) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Creating '" + className + "' failed.", e);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new DAOConfigurationException("Creating new instance of " + className +
                                        ".class failed.", e);
        }

        return instance;
    }

    public static Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    public static void returnConnection(Connection connection) {
        connectionPool.returnConnection(connection);
    }

    public abstract TrainDAO getTrainDAO();
    public abstract StationDAO getStationDAO();
    public abstract OrderDAO getOrderDAO();
    public abstract MainRouteDAO getMainRouteDAO();
    public abstract UserDAO getUserDAO();


}
