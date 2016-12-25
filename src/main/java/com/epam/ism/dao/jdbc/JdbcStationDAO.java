package com.epam.ism.dao.jdbc;

import com.epam.ism.FactoryMethod;
import com.epam.ism.dao.StationDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcStationDAO extends AbstractJdbcDAO<Station> implements StationDAO {
    public static List<Station> list;

    static {
        list = FactoryMethod.getStationList();
    }

    public JdbcStationDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public void create(Station entity) throws DAOException {

    }

    @Override
    public void update(Station entity) throws DAOException {

    }

    @Override
    public void delete(Station entity) throws DAOException {

    }

    @Override
    public List<Station> list() throws DAOException {
        return null;
    }

    @Override
    public Station find(Long id) throws DAOException {
        return null;
    }

    @Override
    public Station find(String name) throws DAOException {
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
