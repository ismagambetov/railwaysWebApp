package com.epam.ism.dao.oracle;

import com.epam.ism.dao.*;

public class OracleDaoFactory extends DaoFactory {

    @Override
    public StationDao getStationDAO() {
        return new OracleStationDao();
    }

    @Override
    public OrderDao getOrderDAO() {
        return new OracleOrderDao();
    }

    @Override
    public TrainDao getTrainDAO() {
        return new OracleTrainDao();
    }

    @Override
    public MainRouteDao getMainRouteDAO() {
        return new OracleMainRouteDao();
    }

    @Override
    public UserDao getUserDAO() {
        return new OracleUserDao();
    }
}
