package com.epam.ism.service;

import com.epam.ism.dao.StationDAO;
import com.epam.ism.dao.jdbc.JdbcDAOFactory;
import com.epam.ism.entity.Station;

public class StationService {

    public Station find(String name) {
        JdbcDAOFactory daoFactory = JdbcDAOFactory.get("railways_db.jdbc");
        StationDAO stationDAO = daoFactory.getStationDAO();
        return stationDAO.find(name);
    }

}
