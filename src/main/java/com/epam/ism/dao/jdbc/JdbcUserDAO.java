package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.UserDAO;
import com.epam.ism.dao.exception.DAOException;
import com.epam.ism.entity.Role;
import com.epam.ism.entity.User;
import com.epam.ism.utils.Password;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.ism.dao.jdbc.JdbcDAOUtil.*;

/**
 * This class represents a concrete JDBC implementation of the {@link UserDAO} interface
 * and is extended by {@link AbstractJdbcDAO} abstract class.
 *
 * @author IDS.
 */
public class JdbcUserDAO extends AbstractJdbcDAO<User> implements UserDAO {
    //There are methods beneath to be used just for testing.
    private List<User> users = new ArrayList<>();
    private static Long i = 0L;

    @Override
    public User createAndGet(String firstName, String lastName, String personalCode, Date birthday,
                             String password, String email, Role role) {
        User user = new User();
        user.setId(++i);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPersonalCode(personalCode);
        user.setBirthday(birthday);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);

        return user;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public User getByCode(String personalCode) {
        for (User user : users) {
            if (user.getPersonalCode().equals(personalCode)) {
                return user;
            }
        }
        return null;
    }

    //There is a code beneath, which will be used at database level.
    private static final String SQL_FIND_BY_ID =
            "SELECT id,firstname,lastname,email,personalcode,birthday FROM User WHERE id = ?";
    private static final String SQL_FIND_BY_EMAIL_AND_PASSWORD =
            "SELECT id,firstname,lastname,email,personalcode,birthday FROM User WHERE email = ? and password = ?";
    private static final String SQL_FIND_BY_PERSONAL_CODE =
            "SELECT id,firstname,lastname,email,personalcode,birthday FROM User WHERE personalcode = ?";
    private static final String SQL_LIST_ORDERED_BY_ID =
            "SELECT id,firstname,lastname,email,personalcode,birthday FROM User ORDER BY id";
    private static final String SQL_INSERT =
            "INSERT INTO User (firstname,lastname,password,email,personalcode,birthday,role) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE =
            "UPDATE User SET firstname=?,lastname=?,email=?,personalcode=?,birthday=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM User WHERE id = ?";
    private static final String SQL_EXIST_EMAIL = "SELECT id FROM user WHERE email = ?";
    private static final String SQL_CHANGE_PASSWORD = "UPDATE User SET password = ? WHERE id = ?";

    private JdbcDAOFactory daoFactory;

    public JdbcUserDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
        this.daoFactory = daoFactory;
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
    public User find(String personalCode) throws DAOException {
        User user = null;
        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection,SQL_FIND_BY_PERSONAL_CODE,false,personalCode);
                ResultSet resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean existEmail(String email) throws DAOException {
        boolean exist;
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

    @Override
    public String insertQuery() {return SQL_INSERT;}

    @Override
    public String updateQuery() {return SQL_UPDATE;}

    @Override
    public String findQuery() {return SQL_FIND_BY_ID;}

    @Override
    public String deleteQuery() {return SQL_DELETE;}

    @Override
    public String listQuery() {return SQL_LIST_ORDERED_BY_ID;}

    @Override
    public Object[] generateValuesForCreate(User user) {
        return concatArrays(generateValues(user), new Object[]{user.getPassword()});
    }

    @Override
    public Object[] generateValuesForUpdate(User user) {
        return concatArrays(generateValues(user),new Object[]{user.getId()});
    }

    @Override
    public Object[] generateValuesForDelete(User user) {
        return new Object[]{user.getId()};
    }

    private Object[] generateValues(User user) {
        return new Object[] {
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPersonalCode(),
            user.getRole()
        };
    }

    @Override
    public User map(ResultSet resultSet) throws SQLException {
//        User user = new User();
//        user.setId(resultSet.getLong("id"));
//        user.setFirstName(resultSet.getString("firstname"));
//        user.setLastName(resultSet.getString("lastname"));
//        user.setEmail(resultSet.getString("email"));
//        user.setPersonalCode(resultSet.getString("personalcode"));
//        user.setBirthday(resultSet.getDate("birthday"));
//
//        int role_ = resultSet.getInt("role");
//        Role role = null;
//        switch (role_) {
//            case 1: role = Role.ADMINISTRATOR;
//            case 2: role = Role.CASHIER;
//            case 3: role = Role.PASSENGER;
//                default:role = Role.PASSENGER;
//        }
//        user.setRole(role);
        return null;
    }

}
