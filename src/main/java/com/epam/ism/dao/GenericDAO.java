package com.epam.ism.dao;

import com.epam.ism.dao.exception.DAOException;
import java.util.List;

/**
 * This interface represents base methods which to be used by DAO models.
 * @param <T> Generic type of entities.
 *
 * @author IDS.
 */
public interface GenericDAO<T> {

    /**
     * Create the given T type entity in the database. The entity ID must be null, otherwise it
     * will be throw IllegalArgumentException.
     * @param entity The T type entity to be created in the database.
     * @throws IllegalArgumentException If the entity ID is not null.
     * @throws DAOException If something fails at database level.
     */
    void create(T entity) throws DAOException;

    /**
     * Update the given T type entity in the database. The entity ID must not be null, otherwise it
     * will be throw IllegalArgumentException.
     * @param entity The T type entity to be updated in the database.
     * @throws IllegalArgumentException If the entity ID is null.
     * @throws DAOException If something fails at database level.
     */
    void update(T entity) throws DAOException;

    /**
     * Delete the given entity from the database. After deleting, the DAO will set the ID of the given
     * entity to null.
     * @param entity The T type entity to be deleted from the database.
     * @throws IllegalArgumentException If the entity ID is null.
     * @throws DAOException If something fails at database level.
     */
    void delete(T entity) throws DAOException;

    /**
     * Returns a list of all T type entities from the database ordered by entity ID. The list is never null and
     * is empty when database does not contain any object.
     * @return A list of all objects from the database.
     * @throws DAOException If something fails at database level.
     */
    List<T> list() throws DAOException;

    /**
     * Returns the T type entity from the database matching the given ID, otherwise null.
     * @param id The ID of the entity to be returned.
     * @return The entity from the database, otherwise null.
     * @throws DAOException If something fails at database level.
     */
    T find(Long id) throws DAOException;


}
