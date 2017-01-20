package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.*;

public class JdbcDaoFactory extends DaoFactory {

    @Override
    public TrainDao getTrainDao(DaoManager daoManager) {
        return new JdbcTrainDao(daoManager.getTxConnection());
    }

    @Override
    public StationDao getStationDao(DaoManager daoManager) {
        return new JdbcStationDao(daoManager.getTxConnection());
    }

    @Override
    public OrderDao getOrderDao(DaoManager daoManager) {
        return new JdbcOrderDao(daoManager.getTxConnection());
    }

    @Override
    public RouteDao getRouteDao(DaoManager daoManager) {
        return new JdbcRouteDao(daoManager.getTxConnection());
    }

    @Override
    public UserDao getUserDao(DaoManager daoManager) {
        return new JdbcUserDao(daoManager.getTxConnection());
    }

    @Override
    public WagonDao getWagonDao(DaoManager daoManager) {
        return new JdbcWagonDao(daoManager.getTxConnection());
    }
}
