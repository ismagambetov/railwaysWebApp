package com.epam.ism.dao;

import com.epam.ism.connection.ConnectionPool;
import com.epam.ism.dao.exception.DaoConfigurationException;
import com.epam.ism.utils.PropertyManager;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DaoFactory {
    private static final String PROPERTIES_FILE = "dao.properties";
    private static final String PROPERTY_DAO_CLASS = "dao_class";

    private static ConnectionPool connectionPool;

    static {
        connectionPool = ConnectionPool.getInstance();
    }

    public static DaoFactory getFactory() {
        PropertyManager properties = new PropertyManager(PROPERTIES_FILE);
        String className = properties.getProperty(PROPERTY_DAO_CLASS);
        DaoFactory instance;
        try {
            Class<?> clazz = Class.forName(className);
            instance = (DaoFactory) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            throw new DaoConfigurationException("Creating '" + className + "' failed.", e);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new DaoConfigurationException("Creating new instance of " + className +
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

    public abstract TrainDao getTrainDAO();
    public abstract StationDao getStationDAO();
    public abstract OrderDao getOrderDAO();
    public abstract MainRouteDao getMainRouteDAO();
    public abstract UserDao getUserDAO();


}
