package com.epam.ism.dao.oracle;

import com.epam.ism.dao.UserDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.User;

import java.util.List;

public class OracleUserDAO extends AbstractOracleDAO<User> implements UserDAO {


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
    public void create(User entity) throws DAOException {

    }

    @Override
    public void update(User entity) throws DAOException {

    }

    @Override
    public void delete(User entity) throws DAOException {

    }

    @Override
    public List<User> list() throws DAOException {
        return null;
    }

    @Override
    public User find(Long id) throws DAOException {
        return null;
    }

    @Override
    public User find(String name) throws DAOException {
        return null;
    }
}
