package com.epam.ism.service;

import com.epam.ism.dao.MainRouteDAO;
import com.epam.ism.dao.jdbc.JdbcDAOFactory;
import com.epam.ism.entity.MainRoute;
import com.epam.ism.entity.Station;

import java.util.List;

public class RouteService {
    private JdbcDAOFactory daoFactory;
    private MainRouteDAO mainRouteDAO;

    public RouteService() {
        daoFactory = JdbcDAOFactory.get("railways_db.jdbc");
        mainRouteDAO = daoFactory.getMainRouteDAO();
    }

    public MainRoute getRoute(Station from, Station to) {
        return mainRouteDAO.findByStations(from, to);
    }


    public void add(List<Object> arguments) {
        // TODO: 22.12.2016
        MainRoute route = new MainRoute();
//        route.setFrom(arguments.get(0));
//        route.setTo(arguments.get(1));
        //..................

        mainRouteDAO.create(route);

    }

}
