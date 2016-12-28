package com.epam.ism.dao.oracle;

import com.epam.ism.dao.MainRouteDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.MainRoute;

import java.util.List;

public class OracleMainRouteDao extends AbstractOracleDao<MainRoute> implements MainRouteDao {

    @Override
    public void create(MainRoute entity) throws DaoException {

    }

    @Override
    public void update(MainRoute entity) throws DaoException {

    }

    @Override
    public void delete(MainRoute entity) throws DaoException {

    }

    @Override
    public List<MainRoute> list() throws DaoException {
        return null;
    }

    @Override
    public MainRoute find(Long id) throws DaoException {
        return null;
    }

    @Override
    public MainRoute find(String name) throws DaoException {
        return null;
    }
}
