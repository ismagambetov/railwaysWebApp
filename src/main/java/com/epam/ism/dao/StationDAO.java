package com.epam.ism.dao;

import com.epam.ism.entity.Station;

import java.util.List;

/**
 * This interface represents a contract for a DAO for the {@link Station} model.
 *
 * @author IDS.
 */
public interface StationDAO extends GenericDAO<Station> {

    void add(Station station);
    Station createAndGet(String stationName);
    List<Station> getList();
    Station getByName(String name);

}
