package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.RouteDao;
import com.epam.ism.entity.Course;
import com.epam.ism.entity.Route;
import com.epam.ism.entity.Station;
import com.epam.ism.entity.Train;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class JdbcRouteDao extends AbstractJdbcDao<Route> implements RouteDao {


    public JdbcRouteDao(Connection connection) {
        super(connection);
    }

    @Override
    public Object[] generateValuesForCreate(Route entity) {
        return new Object[0];
    }

    @Override
    public Route map(ResultSet rs) throws SQLException {
        Route route = new Route();

        Course course = new Course();
        Station departureStation = new Station();
        departureStation.setName(rs.getString(3));

        Station arrivalStation = new Station();
        arrivalStation.setName(rs.getString(4));

        course.setDepartureStation(departureStation);
        course.setArrivalStation(arrivalStation);

        Train train = new Train();

        train.setName(rs.getString(9));

        route.setId(rs.getLong(1));
        route.setOrder_id(rs.getLong(2));
        route.setCourse(course);
        route.setDepartureTime(rs.getString(5));
        route.setArrivalTime(rs.getString(6));
        route.setCost1(rs.getDouble(7));
        route.setCost2(rs.getDouble(8));
        route.setTrain(train);
        route.setMainRoute(rs.getBoolean(10));

        return route;
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
    public String findByIdQuery() {
        return null;
    }

    @Override
    public String findByNameQuery() {
        return null;
    }

    @Override
    public String deleteQuery() {
        return "DELETE FROM routes WHERE id = ?";
    }

    @Override
    public String listQuery() {
        return "SELECT * FROM routes";
    }

}
