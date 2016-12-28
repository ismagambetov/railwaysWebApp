package com.epam.ism.dao.oracle;

import com.epam.ism.dao.StationDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.Station;

import java.util.List;

public class OracleStationDAO extends AbstractOracleDAO<Station> implements StationDAO {
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
        return null;
    }
}
