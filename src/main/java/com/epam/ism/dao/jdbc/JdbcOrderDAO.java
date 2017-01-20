package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.OrderDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.*;
import com.epam.ism.utils.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class JdbcOrderDao extends AbstractJdbcDao<Order> implements OrderDao {

    public JdbcOrderDao(Connection connection) {
        super(connection);
    }

    @Override
    public Object[] generateValuesForCreate(Order entity) {
        return new Object[0];
    }

//    @Override
//    public Order map(ResultSet resultSet) throws SQLException {
//        Order order = new Order();
//        order.setId(resultSet.getInt(1));
//        order.setDepartureDate(JdbcDaoUtil.toUtilDate(resultSet.getDate(2)));
//
//        User user = new User();
//        user.setId(resultSet.getInt(3));
//        user.setRole(Role.PASSENGER);
//        order.setPassenger(user);
//
//        Train train = new Train();
//        train.setId(resultSet.getInt(4));
//        order.setTrain(train);
//
//        Wagon wagon = new Wagon();
//        wagon.setId(resultSet.getInt(5));
//        wagon.setWagonNum(resultSet.getInt(6));
//
//        order.setWagon(wagon);
//        order.setPlaceNumber(resultSet.getInt(6));
//
//        Station depStation = new Station();
//        depStation.setId(resultSet.getInt(7));
//        order.setDepartureStation(depStation);
//
//        Station arrStation = new Station();
//        arrStation.setId(resultSet.getInt(8));
//        order.setArrivalStation(arrStation);
//
//        order.setDepartureTime(resultSet.getString(9));
//        order.setArrivalTime(resultSet.getString(10));
//        order.setCost(resultSet.getInt(11));
//
//        return order;
//    }

    @Override
    public String insertQuery() {
        return null;
    }

    @Override
    public String updateQuery() {
        return null;
    }

    @Override
    public String findByIdQuery() {
        return null;
    }

    @Override
    public String findByNameQuery() {
        return null;
    }

    @Override
    public String deleteQuery() {
        return null;
    }

    @Override
    public String listQuery() {
        return null;
    }
}
