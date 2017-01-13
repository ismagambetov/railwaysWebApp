package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.*;

import java.sql.Connection;

public class JdbcDaoFactory extends DaoFactory {
    private Connection connection;

    public JdbcDaoFactory() {
        DaoManager daoManager = DaoFactory.getDaoManager();
        this.connection = daoManager.getTxConnection();
    }

    @Override
    public TrainDao getTrainDao() {
        return new JdbcTrainDao(connection);
    }

    @Override
    public StationDao getStationDao() {
        return new JdbcStationDao(connection);
    }

    @Override
    public OrderDao getOrderDao() {
        return new JdbcOrderDao(connection);
    }

    @Override
    public RouteDao getRouteDao() {
        return new JdbcRouteDao(connection);
    }

    @Override
    public UserDao getUserDao() {
        return new JdbcUserDao(connection);
    }
}
