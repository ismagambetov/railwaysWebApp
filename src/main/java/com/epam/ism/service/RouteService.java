package com.epam.ism.service;

import com.epam.ism.entity.MainRoute;
import com.epam.ism.entity.Route;
import com.epam.ism.entity.Station;

import java.util.List;

public class RouteService {

    public boolean exist(MainRoute mainRoute, Station from, Station to) {

        boolean b = compareStations(mainRoute, from, to);
        if (b) {
            return true;
        }

        List<Route> routes = mainRoute.getRoutes();
        for (Route route : routes) {
            b = compareStations(route, from, to);
            if (b) {
                return true;
            }
        }
        return false;
    }

    private boolean compareStations(MainRoute route, Station from, Station to) {
        if (route.getFrom().equals(from) &&
                route.getTo().equals(to)) {
            return true;
        }
        return false;
    }


}
