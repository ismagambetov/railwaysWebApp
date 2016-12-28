package com.epam.ism.service;

import com.epam.ism.dao.DAOFactory;
import com.epam.ism.dao.StationDAO;
import com.epam.ism.entity.Station;

public class StationService {

    public Station find(String name) {
        DAOFactory factory = DAOFactory.getFactory();
        StationDAO stationDAO = factory.getStationDAO();
        return stationDAO.find(name);
    }

}
