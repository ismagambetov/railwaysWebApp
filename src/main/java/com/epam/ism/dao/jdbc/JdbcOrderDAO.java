package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.OrderDAO;
import com.epam.ism.entity.Carriage;
import com.epam.ism.entity.Order;
import com.epam.ism.entity.Place;
import com.epam.ism.entity.Train;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class JdbcOrderDAO extends AbstractJdbcDAO<Order> implements OrderDAO {
    //There are methods beneath to be used just for testing.
    private List<Order> orders = new ArrayList<>();
    private static Long i = 0L;

    @Override
    public List<Order> getList() {
        return orders;
    }

    @Override
    public List<Place> getBookedPlaces(Date departureDate, List<Train> trains, List<Order> orders) {
        HashMap<Carriage,List<Integer>> places = new HashMap<>();
        //HashMap<Train,List<Place>> map = new HashMap<>();
        HashMap<Train,HashMap<Carriage,List<Integer>>> map = new HashMap<>();

        List<Order> ordersByTrains = findOrdersByTrains(orders, trains);
        List<Order> ordersByDate = findOrdersByDate(ordersByTrains, departureDate);




//        for (Order order : ordersByDate) {
//            Train train = order.getTrain();
//            Carriage carriage = order.getCarriage();
//            int placeNumber = order.getPlaceNumber();
//            Place place = new Place(carriage, placeNumber);
//
//            if (map.containsKey(train)) {
//                places = map.get(train);
//
//                if (places.containsKey(carriage)) {
//                    List<Integer> placeNumbers = places.get(carriage);
//                    placeNumbers.add(placeNumber);
//
//                }
//
//
//
//
//                put(map,train,places,place);
//
//            } else {
//                put(map,train,places,place);
//            }
//
//        }

        return places;
    }

    private void put(HashMap<Train,List<Place>> map, Train train, List<Place> places, Place place) {
        places.add(place);
        map.put(train,places);
    }


    private List<Order> findOrdersByTrains(List<Order> orders, List<Train> trains) {

        List<Order> list = new ArrayList<>();
        for (Order order : orders) {

            for (Train train : trains) {

                if (order.getTrain().equals(train)) {
                    list.add(order);
                }

            }


        }
        return list;
    }

    private List<Order> findOrdersByDate(List<Order> orders, Date date) {
        List<Order> list = new ArrayList<>();
        for (Order order : orders) {

            if (order.getDepartureDate().equals(date)) {
                list.add(order);
            }
        }

        return list;
    }

    //There is a code beneath, which will be used at database level.
    public JdbcOrderDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Object[] generateValuesForCreate(Order entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(Order entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(Order entity) {
        return new Object[0];
    }

    @Override
    public Order map(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public String insertQuery() {
        return null;
    }

    @Override
    public String updateQuery() {
        return null;
    }

    @Override
    public String findQuery() {
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
