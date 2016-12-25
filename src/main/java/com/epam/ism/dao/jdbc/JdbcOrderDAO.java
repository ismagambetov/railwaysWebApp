package com.epam.ism.dao.jdbc;

import com.epam.ism.FactoryMethod;
import com.epam.ism.dao.OrderDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.MainRoute;
import com.epam.ism.entity.Order;
import com.epam.ism.entity.User;
import snaq.db.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 *
 */
public class JdbcOrderDAO extends AbstractJdbcDAO<Order> implements OrderDAO {
    public static List<User> list;

    static {
        list = FactoryMethod.getUserList();
    }


    public JdbcOrderDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Object[] generateValuesForCreate(Order entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(Order entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(Order entity) {
        return new Object[0];
    }

    @Override
    public Order map(ResultSet resultSet) throws SQLException {
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

    @Override
    public Order find(String name) throws DAOException {
        return null;
    }
}
