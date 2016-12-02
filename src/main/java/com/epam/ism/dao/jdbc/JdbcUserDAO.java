package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.UserDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.User;


/**
 * This class represents a concrete JDBC implementation of the {@link UserDAO} interface
 * and is extended by {@link AbstractJdbcDAO} abstract class.
 *
 * @author IDS.
 */
public class JdbcUserDAO<T extends User> extends AbstractJdbcDAO<User> implements UserDAO<T> {

    /**
     * Returns the user from the database matching the given ID and password, otherwise null.
     * @param email The email of the user to be returned.
     * @param password The password of the user to be returned.
     * @return The user from the database, otherwise null.
     * @throws DAOException If something fails at database level.
     */
    @Override
    public T find(String email, String password) throws DAOException {
        return null;
    }

    /**
     * Returns true if the given email address exists in the database.
     * @param email The email address that will be checked in the database.
     * @return True if the given email address exists in the database.
     * @throws DAOException If something fails at database level.
     */
    @Override
    public boolean existEmail(String email) throws DAOException {
        return false;
    }

    /**
     * Change the password of the given user. The user ID must not be null, otherwise it
     * will be throw IllegalArgumentException.
     * @param entity The user to change the password for.
     * @throws IllegalArgumentException If the user ID is null.
     * @throws DAOException If something fails at database level.
     */
    @Override
    public void changePassword(T entity) throws IllegalArgumentException, DAOException {

    }

}
