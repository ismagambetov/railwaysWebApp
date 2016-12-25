package com.epam.ism.dao.jdbc;

import com.epam.ism.FactoryMethod;
import com.epam.ism.dao.RouteDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.Route;
import com.epam.ism.entity.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class JdbcRouteDAO extends AbstractJdbcDAO<Route> implements RouteDAO {

    public JdbcRouteDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
    }

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
    public Route find(String name) throws DAOException {
        return null;
    }
}
