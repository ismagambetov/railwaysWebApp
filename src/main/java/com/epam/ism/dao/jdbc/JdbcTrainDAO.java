package com.epam.ism.dao.jdbc;

import com.epam.ism.FactoryMethod;
import com.epam.ism.dao.TrainDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.Train;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * This class represents a concrete JDBC implementation of the {@link TrainDao} interface.
 *
 * @author IDS.
 */
public class JdbcTrainDao extends AbstractJdbcDao<Train> implements TrainDao {

    public static List<Train> list;

    static {
        list = FactoryMethod.getTrainList();
    }

    @Override
    public List<Train> list() throws DaoException {
        return list;
    }

    @Override
    public Object[] generateValuesForCreate(Train entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(Train entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(Train entity) {
        return new Object[0];
    }

    @Override
    public Train map(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public String insertQuery() {
        return null;
    }

    @Override
    public String updateQuery() {
        return null;
    }

    @Override
    public String findQuery() {
        return null;
    }

    @Override
    public String deleteQuery() {
        return null;
    }

    @Override
    public String listQuery() {
        return null;
    }

    @Override
    public Train find(String name) throws DaoException {
        for (Train train : list) {
            if (train.getName().equals(name)) return train;
        }
        return null;
    }
}
