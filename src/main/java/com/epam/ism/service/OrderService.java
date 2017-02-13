package com.epam.ism.service;

import com.epam.ism.dao.*;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.Order;
import com.epam.ism.entity.Train;
import com.epam.ism.entity.User;
import com.epam.ism.entity.Wagon;
import com.epam.ism.utils.RowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderService {
    final static Logger logger = LoggerFactory.getLogger(TrainService.class);

    private DaoFactory factory;
    private DaoManager daoManager;

    public OrderService() {
        factory = DaoFactory.getFactory();
        logger.info("DaoFactory.getFactory() from class OrderService" + factory);
        daoManager = DaoFactory.getDaoManager();
    }

    @SuppressWarnings (value="unchecked")
    public List<Order> findOrders(Train train, Date departureDate) throws ServiceException {
        int trainId = train.getId();

        String query = "select w.wagon_num,o.place from orders as o\n" +
                "left join wagons as w on o.wagon_id=w.id\n" +
                " where o.train_id = ? and o.dep_date = ?";

        return (List<Order>) daoManager.transactionAndReturnCon(new DaoCommand() {
            @Override
            public Object execute() throws SQLException, DaoException {
                OrderDao orderDao = factory.getOrderDao(daoManager);
                orderDao.map(new RowMapper() {
                    @Override
                    public Order mapRow(ResultSet rs) throws SQLException {
                        Order order = new Order();

                        Wagon wagon = new Wagon();
                        wagon.setWagonNum(rs.getInt(1));

                        order.setWagon(wagon);
                        order.setPlaceNumber(rs.getInt(2));

                        return order;
                    }
                });

                return orderDao.list(query, trainId, departureDate);
            }
        });

    }

    public int calcCost() {

        return 0;
    }

    public User getUser() {
        return null;
    }
}
