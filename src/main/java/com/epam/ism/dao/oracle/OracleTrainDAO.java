package com.epam.ism.dao.oracle;

import com.epam.ism.dao.TrainDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.Train;

import java.util.List;

public class OracleTrainDAO extends AbstractOracleDAO<Train> implements TrainDAO {
    @Override
    public void create(Train entity) throws DAOException {

    }

    @Override
    public void update(Train entity) throws DAOException {

    }

    @Override
    public void delete(Train entity) throws DAOException {

    }

    @Override
    public List<Train> list() throws DAOException {
        return null;
    }

    @Override
    public Train find(Long id) throws DAOException {
        return null;
    }

    @Override
    public Train find(String name) throws DAOException {
        return null;
    }
}
