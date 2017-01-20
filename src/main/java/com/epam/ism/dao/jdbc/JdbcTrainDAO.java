package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.TrainDao;
import com.epam.ism.entity.Train;
import com.epam.ism.utils.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * This class represents a concrete JDBC implementation of the {@link TrainDao} interface.
 *
 * @author IDS.
 */
public class JdbcTrainDao extends AbstractJdbcDao<Train> implements TrainDao {

    public JdbcTrainDao(Connection connection) {
        super(connection);
    }

    @Override
    public Object[] generateValuesForCreate(Train entity) {
        return new Object[] {entity.getId()};
    }

    @Override
    public String insertQuery() {
        return "INSERT INTO trains (name) VALUES (?)";
    }

    @Override
    public String updateQuery() {
        return "UPDATE trains SET name = ? WHERE id = ?";
    }

    @Override
    public String findByIdQuery() {
        return "SELECT * FROM trains WHERE id = ?";
    }

    @Override
    public String findByNameQuery() {
        return "SELECT * FROM trains WHERE name = ?";
    }

    @Override
    public String deleteQuery() {
        return "DELETE FROM trains WHERE id = ?";
    }

    @Override
    public String listQuery() {
        return "SELECT * FROM trains";
    }

}
