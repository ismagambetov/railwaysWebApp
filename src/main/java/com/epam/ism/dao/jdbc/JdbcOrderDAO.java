package com.epam.ism.dao.jdbc;

import com.epam.ism.FactoryMethod;
import com.epam.ism.dao.OrderDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 *
 */
public class JdbcOrderDao extends AbstractJdbcDao<Order> implements OrderDao {
    public static List<Order> list;

    static {
        list = FactoryMethod.getOrderList();
    }

    public JdbcOrderDao(Connection connection) {
        super(connection);
    }


//    public JdbcOrderDAO(JdbcDAOFactory daoFactory) {
//        super(daoFactory);
//    }

    @Override
    public Object[] generateValuesForCreate(Order entity) {
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
    public String findByIdQuery() {
        return null;
    }

    @Override
    public String findByNameQuery() {
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
    public List<Order> list() throws DaoException {
        return list;
    }
}
