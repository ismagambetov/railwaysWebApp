package com.epam.ism.dao;

import com.epam.ism.entity.Order;
import com.epam.ism.entity.Place;
import com.epam.ism.entity.Train;

import java.util.Date;
import java.util.List;

/**
 * This interface represents a contract for a DAO for the {@link Order} model.
 *
 * @author IDS.
 */
public interface OrderDAO extends GenericDAO<Order> {

    List<Order> getList();
    List<Place> getBookedPlaces(Date departureDate, List<Train> trains, List<Order> orders);

}
