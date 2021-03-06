package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.RouteDao;
import com.epam.ism.entity.Course;
import com.epam.ism.entity.Route;
import com.epam.ism.entity.Station;
import com.epam.ism.entity.Train;
import com.epam.ism.utils.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class JdbcRouteDao extends AbstractJdbcDao<Route> implements RouteDao {

    public JdbcRouteDao(Connection connection) {
        super(connection);
    }

    @Override
    public Object[] generateValuesForCreate(Route entity) {
        return new Object[0];
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
    public String findByIdQuery() {
        return null;
    }

    @Override
    public String findByNameQuery() {
        return null;
    }

    @Override
    public String deleteQuery() {
        return "DELETE FROM routes WHERE id = ?";
    }

    @Override
    public String listQuery() {
        return "SELECT * FROM routes";
    }

}
