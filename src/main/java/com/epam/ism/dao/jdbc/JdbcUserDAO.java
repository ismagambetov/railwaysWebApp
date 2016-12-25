package com.epam.ism.dao.jdbc;

import com.epam.ism.FactoryMethod;
import com.epam.ism.dao.UserDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.Order;
import com.epam.ism.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * This class represents a concrete JDBC implementation of the {@link UserDAO} interface
 * and is extended by {@link AbstractJdbcDAO} abstract class.
 *
 * @author IDS.
 */
public class JdbcUserDAO extends AbstractJdbcDAO<User> implements UserDAO {
    public static List<User> list;

    static {
        list = FactoryMethod.getUserList();
    }

    public JdbcUserDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Object[] generateValuesForCreate(User entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(User entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(User entity) {
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
    public User find(String email, String password) throws DAOException {
        return null;
    }

    @Override
    public boolean existEmail(String email) throws DAOException {
        return false;
    }

    @Override
    public void changePassword(User user) throws IllegalArgumentException, DAOException {

    }

    @Override
    public User find(String personalCode) throws DAOException {

       return null;
    }


}
