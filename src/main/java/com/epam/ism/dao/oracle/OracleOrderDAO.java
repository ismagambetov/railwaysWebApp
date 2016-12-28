package com.epam.ism.dao.oracle;

import com.epam.ism.dao.OrderDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.Order;

import java.util.List;

public class OracleOrderDAO implements OrderDAO {

    @Override
    public void create(Order entity) throws DAOException {

    }

    @Override
    public void update(Order entity) throws DAOException {

    }

    @Override
    public void delete(Order entity) throws DAOException {

    }

    @Override
    public List<Order> list() throws DAOException {
        return null;
    }

    @Override
    public Order find(Long id) throws DAOException {
        return null;
    }

    @Override
    public Order find(String name) throws DAOException {
        return null;
    }
}
