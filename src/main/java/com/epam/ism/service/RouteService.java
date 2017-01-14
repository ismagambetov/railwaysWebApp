package com.epam.ism.service;

import com.epam.ism.dao.DaoCommand;
import com.epam.ism.dao.DaoFactory;
import com.epam.ism.dao.DaoManager;
import com.epam.ism.dao.RouteDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.Route;
import com.epam.ism.entity.Station;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public List<Route> findAll(Station departureStation, Station arrivalStation) throws ServiceException {


        Long depStationId = departureStation.getId();
        Long arrStationId = arrivalStation.getId();

        String query = "select DISTINCT null as id, null as order_id, s1.name as dep_st, s2.name as arr_st, t1.dep_time, t2.arr_time, \n" +
                "\tnull as cost1, null as cost2, t.name, null as main_route\n" +
                "\n" +
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
                logger.info("factory.getRouteDao(): " + routeDao);
                return routeDao.list(query, depStationId, arrStationId);
            }
        });
    }
}
