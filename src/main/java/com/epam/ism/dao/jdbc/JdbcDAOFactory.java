package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.*;

public class JdbcDAOFactory extends DAOFactory {

    @Override
    public TrainDAO getTrainDAO() {
        return new JdbcTrainDAO();
    }

    @Override
    public StationDAO getStationDAO() {
        return new JdbcStationDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new JdbcOrderDAO();
    }

    @Override
    public MainRouteDAO getMainRouteDAO() {
        return new JdbcMainRouteDAO();
    }

    @Override
    public UserDAO getUserDAO() { return new JdbcUserDAO(); }
}
