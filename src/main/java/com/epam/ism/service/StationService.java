package com.epam.ism.service;

import com.epam.ism.dao.DaoFactory;
import com.epam.ism.dao.StationDao;
import com.epam.ism.entity.Station;

public class StationService {

    public Station find(String name) {
        DaoFactory factory = DaoFactory.getFactory();
        StationDao stationDAO = factory.getStationDAO();
        return stationDAO.find(name);
    }

}
