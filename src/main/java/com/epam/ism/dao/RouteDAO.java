package com.epam.ism.dao;

import com.epam.ism.entity.Route;
import com.epam.ism.entity.Station;

import java.util.List;

/**
 * This interface represents a contract for a DAO for the {@link Route} model.
 *
 * @author IDS.
 */
public interface RouteDAO extends GenericDAO<Route> {

    void add(Station stationFrom, Station stationTo, String departuteTime,
             String arrivalTime, Double interval, int parkingTime);

    List<Route> getList();

}
