package com.epam.ism.dao;

import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.User;

/**
 * This interface represents a contract for a DAO for the {@link User} model.
 * Note that all methods which returns the {@link User} from DB, will not
 * fill the model with the password, due to security reasons.
 *
 * @author IDS.
 */
public interface UserDao extends GenericDao<User> {

    /**
     * Returns the User from the database matching the given ID and password, otherwise null.
     * @param email The email of the User to be returned.
     * @param password The password of the User to be returned.
     * @return The User from the database, otherwise null.
     * @throws DaoException If something fails at database level.
     */
    User find(String email, String password) throws DaoException;

    /**
     * Returns the User from database matching the given PersonalCode, otherwise null.
     * @param personalCode The PersonalCode of the User to be returned.
     * @return The User from the database, otherwise null.
     * @throws DaoException If something fails at database level.
     */
    User find(String personalCode) throws DaoException;

    /**
     * Returns true if the given email address exists in the database.
     * @param email The email address that will be checked in the database.
     * @return True if the given email address exists in the database.
     * @throws DaoException If something fails at database level.
     */
    boolean existEmail(String email) throws DaoException;

    /**
     * Change the password of the given User. The User ID must not be null, otherwise it
     * will be throw IllegalArgumentException.
     * @param user The User to change the password for.
     * @throws IllegalArgumentException If the User ID is null.
     * @throws DaoException If something fails at database level.
     */
    void changePassword(User user) throws IllegalArgumentException, DaoException;



}
