package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.StationDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.Station;
import com.epam.ism.utils.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcStationDao extends AbstractJdbcDao<Station> implements StationDao {

    private RowMapper rowMapper;

    public JdbcStationDao(Connection connection) {
        super(connection);
    }

    @Override
    public void map(RowMapper rowMapper) throws SQLException {
        this.rowMapper = rowMapper;
    }

    @Override
    public Station mapRow(ResultSet rs) throws SQLException {
        return (Station) rowMapper.mapRow(rs);
    }

    @Override
    public Object[] generateValuesForCreate(Station entity) {
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
        return "SELECT * FROM stations WHERE ID = ?";
    }

    @Override
    public String findByNameQuery() {
        return "SELECT * FROM stations WHERE NAME = ?";
    }

    @Override
    public String deleteQuery() {
        return "DELETE FROM stations WHERE id = ?";
    }

    @Override
    public String listQuery() {
        return "SELECT * FROM stations";
    }

}
