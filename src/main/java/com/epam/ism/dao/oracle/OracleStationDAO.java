package com.epam.ism.dao.oracle;

import com.epam.ism.dao.StationDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.Station;

import java.util.List;

public class OracleStationDao extends AbstractOracleDao<Station> implements StationDao {
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
        return null;
    }
}
