package com.epam.ism.dao.jdbc;

import com.epam.ism.connection.ConnectionPool;
import com.epam.ism.dao.UserDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.User;
import com.epam.ism.utils.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * This class represents a concrete JDBC implementation of the {@link UserDao} interface
 * and is extended by {@link AbstractJdbcDao} abstract class.
 *
 * @author IDS.
 */
public class JdbcUserDao extends AbstractJdbcDao<User> implements UserDao {
    private RowMapper rowMapper;

    public JdbcUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public void map(RowMapper rowMapper) throws SQLException {
        this.rowMapper = rowMapper;
    }

    @Override
    public User mapRow(ResultSet rs) throws SQLException {
        return (User)rowMapper.mapRow(rs);
    }

    @Override
    public Object[] generateValuesForCreate(User entity) {
        return new Object[0];
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
        return "select username,first_name,last_name,iin,birthday,u_password as pass,r.name as role from users\n" +
                "left join user_roles as r on user_role_id = r.id    \n" +
                "where username = ?";
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
