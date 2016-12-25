package com.epam.ism.dao.jdbc;

import com.epam.ism.FactoryMethod;
import com.epam.ism.dao.MainRouteDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcMainRouteDAO extends AbstractJdbcDAO<MainRoute> implements MainRouteDAO {
    public static List<MainRoute> list;

    static {
        list = FactoryMethod.getMainRouteList();
    }

    public JdbcMainRouteDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Object[] generateValuesForCreate(MainRoute entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(MainRoute entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(MainRoute entity) {
        return new Object[0];
    }

    @Override
    public MainRoute map(ResultSet resultSet) throws SQLException {
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
    public MainRoute find(String name) throws DAOException {
        return null;
    }
}