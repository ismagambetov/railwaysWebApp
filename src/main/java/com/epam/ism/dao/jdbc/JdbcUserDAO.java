package com.epam.ism.dao.jdbc;

import com.epam.ism.FactoryMethod;
import com.epam.ism.dao.UserDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * This class represents a concrete JDBC implementation of the {@link UserDao} interface
 * and is extended by {@link AbstractJdbcDao} abstract class.
 *
 * @author IDS.
 */
public class JdbcUserDao extends AbstractJdbcDao<User> implements UserDao {
    public static List<User> list;

    static {
        list = FactoryMethod.getUserList();
    }

    public JdbcUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public Object[] generateValuesForCreate(User entity) {
        return new Object[0];
    }

    @Override
    public User map(ResultSet resultSet) throws SQLException {
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
    public User find(String email, String password) throws DaoException {
        return null;
    }

    @Override
    public boolean existEmail(String email) throws DaoException {
        return false;
    }

    @Override
    public void changePassword(User user) throws IllegalArgumentException, DaoException {

    }

    @Override
    public User find(String personalCode) throws DaoException {

       return null;
    }


}
