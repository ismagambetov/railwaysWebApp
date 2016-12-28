package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.DAOFactory;
import com.epam.ism.dao.GenericDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.IdEntity;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.ism.dao.jdbc.JdbcDAOUtil.*;

/**
 * This abstract class represents implementations of the common methods
 * of the all DAO models.
 * @param <T> Generic type of models.
 *
 * @author IDS.
 */
public abstract class AbstractJdbcDAO<T extends IdEntity> implements GenericDAO<T> {

//    public AbstractJdbcDAO() {
//        Class entityClass = ((Class) ((ParameterizedType) getClass()
//                .getGenericSuperclass()).getActualTypeArguments()[0]);
//    }

    @Override
    public void create(T entity) throws DAOException,IllegalArgumentException {
        String className = entity.getClass().getSimpleName();

        if (entity.getId() != null) {
            throw new IllegalArgumentException(className + " is already created, the user ID is not null.");
        }

        Object[] values = generateValuesForCreate(entity);

        try (
                Connection connection = DAOFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, insertQuery(),true,values)
        ){
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Creating " + className + " failed, now rows affected.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public void update(T entity) throws DAOException {
        String className = entity.getClass().getSimpleName();
        if (entity.getId() == null) {
            throw new DAOException(className + " is not created yet, the user ID is null.");
        }

        Object[] values = generateValuesForUpdate(entity);

        try(
                Connection connection = DAOFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, updateQuery(), false, values)
        ){

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating " + className + " failed, now rows affected.");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(T entity) throws DAOException {

        String className = entity.getClass().getSimpleName();

        if (entity.getId() == null) {
            throw new IllegalArgumentException(className + " is not created yet, the user ID is null.");
        }

        Object[] values = generateValuesForDelete(entity);

        try (
                Connection connection = DAOFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, deleteQuery(), false, values)
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting " + className + " failed, now rows affected.");
            } else {
                entity.setId(null);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<T> list() throws DAOException {
        List<T> list = new ArrayList<>();
        try(
                Connection connection = DAOFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(listQuery());
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                list.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return list;
    }


    @Override
    public T find(Long id) throws DAOException {
        T entity = null;

        try (
                Connection connection = DAOFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, findQuery(), false, id);
                ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                entity = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
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
