package com.epam.ism.dao.oracle;

import com.epam.ism.dao.MainRouteDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.MainRoute;

import java.util.List;

public class OracleMainRouteDAO extends AbstractOracleDAO<MainRoute> implements MainRouteDAO {

    @Override
    public void create(MainRoute entity) throws DAOException {

    }

    @Override
    public void update(MainRoute entity) throws DAOException {

    }

    @Override
    public void delete(MainRoute entity) throws DAOException {

    }

    @Override
    public List<MainRoute> list() throws DAOException {
        return null;
    }

    @Override
    public MainRoute find(Long id) throws DAOException {
        return null;
    }

    @Override
    public MainRoute find(String name) throws DAOException {
        return null;
    }
}
