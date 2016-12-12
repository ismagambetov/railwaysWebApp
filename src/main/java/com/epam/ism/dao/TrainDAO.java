package com.epam.ism.dao;

import com.epam.ism.entity.Train;

import java.util.List;

/**
 * This interface represents a contract for a DAO for the {@link Train} model.
 *
 * @author IDS.
 */
public interface TrainDAO extends GenericDAO<Train> {
    Train createAndGet(String trainName);
    void add(Train train);
    List<Train> getList();
    Train getByName(String name);


}
