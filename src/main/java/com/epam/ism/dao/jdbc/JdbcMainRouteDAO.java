package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.MainRouteDAO;
import com.epam.ism.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JdbcMainRouteDAO extends AbstractJdbcDAO<MainRoute> implements MainRouteDAO {
    //There are methods beneath to be used just for testing.
    private List<MainRoute> mainRoutes = new ArrayList<>();
    private static Long i = 0L;

    @Override
    public MainRoute createAndGet(Station stationFrom, Station stationTo, String departuteTime, String arrivalTime,
         Double interval, Double priceForCloseSection, Double priceForOpenSection, Train train, List<Route> routes) {

        MainRoute mainRoute = new MainRoute();
        mainRoute.setId(i);
        mainRoute.setFrom(stationFrom);
        mainRoute.setTo(stationTo);
        mainRoute.setDepartureTime(departuteTime);
        mainRoute.setArrivalTime(arrivalTime);
        mainRoute.setInterval(interval);
        mainRoute.setPriceForCloseSection(priceForCloseSection);
        mainRoute.setPriceForOpenSection(priceForOpenSection);
        mainRoute.setTrain(train);

        return mainRoute;
    }

    @Override
    public void add(MainRoute mainRoute) {
        mainRoutes.add(mainRoute);
    }

    @Override
    public List<MainRoute> getList() {
        return mainRoutes;
    }

    @Override
    public List<Train> findByRequest(List<MainRoute> mainRoutes, Request request) {
        List<Train> trains = new ArrayList<>();
        for (MainRoute mainRoute : mainRoutes) {
            Train train = mainRoute.getTrain();
            LinkedList<Route> routes = mainRoute.getRoutes();

            if (mainRoute.getFrom().equals(request.getFrom())) {
                if (mainRoute.getTo().equals(request.getTo())) {
                    trains.add(train);
                } else {
                    for (Route route : routes) {
                        if (mainRoute.getTo().equals(route.getTo())) {
                            trains.add(train);
                        }
                    }
                }


            }


        }


        return trains;
    }

    //There is a code beneath, which will be used at database level.
    public JdbcMainRouteDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Object[] generateValuesForCreate(MainRoute entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(MainRoute entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(MainRoute entity) {
        return new Object[0];
    }

    @Override
    public MainRoute map(ResultSet resultSet) throws SQLException {
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
