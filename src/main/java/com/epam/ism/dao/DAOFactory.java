package com.epam.ism.dao;

import com.epam.ism.connection.ConnectionPool;
import com.epam.ism.dao.exception.DaoConfigurationException;
import com.epam.ism.utils.PropertyManager;

public abstract class DaoFactory {
    private static final String PROPERTIES_FILE = "dao.properties";
    private static final String PROPERTY_DAO_CLASS = "dao_class";

    private static DaoFactory instance = null;

    public static DaoFactory getFactory() {

        if (instance == null) {
            PropertyManager properties = new PropertyManager(PROPERTIES_FILE);
            String className = properties.getProperty(PROPERTY_DAO_CLASS);

            try {
                Class<?> clazz = Class.forName(className);
                instance = (DaoFactory) clazz.newInstance();
            } catch (ClassNotFoundException e) {
                throw new DaoConfigurationException("Creating '" + className + "' failed.", e);

            } catch (InstantiationException | IllegalAccessException e) {
                throw new DaoConfigurationException("Creating new instance of " + className +
                        ".class failed.", e);}
        }

        return instance;
    }

    public static DaoManager getDaoManager() {
        return new DaoManager(ConnectionPool.getInstance());
    }


    public abstract TrainDao getTrainDao(DaoManager daoManager);
    public abstract StationDao getStationDao(DaoManager daoManager);
    public abstract OrderDao getOrderDao(DaoManager daoManager);
    public abstract RouteDao getRouteDao(DaoManager daoManager);
    public abstract UserDao getUserDao(DaoManager daoManager);
    public abstract WagonDao getWagonDao(DaoManager daoManager);

}
