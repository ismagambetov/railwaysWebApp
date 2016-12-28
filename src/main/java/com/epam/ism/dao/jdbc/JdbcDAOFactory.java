package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.*;

public class JdbcDaoFactory extends DaoFactory {

    @Override
    public TrainDao getTrainDAO() {
        return new JdbcTrainDao();
    }

    @Override
    public StationDao getStationDAO() {
        return new JdbcStationDao();
    }

    @Override
    public OrderDao getOrderDAO() {
        return new JdbcOrderDao();
    }

    @Override
    public MainRouteDao getMainRouteDAO() {
        return new JdbcMainRouteDao();
    }

    @Override
    public UserDao getUserDAO() { return new JdbcUserDao(); }
}
