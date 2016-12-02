package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.UserDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.User;
import com.epam.ism.utils.Password;

import static com.epam.ism.dao.jdbc.JdbcDAOUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * This class represents a concrete JDBC implementation of the {@link UserDAO} interface.
 *
 * @author IDS.
 */
public class JdbcUserDAO implements UserDAO {

    //Constants
    private static final String SQL_FIND_BY_ID =
            "SELECT id,firstname,lastname,email FROM User WHERE id = ?";
    private static final String SQL_FIND_BY_EMAIL_AND_PASSWORD =
            "SELECT id,firstname,lastname,password,email FROM User WHERE email = ?";
    private static final String SQL_LIST_ORDERED_BY_ID =
            "SELECT id,firstname,lastname,email FROM User ORDER BY id";
    private static final String SQL_INSERT =
            "INSERT INTO User (firstname,lastname,password, email) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE =
            "UPDATE User SET firstname = ?, lastname = ?, email = ? WHERE id = ?";
    private static final String SQL_DELETE =
            "DELETE FROM User WHERE id = ?";
    private static final String SQL_EXIST_EMAIL =
            "SELECT id FROM user WHERE email = ?";
    private static final String SQL_CHANGE_PASSWORD =
            "UPDATE User SET password = ? WHERE id = ?";

    private JdbcDAOFactory daoFactory;

    /**
     * Construct an User DAO for the given DAOFactory.
     * @param daoFactory The DAOFactory to construct this User DAO for.
     */
    public JdbcUserDAO(JdbcDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    //Actions
    @Override
    public User find(Long id) throws DAOException {
        User user = null;

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_FIND_BY_ID, false, id);
                ResultSet resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public User find(String email, String password) throws DAOException {
        User user = null;
        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_EMAIL_AND_PASSWORD);
             ) {
                statement.setString(1,email);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String passwordDB = resultSet.getString(4);
                    boolean exist = Password.check(password, passwordDB);
                    if (exist) user = map(resultSet);
                }
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return user;
    }

    @Override
    public List<User> list() throws DAOException {
        List<User> users = new ArrayList<>();
        try(
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDERED_BY_ID);
                ResultSet resultSet = statement.executeQuery();
            ) {
                while (resultSet.next()) {
                    users.add(map(resultSet));
                }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public void create(User user) throws IllegalArgumentException, DAOException {

        if (user.getId() != null) {
            throw new IllegalArgumentException("User is already created, the user ID is not null.");
        }

        Object[] values = {
            user.getFirstName(),
            user.getLastName(),
            user.getPassword(),
            user.getEmail()
        };

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_INSERT,true,values);
            ){
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new DAOException("Creating user failed, now rows affected.");
                }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e){
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User user) throws IllegalArgumentException, DAOException {
        if (user.getId() == null) {
            throw new DAOException("User is not created yet, the user ID is null.");
        }

        Object[] values = {
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getId()
        };

        try(
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_UPDATE, false, values);
            ){

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating user failed, now rows affected.");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public void delete(User user) throws DAOException {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User is not created yet, the user ID is null.");
        }

        Object[] values = {
            user.getId()
        };

        try (
            Connection connection = daoFactory.getConnection();
            PreparedStatement statement = prepareStatement(connection, SQL_DELETE, false, values);
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting user failed, now rows affected.");
            } else {
                user.setId(null);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean existEmail(String email) throws DAOException {
        boolean exist = false;
        try(
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_EXIST_EMAIL, false, email);
                ResultSet resultSet = statement.executeQuery();
        ) {
            exist = resultSet.next();
        }catch (SQLException e) {
            throw new DAOException(e);
        }

        return exist;
    }

    @Override
    public void changePassword(User user) throws IllegalArgumentException, DAOException {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User is not created yet, the user ID is null.");
        }
        Object[] values = {
                user.getPassword(),
                user.getId()
        };

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_CHANGE_PASSWORD, false, values);
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Changing password failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Map the current row of the given ResultSet to an User.
     * @param resultSet The ResultSet of which the current row is to be mapped to an User.
     * @return The mapped User from the current row of the given ResultSet.
     * @throws SQLException If something fails at database level.
     */
    private static User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setLastName(resultSet.getString("lastname"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }



}
