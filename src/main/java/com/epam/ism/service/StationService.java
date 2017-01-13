package com.epam.ism.service;

import com.epam.ism.dao.DaoCommand;
import com.epam.ism.dao.DaoFactory;
import com.epam.ism.dao.DaoManager;
import com.epam.ism.dao.StationDao;
import com.epam.ism.entity.Station;

import java.sql.SQLException;

public class StationService {

    public Station find(String name) {

        DaoFactory daoFactory = DaoFactory.getFactory();
        DaoManager daoManager = DaoFactory.getDaoManager();

        return (Station) daoManager.transactionAndReturnCon(new DaoCommand() {
            @Override
            public Object execute() throws SQLException {
                StationDao stationDAO = daoFactory.getStationDao();
                return stationDAO.findByName(name);
            }
        });
    }

    public void delete(Station station) {
        DaoFactory daoFactory = DaoFactory.getFactory();
        DaoManager daoManager = DaoFactory.getDaoManager();

        daoManager.transactionAndReturnCon(new DaoCommand() {
            @Override
            public Object execute() throws SQLException {
                StationDao stationDAO = daoFactory.getStationDao();
                stationDAO.delete(station);
                return null;
            }
        });
    }

}
