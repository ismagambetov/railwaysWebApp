package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.StationDAO;
import com.epam.ism.entity.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JdbcStationDAO extends AbstractJdbcDAO<Station> implements StationDAO {

    //There are methods beneath to be used just for testing.

    private List<Station> stations = new ArrayList<>();
    private static Long i = 0L;

    @Override
    public Station createAndGet(String stationName) {
        Station station = new Station();
        station.setId(++i);
        station.setName(stationName);

        return station;
    }

    @Override
    public void add(Station station) {
        stations.add(station);
    }

    @Override
    public List<Station> getList() {
        return stations;
    }

    @Override
    public Station getByName(String name) {
        for (Station station : stations) {
            if (station.getName().equals(name)) {
                return station;
            }
        }
        return null;
    }

    //There is code beneath, which will be used at database level.
    public JdbcStationDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
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
