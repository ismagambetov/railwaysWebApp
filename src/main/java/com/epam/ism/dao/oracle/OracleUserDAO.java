package com.epam.ism.dao.oracle;

import com.epam.ism.dao.UserDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.User;

import java.util.List;

public class OracleUserDao extends AbstractOracleDao<User> implements UserDao {


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
    public void create(User entity) throws DaoException {

    }

    @Override
    public void update(User entity) throws DaoException {

    }

    @Override
    public void delete(User entity) throws DaoException {

    }

    @Override
    public List<User> list() throws DaoException {
        return null;
    }

    @Override
    public User find(Long id) throws DaoException {
        return null;
    }

    @Override
    public User find(String name) throws DaoException {
        return null;
    }
}
