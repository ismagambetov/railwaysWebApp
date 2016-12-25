package com.epam.ism.dao.jdbc;

import com.epam.ism.connection.ConnectionPoolException;
import com.epam.ism.connection.JdbcConnectionPool;
import com.epam.ism.dao.MainRouteDAO;
import com.epam.ism.dao.OrderDAO;
import com.epam.ism.dao.StationDAO;
import com.epam.ism.dao.TrainDAO;
import com.epam.ism.dao.exception.DAOConfigurationException;
import java.sql.Connection;

/**
 * This class represents a DAO factory for a SQL database. You can use {@link #get(String)}
 * to obtain a new instance for the given database name. The specific instance returned depends on
 * the properties file configuration. You can obtain DAO's for the DAO factory instance using the
 * DAO getters.
 * The class has two abstract getConnection() - is used to take a connection from ConnectionPool, and
 * returnConnection() - to return unused connection to the pool.
 *
 * @author IDS.
 */
public abstract class JdbcDAOFactory {

    public static JdbcDAOFactory get(String name) throws ConnectionPoolException {
        if (name == null) {
            throw new DAOConfigurationException("Given database name is null.");
        }

        return JdbcConnectionPool.getInstance(name);
    }

    /**
     * Method is used to retrieve a connection from the ConnectionPool.
     * @return The connection.
     */
    public abstract Connection getConnection();

    /**
     * Method is used to return unused connection to the ConnectionPool.
     * @param connection unused connection.
     */
    public abstract void returnConnection(Connection connection);

    public StationDAO getStationDAO() {
        return new JdbcStationDAO(this);
    }

    public TrainDAO getTrainDAO() {
        return new JdbcTrainDAO(this);
    }

    public MainRouteDAO getMainRouteDAO() {
        return new JdbcMainRouteDAO(this);
    }

    public OrderDAO getOrderDAO() {
        return new JdbcOrderDAO(this);
    }
}
