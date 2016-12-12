package com.epam.ism.dao;

import com.epam.ism.entity.Carriage;

import java.util.List;

/**
 * This interface represents a contract for a DAO for the {@link Carriage} model.
 *
 * @author IDS.
 */

public interface CarriageDAO extends GenericDAO<Carriage> {
    void add(Carriage carriage);
    Carriage createAndGet();
    List<Carriage> getList();
}
