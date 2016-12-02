package com.epam.ism.dao;

import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.Train;
import java.util.List;

/**
 * This interface represents a contract for a DAO for the {@link Train} model.
 *
 * @author IDS.
 */
public interface TrainDAO {

    /**
     * Returns the Train from the database matching the given ID, otherwise null.     *
     * @param id The ID of the train to be returned.
     * @return The train from the database, otherwise null.
     * @throws DAOException If something fails at database level.
     */
    Train find(Long id) throws DAOException;

    /**
     * Returns the train from the database matching the given name, otherwise null.
     * @param name The name of the train to be returned.
     * @return The train from the database, otherwise null.
     * @throws DAOException If something fails at database level.
     */
    Train find(String name) throws DAOException;

    /**
     * Returns a list of all trains from the database ordered by train ID. The list is never null and
     * is empty when database does not contain any train.
     * @return A list of all trains from the database.
     * @throws DAOException If something fails at database level.
     */
    List<Train> list() throws DAOException;

    /**
     * Create the given train in the database. The train ID must be null, otherwise it
     * will be throw IllegalArgumentException.
     * @param train The train to be created in the database.
     * @throws IllegalArgumentException If the train ID is not null.
     * @throws DAOException If something fails at database level.
     */
    void create(Train train) throws IllegalArgumentException, DAOException;

    /**
     * Update the given train in the database. The train ID must not be null, otherwise it
     * will be throw IllegalArgumentException.
     * @param train The train to be updated in the database.
     * @throws IllegalArgumentException If the train ID is null.
     * @throws DAOException If something fails at database level.
     */
    void update(Train train) throws IllegalArgumentException, DAOException;

    /**
     * Delete the given train from the database. After deleting, the DAO will set the ID of the given
     * train to null.
     * @param train The train to be deleted from the database.
     * @throws IllegalArgumentException If the train ID is null.
     * @throws DAOException If something fails at database level.
     */
    void delete(Train train) throws IllegalArgumentException, DAOException;
}
