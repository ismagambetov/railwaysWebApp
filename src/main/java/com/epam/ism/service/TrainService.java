package com.epam.ism.service;

import com.epam.ism.dao.OrderDAO;
import com.epam.ism.dao.TrainDAO;
import com.epam.ism.dao.jdbc.JdbcDAOFactory;
import com.epam.ism.dao.jdbc.JdbcDAOUtil;
import com.epam.ism.entity.*;

import java.util.*;

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

    public Map<String,List<Place>> findPlaces(String trainName) {
      //  List<Place> list = JdbcDAOUtil.getEmptyList(Place.class);
        Map<String,List<Place>> map = new HashMap<>();
        String carriageType;

        JdbcDAOFactory daoFactory = JdbcDAOFactory.get("railways_db.jdbc");
        TrainDAO trainDAO = daoFactory.getTrainDAO();
        Train train = trainDAO.find(trainName);

        OrderDAO orderDAO = daoFactory.getOrderDAO();
        List<Order> orders = orderDAO.list();

        for (Order order : orders) {
            if (order.getTrain().equals(train)) {
                Place place = new Place();
                place.setPlace(order.getPlaceNumber());
                place.setBooked(true);

                carriageType = order.getCarriage();
                if (order.isLux()) {
                    carriageType += "L";
                }

                List<Place> places = map.get(carriageType);
                if (places != null) {
                    places.add(place);
                } else {
                    places = new ArrayList<>();
                    places.add(place);
                }

                map.put(carriageType,places);
            }
        }

        return map;
    }
}
