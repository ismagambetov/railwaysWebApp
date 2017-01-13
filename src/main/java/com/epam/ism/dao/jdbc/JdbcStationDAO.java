package com.epam.ism.dao.jdbc;

import com.epam.ism.FactoryMethod;
import com.epam.ism.dao.StationDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.Station;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcStationDao extends AbstractJdbcDao<Station> implements StationDao {
//    public static List<Station> list;
//
//    static {
//        list = FactoryMethod.getStationList();
//    }


    public JdbcStationDao(Connection connection) {
        super(connection);
    }


    @Override
    public Object[] generateValuesForCreate(Station entity) {
        return new Object[0];
    }


    @Override
    public Station map(ResultSet resultSet) throws SQLException {
        Station station = new Station();
        station.setId(resultSet.getLong(1));
        station.setName(resultSet.getString(2));

        return station;
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
