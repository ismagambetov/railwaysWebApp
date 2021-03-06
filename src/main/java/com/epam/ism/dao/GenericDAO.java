package com.epam.ism.dao;

import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.utils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * This interface represents base methods which to be used by DAO models.
 * @param <T> Generic type of entities.
 *
 * @author IDS.
 */
public interface GenericDao<T> {

    /**
     * Create the given T type entity in the database. The entity ID must be null, otherwise it
     * will be throw IllegalArgumentException.
     * @param entity The T type entity to be created in the database.
     * @throws IllegalArgumentException If the entity ID is not null.
     * @throws DaoException If something fails at database level.
     */
    void create(T entity) throws DaoException;

    /**
     * Update the given T type entity in the database. The entity ID must not be null, otherwise it
     * will be throw IllegalArgumentException.
     * @param entity The T type entity to be updated in the database.
     * @throws IllegalArgumentException If the entity ID is null.
     * @throws DaoException If something fails at database level.
     */
    void update(T entity) throws DaoException;

    /**
     * Delete the given entity from the database. After deleting, the DAO will set the ID of the given
     * entity to null.
     * @param entity The T type entity to be deleted from the database.
     * @throws IllegalArgumentException If the entity ID is null.
     * @throws DaoException If something fails at database level.
     */
    void delete(T entity) throws DaoException;

    /**
     * Returns a list of all T type entities from the database ordered by entity ID. The list is never null and
     * is empty when database does not contain any object.
     * @return A list of all objects from the database.
     * @throws DaoException If something fails at database level.
     */
    List<T> list() throws DaoException;

    List<T> list(String query, Object...params) throws DaoException;

    /**
     * Returns the T type entity from the database matching the given ID, otherwise null.
     * @param id The ID of the entity to be returned.
     * @return The entity from the database, otherwise null.
     * @throws DaoException If something fails at database level.
     */
    T findById(int id) throws DaoException;

    /**
     * Returns the T type entity from the database matching the given Name, otherwise null.
     * @param name The Name of the entity to be returned.
     * @return The entity from the database, otherwise null.
     * @throws DaoException If something fails at database level.
     */
    T findByName(String name) throws DaoException;

    void map(RowMapper rowMapper) throws SQLException;

    T mapRow(ResultSet rs) throws SQLException;
}
