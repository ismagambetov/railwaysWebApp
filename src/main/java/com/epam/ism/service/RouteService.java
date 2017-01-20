package com.epam.ism.service;

import com.epam.ism.dao.DaoCommand;
import com.epam.ism.dao.DaoFactory;
import com.epam.ism.dao.DaoManager;
import com.epam.ism.dao.RouteDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.*;
import com.epam.ism.utils.RowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RouteService {
    final static Logger logger = LoggerFactory.getLogger(RouteService.class);

    private DaoFactory factory;
    private DaoManager daoManager;

    public RouteService() {
        factory = DaoFactory.getFactory();
        logger.info("DaoFactory.getFactory() from class RouteService: " + factory);
        daoManager = DaoFactory.getDaoManager();
    }

    @SuppressWarnings(value = "unchecked")
    public List<Route> findAll(Station departureStation, Station arrivalStation) throws ServiceException {
        int depStationId = departureStation.getId();
        int arrStationId = arrivalStation.getId();

        String query = "select DISTINCT t.id as train_id,t.name,s1.name as dep_st,s2.name as arr_st,t1.dep_time,t2.arr_time\n" +
                " from\n" +
                "\n" +
                "\t(select r1.id, r1.order_id, c.dep_station_id, r1.cost1, r1.cost2, \n" +
                "\t   r1.dep_time, r1.arr_time, r1.parking_time, r1.train_id, r1.main_route from routes as r1\n" +
                "\t\tleft join courses as c on r1.course_id = c.id\n" +
                "\t\tleft join trains as t on r1.train_id = t.id\n" +
                "\t\twhere c.dep_station_id = ?) as t1\n" +
                "\tinner join (\n" +
                "\tselect r2.id, r2.train_id, c.arr_station_id, r2.arr_time from routes as r2\n" +
                "\t\tleft join courses as c on r2.course_id = c.id\n" +
                "\t\twhere c.arr_station_id = ?) as t2\n" +
                "\t\ton t1.train_id = t2.train_id\n" +
                "        \n" +
                "left join stations as s1 on t1.dep_station_id = s1.id\n" +
                "left join stations as s2 on t2.arr_station_id = s2.id\n" +
                "left join trains as t on t1.train_id = t.id";

        return (List<Route>) daoManager.transactionAndReturnCon(new DaoCommand() {
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
                        departureStation.setName(rs.getString(3));
                        Station arrivalStation = new Station();
                        arrivalStation.setName(rs.getString(4));
                        course.setDepartureStation(departureStation);
                        course.setArrivalStation(arrivalStation);

                        route.setCourse(course);

                        route.setDepartureTime(rs.getString(5));
                        route.setArrivalTime(rs.getString(6));

                        return route;
                    }
                });



                return routeDao.list(query, depStationId, arrStationId);
            }
        });
    }
}
