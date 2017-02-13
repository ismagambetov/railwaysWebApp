package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.IdEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  Utility class for DAO's. This class contains commonly used DAO logic which is been refactored in
 *  single static methods.
 *
 *  @author IDS;
 */
public final class JdbcDaoUtil {
    final static Logger logger = LoggerFactory.getLogger(JdbcDaoUtil.class);
    //Constructor
    /*
     * Utility class, hide constructor.
     */
    private JdbcDaoUtil() {
    }

    /**
     * Returns a PreparedStatement of the given connection, set with the given SQL query and the
     * given  parameter values.
     * @param connection The Connection to create the PreparedStatement from.
     * @param sql The SQL query to construct the PreparedStatement with.
     * @param returnGeneratedKeys Set whether to return generated keys or not.
     * @param values The parameter values to be set in the created PreparedStatement.
     * @return The PreparedStatement.
     * @throws SQLException If something fails during creating the PreparedStatement.
     */
    public static PreparedStatement prepareStatement(Connection connection,
                  String sql, boolean returnGeneratedKeys, Object...values) throws SQLException {

        PreparedStatement statement = connection.prepareStatement(sql,
                returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        setValues(statement, values);

        return statement;
    }

    /**
     * Set the given parameter values in the given PreparedStatement.
     * @param statement The PreparedStatement to set the given parameter values in.
     * @param values The parameter values to be set in the created PreparedStatement.
     * @throws SQLException if something fails during setting the PreparedStatement values.
     */
    public static void setValues(PreparedStatement statement, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i+1,values[i]);
        }
    }

    public static void executeUpdate(PreparedStatement statement, String className, String action) throws SQLException, DaoException {
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new DaoException(action + " action failed, now rows affected. Class: " + className);
        }
    }



}
