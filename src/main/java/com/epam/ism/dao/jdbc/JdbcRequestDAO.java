package com.epam.ism.dao.jdbc;

import com.epam.ism.dao.RequestDAO;
import com.epam.ism.entity.Request;
import com.epam.ism.entity.Station;
import com.epam.ism.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class JdbcRequestDAO extends AbstractJdbcDAO<Request> implements RequestDAO {
    //There are methods beneath to be used just for testing.
    private List<Request> requests = new ArrayList<>();
    private static Long i = 0L;

    @Override
    public Request createAndGet(User passenger, Date departureDate, Station from, Station to) {
        Request request =  new Request();
        request.setId(++i);
        request.setPassenger(passenger);
        request.setDepartureDate(departureDate);
        request.setFrom(from);
        request.setTo(to);

        return request;
    }

    @Override
    public void add(Request request) {
        requests.add(request);
    }

    @Override
    public List<Request> getList() {
        return requests;
    }

    //There is a code beneath, which will be used at database level.
    public JdbcRequestDAO(JdbcDAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Object[] generateValuesForCreate(Request entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForUpdate(Request entity) {
        return new Object[0];
    }

    @Override
    public Object[] generateValuesForDelete(Request entity) {
        return new Object[0];
    }

    @Override
    public Request map(ResultSet resultSet) throws SQLException {
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
