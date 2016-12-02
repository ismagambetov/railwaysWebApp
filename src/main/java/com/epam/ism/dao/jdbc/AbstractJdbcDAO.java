package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.GenericDAO;
import com.epam.ism.dao.exception.DAOException;

import java.util.List;

/**
 * This abstract class represents implementations of the common methods
 * of the all DAO models.
 * @param <T> Generic type of models.
 *
 * @author IDS.
 */
public abstract class AbstractJdbcDAO<T> implements GenericDAO<T> {

    @Override
    public void create(T entity) throws DAOException {

    }

    @Override
    public void update(T entity) throws DAOException {

    }

    @Override
    public void delete(T entity) throws DAOException {

    }

    @Override
    public List<T> list() throws DAOException {
        return null;
    }

    @Override
    public T find(Long id) throws DAOException {
        return null;
    }
}
