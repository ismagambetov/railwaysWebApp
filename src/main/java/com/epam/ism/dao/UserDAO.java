package com.epam.ism.dao;

import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.User;
import java.util.List;

/**
 * This interface represents a contract for a DAO for the {@link User} model.
 * Note that all methods which returns the {@link User} from DB, will not
 * fill the model with the password, due to security reasons.
 *
 * @author IDS.
 */
public interface UserDAO {

    /**
     * Returns the user from the database matching the given ID, otherwise null.     *
      * @param id The ID of the user to be returned.
     * @return The user from the database, otherwise null.
     * @throws DAOException If something fails at database level.
     */
    User find(Long id) throws DAOException;

    /**
     * Returns the user from the database matching the given ID and password, otherwise null.
     * @param email The email of the user to be returned.
     * @param password The password of the user to be returned.
     * @return The user from the database, otherwise null.
     * @throws DAOException If something fails at database level.
     */
    User find(String email, String password) throws DAOException;

    /**
     * Returns a list of all users from the database ordered by user ID. The list is never null and
     * is empty when database does not contain any user.
     * @return A list of all users from the database.
     * @throws DAOException If something fails at database level.
     */
    List<User> list() throws DAOException;

    /**
     * Create the given user in the database. The user ID must be null, otherwise it
     * will be throw IllegalArgumentException.
     * @param user The user to be created in the database.
     * @throws IllegalArgumentException If the user ID is not null.
     * @throws DAOException If something fails at database level.
     */
    void create(User user) throws IllegalArgumentException, DAOException;

    /**
     * Update the given user in the database. The user ID must not be null, otherwise it
     * will be throw IllegalArgumentException.
     * @param user The user to be updated in the database.
     * @throws IllegalArgumentException If the user ID is null.
     * @throws DAOException If something fails at database level.
     */
    void update(User user) throws IllegalArgumentException, DAOException;

    /**
     * Delete the given user from the database. After deleting, the DAO will set the ID of the given
     * user to null.
     * @param user The user to be deleted from the database.
     * @throws IllegalArgumentException If the user ID is null.
     * @throws DAOException If something fails at database level.
     */
    void delete(User user) throws IllegalArgumentException, DAOException;

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
     * @param user The user to change the password for.
     * @throws IllegalArgumentException If the user ID is null.
     * @throws DAOException If something fails at database level.
     */
    void changePassword(User user) throws IllegalArgumentException, DAOException;


}
