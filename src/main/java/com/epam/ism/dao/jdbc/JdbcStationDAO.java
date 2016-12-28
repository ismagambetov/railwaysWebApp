package com.epam.ism.dao.jdbc;

import com.epam.ism.FactoryMethod;
import com.epam.ism.dao.StationDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcStationDao extends AbstractJdbcDao<Station> implements StationDao {
    public static List<Station> list;

    static {
        list = FactoryMethod.getStationList();
    }

    @Override
    public void create(Station entity) throws DaoException {

    }

    @Override
    public void update(Station entity) throws DaoException {

    }

    @Override
    public void delete(Station entity) throws DaoException {

    }

    @Override
    public List<Station> list() throws DaoException {
        return null;
    }

    @Override
    public Station find(Long id) throws DaoException {
        return null;
    }

    @Override
    public Station find(String name) throws DaoException {
        for (Station station : list) {
            if (station.getName().equals(name)) {
                return station;
            }
        }

        return null;
    }

    @Override
    public Object[] generateValuesForCreate(Station entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(Station entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(Station entity) {
        return new Object[0];
    }

    @Override
    public Station map(ResultSet resultSet) throws SQLException {
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
