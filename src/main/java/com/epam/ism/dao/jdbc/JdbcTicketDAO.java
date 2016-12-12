package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.TicketDAO;
import com.epam.ism.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTicketDAO extends AbstractJdbcDAO<Ticket> implements TicketDAO {
    //There are methods beneath to be used just for testing.




    //There is a code beneath, which will be used at database level.
    public JdbcTicketDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Object[] generateValuesForCreate(Ticket entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(Ticket entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(Ticket entity) {
        return new Object[0];
    }

    @Override
    public Ticket map(ResultSet resultSet) throws SQLException {
        return null;
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
