package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.TrainDAO;
import com.epam.ism.dao.exception.DAOException;
import static com.epam.ism.dao.jdbc.JdbcDAOUtil.*;
import com.epam.ism.entity.Train;
import com.epam.ism.entity.Trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * This class represents a concrete JDBC implementation of the {@link TrainDAO} interface.
 *
 * @author IDS.
 */
public class JdbcTrainDAO extends AbstractJdbcDAO<Train> implements TrainDAO {

    //There are methods beneath to be used just for testing.
    private static Long i = 0L;
    private List<Train> trains = new ArrayList<>();

    @Override
    public Train createAndGet(String trainName) {
        Train train = new Train();
        train.setId(++i);
        train.setName(trainName);

        return train;
    }

    @Override
    public void add(Train train) {
        trains.add(train);
    }

    @Override
    public List<Train> getList() {
        return trains;
    }

    @Override
    public Train getByName(String name) {
        for (Train train : trains) {
            if (train.getName().equals(name)) {
                return train;
            }
        }
        return null;
    }

    //There is code beneath, which will be used at database level.
    //Constants
    private static final String SQL_FIND_BY_ID = "SELECT * FROM Train WHERE id = ?";
    private static final String SQL_FIND_BY_NAME = "SELECT * FROM Train WHERE name = ?";
    private static final String SQL_LIST_ORDERED_BY_ID = "SELECT * FROM Train ORDER BY id";
    private static final String SQL_INSERT = "INSERT INTO Train (name) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE Train SET name = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM Train WHERE id = ?";

    private JdbcDAOFactory daoFactory;

    /**
     * Constructs a Train DAO for the given DAOFactory
     * @param daoFactory The DAOFactory to construct this Train DAO for.
     */
    public JdbcTrainDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
    }

    //Actions
    @Override
    public Train find(Long id) throws DAOException {
        return find(SQL_FIND_BY_ID, id);
    }

    @Override
    public Object[] generateValuesForCreate(Train entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(Train entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(Train entity) {
        return new Object[0];
    }

    @Override
    public void create(Train train) throws IllegalArgumentException, DAOException {
        if (train.getId() != null) {
            throw new IllegalArgumentException("Train is already created, the train ID is not null.");
        }

        Object[] values = {
            train.getName()
        };

        try(
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection,SQL_INSERT,true,values);
         ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Creating train failed, no rows affected.");
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                train.setId(generatedKeys.getLong(1));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Train train) throws IllegalArgumentException, DAOException {
        if (train.getId() == null) {
            throw new IllegalArgumentException("Train is not created yet, the train ID is null.");
        }

        Object[] values = {
                train.getName()
        };

        try(
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection,SQL_UPDATE,false,values);
         ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating train failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public void delete(Train train) throws IllegalArgumentException, DAOException {
        if (train.getId() == null) {
            throw new IllegalArgumentException("Train is not created yet, the train ID is null.");
        }

        Object[] values = {
                train.getId()
        };

        try(
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection,SQL_DELETE,false,values);

         ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting train failed, now rows affected.");
            } else {
                train.setId(null);
            }
        }catch (SQLException e) {
            throw new DAOException(e);
        }

    }

    @Override
    public List<Train> list() throws DAOException {
        List<Train> trains = new ArrayList<>();
        try(
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDERED_BY_ID);
                ResultSet resultSet = statement.executeQuery();
        ) {
                while (resultSet.next()) {
                    trains.add(map(resultSet));
                }

        }catch (SQLException e) {
            throw new DAOException(e);
        }

        return trains;
    }

    /**
     * Returns the train from the database matching the given SQL query with the given values.
     * @param sql The SQL query to be executed in the database.
     * @param values The PreparedStatement values to be set.
     * @return The train from the database.
     * @throws DAOException If something fails at database level.
     */
    private Train find(String sql, Object...values) throws DAOException {
        Train train = null;
        try(
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection,sql,false,values);
                ResultSet resultSet = statement.executeQuery();
        ) {
                if (resultSet.next()) {
                    train = map(resultSet);
                }

        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return train;
    }

    /**
     * Map current row of the given ResultSet to an Train.     *
     * @param resultSet The ResultSet of which the current row is to be mapped to an Train.
     * @return The mapped Train from the current row of the given ResultSet.
     * @throws SQLException If something fails at database level.
     */
    public Train map(ResultSet resultSet) throws SQLException {
//        Train train = new Train();
//        train.setId(resultSet.getLong(1));
//        train.setName(resultSet.getString(2));
//
        return null;//train;
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

}
