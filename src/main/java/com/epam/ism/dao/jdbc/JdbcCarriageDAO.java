package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.CarriageDAO;
import com.epam.ism.entity.Carriage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class JdbcCarriageDAO extends AbstractJdbcDAO<Carriage> implements CarriageDAO {

    //There are methods beneath to be used just for testing.
    private static Long i = 0L;
    List<Carriage> carriages = new ArrayList<>();

    @Override
    public Carriage createAndGet() {
        Carriage carriage = new Carriage();
        carriage.setId(++i);

        return carriage;
    }

    @Override
    public void add(Carriage carriage) {
        carriages.add(carriage);
    }

    @Override
    public List<Carriage> getList() {
        return carriages;
    }

    //There is a code beneath, which will be used at database level.
    public JdbcCarriageDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Object[] generateValuesForCreate(Carriage entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(Carriage entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(Carriage entity) {
        return new Object[0];
    }

    @Override
    public Carriage map(ResultSet resultSet) throws SQLException {
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
