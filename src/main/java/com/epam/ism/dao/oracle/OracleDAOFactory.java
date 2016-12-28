package com.epam.ism.dao.oracle;

import com.epam.ism.dao.*;

import java.sql.Connection;

public class OracleDAOFactory extends DAOFactory {

    @Override
    public StationDAO getStationDAO() {
        return new OracleStationDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new OracleOrderDAO();
    }

    @Override
    public TrainDAO getTrainDAO() {
        return new OracleTrainDAO();
    }

    @Override
    public MainRouteDAO getMainRouteDAO() {
        return new OracleMainRouteDAO();
    }

    @Override
    public UserDAO getUserDAO() {
        return new OracleUserDAO();
    }
}
