package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.RouteDAO;
import com.epam.ism.entity.Route;
import com.epam.ism.entity.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class JdbcRouteDAO extends AbstractJdbcDAO<Route> implements RouteDAO {

    //There are methods beneath to be used just for testing.
    private List<Route> routes = new ArrayList<>();
    private static Long i;

    @Override
    public void add(Station stationFrom, Station stationTo, String departuteTime,
                                                          String arrivalTime, Double interval, int parkingTime) {

        Route route = new Route();
        route.setId(i);
        route.setFrom(stationFrom);
        route.setTo(stationTo);
        route.setDepartureTime(departuteTime);
        route.setArrivalTime(arrivalTime);
        route.setInterval(interval);
        route.setParkingTime(parkingTime);

        routes.add(route);

    }

    @Override
    public List<Route> getList() {
        return routes;
    }

    //There is a code beneath, which will be used at database level.
    public JdbcRouteDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Object[] generateValuesForCreate(Route entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(Route entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(Route entity) {
        return new Object[0];
    }

    @Override
    public Route map(ResultSet resultSet) throws SQLException {
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
