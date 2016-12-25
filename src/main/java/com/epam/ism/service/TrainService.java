package com.epam.ism.service;

import com.epam.ism.dao.TrainDAO;
import com.epam.ism.dao.jdbc.JdbcDAOFactory;
import com.epam.ism.dao.jdbc.JdbcDAOUtil;
import com.epam.ism.entity.MainRoute;
import com.epam.ism.entity.Station;
import com.epam.ism.entity.Train;

import java.util.Date;
import java.util.List;

public class TrainService {

    public List<Train> findAll(Station from, Station to, Date date) {
        List<Train> list = JdbcDAOUtil.getEmptyList(Train.class);

        JdbcDAOFactory daoFactory = JdbcDAOFactory.get("railways_db.jdbc");
        daoFactory.getMainRouteDAO(); // for executing static block.
        TrainDAO trainDAO = daoFactory.getTrainDAO();
        List<Train> trains = trainDAO.list();

        RouteService routeService = new RouteService();

        for (Train train : trains) {
            MainRoute mainRoute = train.getMainRoute();
            boolean exist = routeService.exist(mainRoute,from,to);

            if (exist) list.add(train);
        }

        return list;
    }
}
