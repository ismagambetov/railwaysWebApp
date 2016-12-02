package com.epam.ism.dao;

import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.User;

/**
 * This interface represents a contract for a DAO for the {@link User} model.
 * Note that all methods which returns the {@link User} from DB, will not
 * fill the model with the password, due to security reasons.
 *
 * @author IDS.
 */
public interface UserDAO<T extends User> extends GenericDAO<User> {

    /**
     * Returns the user from the database matching the given ID and password, otherwise null.
     * @param email The email of the user to be returned.
     * @param password The password of the user to be returned.
     * @return The user from the database, otherwise null.
     * @throws DAOException If something fails at database level.
     */
    T find(String email, String password) throws DAOException;

    /**
     * Returns true if the given email address exists in the database.
     * @param email The email address that will be checked in the database.
     * @return True if the given email address exists in the database.
     * @throws DAOException If something fails at database level.
     */
    boolean existEmail(String email) throws DAOException;

    /**
     * Change the password of the given user. The user ID must not be null, otherwise it
     * will be throw IllegalArgumentException.
     * @param entity The user to change the password for.
     * @throws IllegalArgumentException If the user ID is null.
     * @throws DAOException If something fails at database level.
     */
    void changePassword(T entity) throws IllegalArgumentException, DAOException;

}
