package com.epam.ism.service;

import com.epam.ism.dao.DaoCommand;
import com.epam.ism.dao.DaoFactory;
import com.epam.ism.dao.DaoManager;
import com.epam.ism.dao.RouteDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.*;
import com.epam.ism.utils.RowMapper;
import com.epam.ism.utils.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.ism.dao.jdbc.JdbcDaoUtil.*;

public class RouteService {
    final static Logger logger = LoggerFactory.getLogger(RouteService.class);

    private DaoFactory factory;
    private DaoManager daoManager;

    public RouteService() {
        factory = DaoFactory.getFactory();
        logger.info("DaoFactory.getFactory() from class RouteService: " + factory);
        daoManager = DaoFactory.getDaoManager();
        logger.info("DaoFactory.getDaoManager() from class RouteService: " + daoManager);
    }

    @SuppressWarnings(value = "unchecked")
    public List<Route> findAll(Station departureStation, Station arrivalStation, String departureDate) throws ServiceException {
        int depStationId = departureStation.getId();
        int arrStationId = arrivalStation.getId();

        String query = "Select DISTINCT " +
                "t.id as train_id," +
                "t.name," +
                "s1.id as dep_st_id," +
                "s1.name as dep_st," +
                "s2.id as arr_st_id," +
                "s2.name as arr_st," +
                "t1.dep_time," +
                "t2.arr_time "+

                "from " +

                "(select r1.id, c.dep_station_id, r1.cost1, r1.cost2," +
                "r1.dep_time, r1.arr_time, r1.parking_time, r1.train_id, r1.main_route from routes as r1 " +
                "left join courses as c on r1.course_id = c.id " +
                "left join trains as t on r1.train_id = t.id " +
                "where c.dep_station_id = ?) as t1 " +

                "inner join (" +
                "select r2.id, r2.train_id, c.arr_station_id, r2.arr_time from routes as r2 " +
                "left join courses as c on r2.course_id = c.id " +
                "where c.arr_station_id = ?) as t2 " +
                "on t1.train_id = t2.train_id " +

                "left join stations as s1 on t1.dep_station_id = s1.id " +
                "left join stations as s2 on t2.arr_station_id = s2.id " +
                "left join trains as t on t1.train_id = t.id";

        List<Route> routes = (List<Route>) daoManager.transactionAndReturnCon(new DaoCommand() {
            @Override
            public Object execute() throws SQLException, DaoException {
                RouteDao routeDao = factory.getRouteDao(daoManager);

                routeDao.map(new RowMapper() {
                    @Override
                    public Route mapRow(ResultSet rs) throws SQLException {
                        Route route = new Route();

                        Train train = new Train();
                        train.setId(rs.getInt(1));
                        train.setName(rs.getString(2));
                        route.setTrain(train);

                        Course course = new Course();
                        Station departureStation = new Station();
                        departureStation.setId(rs.getInt(3));
                        departureStation.setName(rs.getString(4));
                        Station arrivalStation = new Station();
                        arrivalStation.setId(rs.getInt(5));
                        arrivalStation.setName(rs.getString(6));

                        course.setDepartureStation(departureStation);
                        course.setArrivalStation(arrivalStation);
                        route.setCourse(course);

                        route.setDepartureDate(DateTimeUtil.getDateTime(departureDate,rs.getString(7)));
                        //in order to set arrival date, we have to make some manipulations with travel times.
                        //see below.

                        return route;
                    }
                });

                return routeDao.list(query, depStationId, arrStationId);
            }
        });

        //finishing filling up routes
        for (Route route : routes) {
            int trainId = route.getTrain().getId();
            int[] result = calc(trainId, depStationId, arrStationId);
            if (result.length > 1) {
                int distance = result[0];
                int travelTimeInMls = result[1];
                Date depDate = route.getDepartureDate();

                route.getCourse().setDistance(distance);
                route.setTravelTime(DateTimeUtil.getParsedTravelTime(travelTimeInMls));
                route.setArrivalDate(DateTimeUtil.getArrivalDateTime(depDate,travelTimeInMls));
            }
        }

        return routes;
    }

    @SuppressWarnings(value="unchecked")
    private int[] calc(int trainId,int depStationId,int arrStationId) {
        ArrayList<Integer> ids = new ArrayList<>();

        // this query used to identify routes' ids in the 'Routes' table by depStationId and arrStationId.
        // Thus, found ids will be used to create range of routes which help us to sum
        // a distance between to stations. {see String query2}
        String query1 = "SELECT sec_id FROM routes " +
                "left join courses as c on course_id=c.id " +
                "where train_id=? and (c.dep_station_id = ? or c.arr_station_id = ?) and main_route = 0 " +
                "order by sec_id";

        ids = (ArrayList<Integer>) daoManager.transactionAndReturnCon(new DaoCommand() {
            @Override
            public Object execute() throws SQLException, DaoException {
                ArrayList<Integer> ids = new ArrayList<>();
                Connection connection = daoManager.getTxConnection();
                PreparedStatement statement = prepareStatement(connection,query1,false,trainId,depStationId,arrStationId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ids.add(resultSet.getInt(1));
                }

                return ids;
            }
        });

        String query2 = "SELECT SUM(c.distance) as distance, SUM(r.travel_time) as travel_time FROM routes as r " +
                        "left join courses as c on r.course_id=c.id " +
                        "where r.train_id=? and r.sec_id between ? and ?";

        final ArrayList<Integer> finalIds = ids;
        return (int[]) daoManager.transactionAndReturnCon(new DaoCommand() {
            @Override
            public Object execute() throws SQLException, DaoException {
                Connection connection = daoManager.getTxConnection();
                PreparedStatement statement = prepareStatement(connection, query2, false, trainId,
                                                                                finalIds.get(0), finalIds.get(1));
                ResultSet resultSet = statement.executeQuery();
                int[] result = new int[2];
                if (resultSet.next()) {
                    result[0] = resultSet.getInt(1);
                    result[1] = resultSet.getInt(2);

                    return result;
                }
                return result;
            }
        });
    }


    public Route getRoute(Train train, Station depStation, Station arrStation,
                          String distance, String depTime, String arrTime) {

        int dist = Integer.parseInt(distance);

        Route route = new Route();
        route.setTrain(train);
//        route.setDepartureTime(depTime);
//        route.setArrivalTime(arrTime);

        Course course = new Course();
        course.setDepartureStation(depStation);
        course.setArrivalStation(arrStation);
        course.setDistance(dist);

        route.setCourse(course);


        return route;
    }
}
