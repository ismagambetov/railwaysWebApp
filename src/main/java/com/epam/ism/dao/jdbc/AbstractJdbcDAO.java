package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.GenericDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.IdEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.epam.ism.dao.jdbc.JdbcDaoUtil.*;

/**
 * This abstract class represents implementations of the common methods
 * of the all DAO models.
 * @param <T> Generic type of models.
 *
 * @author IDS.
 */
public abstract class AbstractJdbcDao<T extends IdEntity> implements GenericDao<T> {
    private static final String CREATE_ACTION = "Create";
    private static final String UPDATE_ACTION = "Update";
    private static final String DELETE_ACTION = "Delete";

    private Connection connection;

    public AbstractJdbcDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(T entity) throws DaoException,IllegalArgumentException {
        String className = entity.getClass().getSimpleName();

        if (entity.getId() != null) {
            throw new IllegalArgumentException(className + " is already created, the user ID is not null.");
        }

        Object[] values = generateValuesForCreate(entity);

        try (
                PreparedStatement statement = prepareStatement(connection, insertQuery(),true,values);
        ) {
            executeUpdate(statement,className,CREATE_ACTION);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e){
                throw new DaoException("Insert data into DB failed: " + e.getMessage());
        }
    }

    @Override
    public void update(T entity) throws DaoException {
        execute(entity,UPDATE_ACTION);
    }

    @Override
    public void delete(T entity) throws DaoException {
        execute(entity,DELETE_ACTION);
    }

    @Override
    public List<T> list() throws DaoException {
        try(PreparedStatement statement = connection.prepareStatement(listQuery())) {
            return list(statement);
        } catch (SQLException e) {
            throw new DaoException("PrepareStatement() failed: listQuery(); " + e.getMessage());
        }
    }

    @Override
    public List<T> list(String query, Object... params) throws DaoException {
        try(PreparedStatement statement = prepareStatement(connection,query,false,params)) {
            return list(statement);
        } catch (SQLException e) {
            throw new DaoException("Process failed: listQuery(); " + e.getMessage());
        }
    }

    @Override
    public T findById(Long id) throws DaoException {
        return find(id);
    }

    @Override
    public T findByName(String name) throws DaoException {
        return find(name);
    }

    private T find(Object object) {
        Long id = null;
        String name = null;
        String query = null;

        if (object instanceof String) {
            name = (String) object;
            query = findByNameQuery();
        } else if (object instanceof Long) {
            id = (Long) object;
            query = findByIdQuery();
        }
        T entity = null;

        try (
                PreparedStatement statement = prepareStatement(connection, query, false,
                                                                        name == null ? id : name);
                ResultSet resultSet = statement.executeQuery();
         ) {
            if (resultSet.next()) entity = map(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Find data process failed: " + e.getMessage());
        }

        return entity;
    }

    private List<T> list(PreparedStatement statement) throws SQLException {
        List<T> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            list.add(map(resultSet));
        }
        return list;
    }

    private void execute(T entity,String action) {
        String className = entity.getClass().getSimpleName();

        if (entity.getId() == null) {
            throw new IllegalArgumentException(className + " is not created yet, the user ID is null.");
        }

        long id = entity.getId();

        String query = null;
        if (action.equals(DELETE_ACTION)) {
            query = deleteQuery();
        } else if (action.equals(UPDATE_ACTION)) {
            query = updateQuery();
        }

        try (
            PreparedStatement statement = prepareStatement(connection, query, false, id);
         ) {
            executeUpdate(statement,className,action);
            if (action.equals(DELETE_ACTION)) entity.setId(null);
        } catch (SQLException e) {
            throw new DaoException(action + " action failed: " + e.getMessage());
        }
    }

    public abstract Object[] generateValuesForCreate(T entity);
    public abstract T map(ResultSet resultSet) throws SQLException;
    public abstract String insertQuery();
    public abstract String updateQuery();
    public abstract String findByIdQuery();
    public abstract String findByNameQuery();
    public abstract String deleteQuery();
    public abstract String listQuery();


}
