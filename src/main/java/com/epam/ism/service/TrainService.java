package com.epam.ism.service;

import com.epam.ism.dao.DaoFactory;
import com.epam.ism.dao.OrderDao;
import com.epam.ism.dao.TrainDao;
import com.epam.ism.dao.jdbc.JdbcDaoUtil;
import com.epam.ism.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TrainService {
    final static Logger logger = LoggerFactory.getLogger(TrainService.class);
    private DaoFactory factory;

    public TrainService() {
        factory = DaoFactory.getFactory();
        logger.info(factory.getClass().getSimpleName() + " has been obtained successfully: " + factory);
    }

    public List<Train> findAll(Station departureFrom, Station arrivalTo, Date departureDate) throws ServiceException {
        // TODO: 28.12.2016 list should not be implemented in the service
        List<Train> list = JdbcDaoUtil.getEmptyList(Train.class);

        //factory.MainRouteDAO(); // for executing static block.

        TrainDao trainDAO = factory.getTrainDAO();
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

        DaoFactory factory = DaoFactory.getFactory();
        TrainDao trainDAO = factory.getTrainDAO();
        Train train = trainDAO.find(trainName);

        OrderDao orderDAO = factory.getOrderDAO();
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
                    places = JdbcDaoUtil.getEmptyList(Place.class);
                    places.add(place);
                }

                map.put(carriageType,places);
            }
        }

        return map;
    }
}
