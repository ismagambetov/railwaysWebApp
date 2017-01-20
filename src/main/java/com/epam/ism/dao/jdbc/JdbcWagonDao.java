package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.WagonDao;
import com.epam.ism.entity.Place;
import com.epam.ism.entity.Train;
import com.epam.ism.entity.Wagon;
import com.epam.ism.entity.WagonCategory;
import com.epam.ism.utils.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcWagonDao extends AbstractJdbcDao<Wagon> implements WagonDao {

    public JdbcWagonDao(Connection connection) {
        super(connection);
    }

    @Override
    public Object[] generateValuesForCreate(Wagon entity) {
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
        return null;
    }

    @Override
    public String listQuery() {
        return null;
    }

}
