package com.epam.ism.dao;

import com.epam.ism.entity.*;

import java.util.List;

/**
 * This interface represents a contract for a DAO for the {@link MainRoute} model.
 *
 * @author IDS.
 */
public interface MainRouteDAO extends GenericDAO<MainRoute> {

    MainRoute findByStations(Station from, Station to);

}
