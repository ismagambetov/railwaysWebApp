package com.epam.ism.dao;

import com.epam.ism.connection.ConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoManager {
    private ConnectionPool dataSource = null;
    private Connection connection = null;

    public DaoManager(ConnectionPool dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getTxConnection() {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoManagerException("SetAutoCommit(false) failed. A database access error occurred or" +
                    "this method is called on a closed connection",e);
        }

        return connection;

    }

    private Connection getConnection() {
        if (this.connection == null) {
            try {
                this.connection = dataSource.getConnection();
            } catch (SQLException e) {
                throw new DaoManagerException("Obtaining a connection failed.",e);
            }
        }

        return this.connection;
    }

    private void returnConnection(Connection connection) throws SQLException {
        this.connection.setAutoCommit(false);
        dataSource.returnConnection(connection);

        this.connection = null;
    }

    public Object transaction(DaoCommand command) throws SQLException {
        try{
            Object returnValue = command.execute();
            getConnection().commit();
            return returnValue;
        } catch (SQLException e){
            try {
                getConnection().rollback();
            } catch (SQLException e1) {
                throw new DaoManagerException("Rollback of a connection failed.",e1);
            }
            throw new DaoManagerException("Failed: command.execute() " +  e.getMessage());
        } finally {
            getConnection().setAutoCommit(true);
        }

    }

    public Object executeAndClose(DaoCommand command) throws SQLException {
        try{
            return command.execute();
        } finally {
           returnConnection(getConnection());
        }
    }

    public Object transactionAndReturnCon(DaoCommand command) {
        try {
            return executeAndClose(new ExecuteAndReturnConDaoCommand(command));
        } catch (SQLException e) {
            throw new DaoManagerException("Executing a command failed.", e);
        }
    }

    public class ExecuteAndReturnConDaoCommand implements DaoCommand {
        private DaoCommand command;

        public ExecuteAndReturnConDaoCommand(DaoCommand command) {
            this.command = command;
        }

        @Override
        public Object execute() throws SQLException {
            return transaction(command);
        }
    }
}
