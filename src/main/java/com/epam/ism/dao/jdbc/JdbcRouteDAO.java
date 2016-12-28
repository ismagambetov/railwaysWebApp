package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.RouteDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.Route;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class JdbcRouteDao extends AbstractJdbcDao<Route> implements RouteDao {

    @Override
    public Object[] generateValuesForCreate(Route entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(Route entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(Route entity) {
        return new Object[0];
    }

    @Override
    public Route map(ResultSet resultSet) throws SQLException {
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
    public Route find(String name) throws DaoException {
        return null;
    }
}
