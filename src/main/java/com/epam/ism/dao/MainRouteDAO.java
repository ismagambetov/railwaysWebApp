package com.epam.ism.dao;

import com.epam.ism.entity.*;

import java.util.List;

/**
 * This interface represents a contract for a DAO for the {@link MainRoute} model.
 *
 * @author IDS.
 */
public interface MainRouteDAO extends GenericDAO<MainRoute> {
    MainRoute createAndGet(Station stationFrom, Station stationTo, String departuteTime,
                           String arrivalTime, Double interval, Double priceForCloseSection, Double priceForOpenSection,
                           Train train, List<Route> routes);

    void add(MainRoute mainRoute);

    List<MainRoute> getList();

    List<Train> findByRequest(List<MainRoute> mainRoutes, Request request);

}
