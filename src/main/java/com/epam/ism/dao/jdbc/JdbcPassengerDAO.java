package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.PassengerDAO;
import com.epam.ism.dao.UserDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.Passenger;

/**
 * This class represents a concrete JDBC implementation of the {@link UserDAO} interface.
 *
 * @author IDS.
 */
public class JdbcPassengerDAO extends JdbcUserDAO<Passenger> implements PassengerDAO {

    @Override
    public Passenger findByOrder(Long orderNumber) throws DAOException {
        return null;
    }
}
