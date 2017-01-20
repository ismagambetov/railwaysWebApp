package com.epam.ism.service;

import com.epam.ism.dao.*;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.*;
import com.epam.ism.utils.RowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TrainService {
    final static Logger logger = LoggerFactory.getLogger(TrainService.class);

    private DaoFactory factory;
    private DaoManager daoManager;

    public TrainService() {
        factory = DaoFactory.getFactory();
        logger.info("DaoFactory.getFactory() from class TrainService" + factory);
        daoManager = DaoFactory.getDaoManager();
    }


    public Train findByName(String trainName) throws DaoException {
        return (Train) daoManager.transactionAndReturnCon(new DaoCommand() {
            @Override
            public Object execute() throws SQLException, DaoException {
                TrainDao trainDao = factory.getTrainDao(daoManager);

                trainDao.map(new RowMapper() {
                    @Override
                    public Train mapRow(ResultSet rs) throws SQLException {
                        Train train = new Train();
                        train.setId(rs.getInt(1));
                        train.setName(rs.getString(2));

                        return train;
                    }
                });

                return trainDao.findByName(trainName);
            }
        });
    }


    public List<Wagon> getWagons(int trainId, Date departureDate) throws ServiceException {

        OrderService orderService = new OrderService();
        List<Order> orders = orderService.findOrders(trainId, departureDate);

        String query = "SELECT w.wagon_num,c.name,w.capacity FROM wagons as w\n" +
                "left join wagon_categories as c \n" +
                "on w.wagon_category_id = c.id \n" +
                "where w.train_id = ?\n" +
                "order by w.id";

        @SuppressWarnings (value="unchecked")
        List<Wagon> wagons = (List<Wagon>) daoManager.transactionAndReturnCon(new DaoCommand() {
            @Override
            public Object execute() throws SQLException, DaoException {

                WagonDao wagonDao = factory.getWagonDao(daoManager);

                wagonDao.map(new RowMapper<Wagon>() {
                    @Override
                    public Wagon mapRow(ResultSet rs) throws SQLException {
                        Wagon wagon = new Wagon();
                        wagon.setWagonNum(rs.getInt(1));
                        wagon.setWagonCategory(WagonCategory.valueOf(rs.getString(2).toUpperCase()));
                        wagon.setCapacity(rs.getInt(3));
                        wagon.setPlaces();
                        return wagon;
                    }
                });

                return wagonDao.list(query, trainId);
            }
        });

        for (Wagon wagon : wagons) {

            for (Order order : orders) {
                int wagonNum = order.getWagon().getWagonNum();
                int placeNum = order.getPlaceNumber();
                List<Place> places = wagon.getPlaces();

                if (wagon.getWagonNum() == wagonNum) {
                    Place place = places.get(placeNum - 1);
                    place.setBooked(true);
                }
            }
        }

        return wagons;
    }



}
