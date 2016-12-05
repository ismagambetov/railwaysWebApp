package com.epam.ism.dao.jdbc;

import java.sql.*;

/**
 *  Utility class for DAO's. This class contains commonly used DAO logic which is been refactored in
 *  single static methods.
 *
 *  @author __;
 */
public final class JdbcDAOUtil {
    //Constructor
    /*
     * Utility class, hide constructor.
     */
    private JdbcDAOUtil() {
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

    /**
     * Converts the given java.util.Date to java.sql.Date.
     * @param date The java.util.Date to be converted to java.sql.Date.
     * @return The converted java.sql.Date.
     */
    public static Date toSqlDate(java.util.Date date) {
        return (date != null) ? new Date(date.getTime()) : null;
    }

    /**
     * Joins two arrays.
     * @param arr1 The Object[] first array to be joined to the common array.
     * @param arr2 The Object[] second array to be joined to the common array.
     * @return The joined array.
     */
    public static Object[] concatArrays(Object[] arr1, Object[] arr2) {
        int length1 = arr1.length;
        int length2 = arr2.length;
        Object[] values = new Object[length1+length2];

        System.arraycopy(arr1,0,values,0,length1);
        System.arraycopy(arr2,0,values,length1,length2);


        return values;
    }

}
