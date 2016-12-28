package com.epam.ism.dao.oracle;

import com.epam.ism.dao.TrainDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.Train;

import java.util.List;

public class OracleTrainDao extends AbstractOracleDao<Train> implements TrainDao {
    @Override
    public void create(Train entity) throws DaoException {

    }

    @Override
    public void update(Train entity) throws DaoException {

    }

    @Override
    public void delete(Train entity) throws DaoException {

    }

    @Override
    public List<Train> list() throws DaoException {
        return null;
    }

    @Override
    public Train find(Long id) throws DaoException {
        return null;
    }

    @Override
    public Train find(String name) throws DaoException {
        return null;
    }
}
