package com.epam.ism.dao;

import com.epam.ism.connection.ConnectionPool;
import com.epam.ism.dao.exception.DaoConfigurationException;
import com.epam.ism.utils.PropertyManager;

public abstract class DaoFactory {
    private static final String PROPERTIES_FILE = "dao.properties";
    private static final String PROPERTY_DAO_CLASS = "dao_class";

    private static DaoFactory instance = null;
    private static DaoManager daoManager = null;
    static Class<?> clazz = null;

    public static DaoFactory getFactory() {

        if (clazz == null) {
            PropertyManager properties = new PropertyManager(PROPERTIES_FILE);
            String className = properties.getProperty(PROPERTY_DAO_CLASS);

            try {
                clazz = Class.forName(className);

            } catch (ClassNotFoundException e) {
                throw new DaoConfigurationException("Creating '" + className + "' failed.", e);
//            } catch (InstantiationException | IllegalAccessException e) {
//                throw new DaoConfigurationException("Creating new instance of " + className +
//                                            ".class failed.", e);
            }
        }
        try {
            instance = (DaoFactory) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return instance;
    }

    public static DaoManager getDaoManager() {
        if (daoManager == null) {
            daoManager = new DaoManager(ConnectionPool.getInstance());
        }
        return daoManager;
    }


    public abstract TrainDao getTrainDao();
    public abstract StationDao getStationDao();
    public abstract OrderDao getOrderDao();
    public abstract RouteDao getRouteDao();
    public abstract UserDao getUserDao();
}
