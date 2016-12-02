package com.epam.ism.dao;

import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.dao.jdbc.AbstractJdbcDAO;
import com.epam.ism.entity.Passenger;
import com.epam.ism.entity.User;

import java.util.List;

/**
 * This interface represents a contract for a DAO for the {@link Passenger} model.
 *
 * @author IDS.
 */

public interface PassengerDAO extends UserDAO<Passenger> {

    /**
     * Returns the Passenger from the database matching the given orderNumber, otherwise null.
     * @param orderNumber The orderNumber of the Passenger to be returned.
     * @return The Passenger from the database, otherwise null.
     * @throws DAOException If something fails at database level.
     */
    Passenger findByOrder(Long orderNumber) throws DAOException;

}
