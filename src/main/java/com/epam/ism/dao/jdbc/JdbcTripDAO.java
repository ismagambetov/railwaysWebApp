package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.TripDAO;
import com.epam.ism.entity.Trip;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class JdbcTripDAO extends AbstractJdbcDAO<Trip> implements TripDAO {
    //There are methods beneath to be used just for testing.







    //There is a code beneath, which will be used at database level.
    public JdbcTripDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Object[] generateValuesForCreate(Trip entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(Trip entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(Trip entity) {
        return new Object[0];
    }

    @Override
    public Trip map(ResultSet resultSet) throws SQLException {
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
}
