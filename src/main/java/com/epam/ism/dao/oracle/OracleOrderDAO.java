package com.epam.ism.dao.oracle;

import com.epam.ism.dao.OrderDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.Order;

import java.util.List;

public class OracleOrderDao implements OrderDao {

    @Override
    public void create(Order entity) throws DaoException {

    }

    @Override
    public void update(Order entity) throws DaoException {

    }

    @Override
    public void delete(Order entity) throws DaoException {

    }

    @Override
    public List<Order> list() throws DaoException {
        return null;
    }

    @Override
    public Order find(Long id) throws DaoException {
        return null;
    }

    @Override
    public Order find(String name) throws DaoException {
        return null;
    }
}
