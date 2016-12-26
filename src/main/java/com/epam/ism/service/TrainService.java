package com.epam.ism.service;

import com.epam.ism.dao.OrderDAO;
import com.epam.ism.dao.TrainDAO;
import com.epam.ism.dao.jdbc.JdbcDAOFactory;
import com.epam.ism.dao.jdbc.JdbcDAOUtil;
import com.epam.ism.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TrainService {
    final static Logger logger = LoggerFactory.getLogger(TrainService.class);

    public List<Train> findAll(Station departureFrom, Station arrivalTo, Date departureDate) throws ServiceException {
        List<Train> list = JdbcDAOUtil.getEmptyList(Train.class);

        JdbcDAOFactory daoFactory = JdbcDAOFactory.get("railways_db.jdbc");
        logger.info("JdbcDAOFactory has been obtained successfully: " + daoFactory);

        daoFactory.getMainRouteDAO(); // for executing static block.

        TrainDAO trainDAO = daoFactory.getTrainDAO();
        logger.info("TrainDAO has been obtained successfully: " + trainDAO);
        List<Train> trains = trainDAO.list();
        logger.info("List<Train> has been obtained successfully: " + trains);

        RouteService routeService = new RouteService();
        logger.info("RouteService instance has been created: " + routeService);

        for (Train train : trains) {
            MainRoute mainRoute = train.getMainRoute();
            boolean exist = routeService.exist(mainRoute,departureFrom,arrivalTo);

            if (exist) {
                list.add(train);
                logger.info("Relevant train has been founded and added in the list. " + train.getName());
            }
        }

        return list;
    }

    public Map<String,List<Place>> findPlaces(String trainName) {
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
                    places = JdbcDAOUtil.getEmptyList(Place.class);
                    places.add(place);
                }

                map.put(carriageType,places);
            }
        }

        return map;
    }
}
