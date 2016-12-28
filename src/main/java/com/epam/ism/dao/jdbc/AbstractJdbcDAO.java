package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.DaoFactory;
import com.epam.ism.dao.GenericDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.IdEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.ism.dao.jdbc.JdbcDaoUtil.*;

/**
 * This abstract class represents implementations of the common methods
 * of the all DAO models.
 * @param <T> Generic type of models.
 *
 * @author IDS.
 */
public abstract class AbstractJdbcDao<T extends IdEntity> implements GenericDao<T> {

//    public AbstractJdbcDAO() {
//        Class entityClass = ((Class) ((ParameterizedType) getClass()
//                .getGenericSuperclass()).getActualTypeArguments()[0]);
//    }

    @Override
    public void create(T entity) throws DaoException,IllegalArgumentException {
        Connection connection = null;
        PreparedStatement statement = null;

        String className = entity.getClass().getSimpleName();

        if (entity.getId() != null) {
            throw new IllegalArgumentException(className + " is already created, the user ID is not null.");
        }

        Object[] values = generateValuesForCreate(entity);

        try {
            connection = DaoFactory.getConnection();
            connection.setAutoCommit(false);
            statement = prepareStatement(connection, insertQuery(),true,values);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Creating " + className + " failed, now rows affected.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e){
            try {
                if (connection != null) {
                    connection.rollback();
                    logger.info("Undoes all changes made in the Insert transaction");
                }
            } catch (SQLException e1) {
                throw new DaoException("Cancellation all changes, during Insert statement, failed.", e);
            }
            throw new DaoException("Insert data process is failed. " +
                    "Problem has occurred out of the connection or given parameter.", e);
        } finally {
            if (connection != null) DaoFactory.returnConnection(connection);
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // TODO: 28.12.2016 How to catch exception in the right way
                    throw new UnsupportedOperationException();
                }
            }
        }
    }

    @Override
    public void update(T entity) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        String className = entity.getClass().getSimpleName();
        if (entity.getId() == null) {
            throw new DaoException(className + " is not created yet, the user ID is null.");
        }

        Object[] values = generateValuesForUpdate(entity);

        try {
            connection = DaoFactory.getConnection();
            connection.setAutoCommit(false);
            statement = prepareStatement(connection, updateQuery(), false, values);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Updating " + className + " failed, now rows affected.");
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                    logger.info("Undoes all changes made in the Update transaction");
                }
            } catch (SQLException e1) {
                throw new DaoException("Cancellation all changes, during Update statement, failed.", e);
            }
            throw new DaoException("Update data process is failed. " +
                    "Problem has occurred out of the connection or given parameter.", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                // TODO: 28.12.2016 How to catch exception in the right way
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public void delete(T entity) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        String className = entity.getClass().getSimpleName();

        if (entity.getId() == null) {
            throw new IllegalArgumentException(className + " is not created yet, the user ID is null.");
        }

        Object[] values = generateValuesForDelete(entity);

        try {
            connection = DaoFactory.getConnection();
            connection.setAutoCommit(false);

            statement = prepareStatement(connection, deleteQuery(), false, values);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Deleting " + className + " failed, now rows affected.");
            } else {
                entity.setId(null);
            }
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                    logger.info("Undoes all changes made in the Delete transaction");
                }
            } catch (SQLException e1) {
                throw new DaoException("Cancellation all changes, during Delete statement, failed.", e);
            }
            throw new DaoException("Delete data process is failed. " +
                    "Problem has occurred out of the connection or given parameter.", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                // TODO: 28.12.2016 How to catch exception in the right way
                throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public List<T> list() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<T> list = new ArrayList<>();

        try {
            connection = DaoFactory.getConnection();
            statement = connection.prepareStatement(listQuery());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(map(resultSet));
            }

        } catch (SQLException e) {
             throw new DaoException("Receiving data process is failed. " +
                    "Problem has occurred out of the connection or given parameter.", e);
        } finally {
            // TODO: 28.12.2016 rid out of copy-paste
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                // TODO: 28.12.2016 How to catch exception in the right way
                throw new UnsupportedOperationException();
            }
        }

        return list;
    }


    @Override
    public T find(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        T entity = null;

        try {
            connection = DaoFactory.getConnection();
            statement = prepareStatement(connection, findQuery(), false, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                entity = map(resultSet);
            }

        } catch (SQLException e) {
            throw new DaoException("Find data process is failed. " +
                    "Problem has occurred out of the connection or given parameter.", e);
        } finally {
            // TODO: 28.12.2016 rid out of copy-paste
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();

            } catch (SQLException e) {
                // TODO: 28.12.2016 How to catch exception in the right way
                throw new UnsupportedOperationException();
            }
        }

        return entity;
    }

    public abstract Object[] generateValuesForCreate(T entity);
    public abstract Object[] generateValuesForUpdate(T entity);
    public abstract Object[] generateValuesForDelete(T entity);
    public abstract T map(ResultSet resultSet) throws SQLException;
    public abstract String insertQuery();
    public abstract String updateQuery();
    public abstract String findQuery();
    public abstract String deleteQuery();
    public abstract String listQuery();


}
