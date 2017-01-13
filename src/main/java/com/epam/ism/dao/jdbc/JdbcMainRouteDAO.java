//package com.epam.ism.dao.jdbc;
//
//import com.epam.ism.FactoryMethod;
//import com.epam.ism.dao.MainRouteDao;
//import com.epam.ism.dao.exception.DaoException;
//import com.epam.ism.entity.*;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//public class JdbcMainRouteDao extends AbstractJdbcDao<MainRoute> implements MainRouteDao {
//    public static List<MainRoute> list;
//
//    static {
//        list = FactoryMethod.getMainRouteList();
//    }
//
//    public JdbcMainRouteDao(Connection connection) {
//        super(connection);
//    }
//
//
//    @Override
//    public Object[] generateValuesForCreate(MainRoute entity) {
//        return new Object[0];
//    }
//
//    @Override
//    public Object[] generateValuesForUpdate(MainRoute entity) {
//        return new Object[0];
//    }
//
//    @Override
//    public Object[] generateValuesForDelete(MainRoute entity) {
//        return new Object[0];
//    }
//
//    @Override
//    public MainRoute map(ResultSet resultSet) throws SQLException {
//        return null;
//    }
//
//    @Override
//    public String insertQuery() {
//        return null;
//    }
//
//    @Override
//    public String updateQuery() {
//        return null;
//    }
//
//    @Override
//    public String findQuery() {
//        return null;
//    }
//
//    @Override
//    public String findByNameQuery() {
//        return null;
//    }
//
//    @Override
//    public String deleteQuery() {
//        return null;
//    }
//
//    @Override
//    public String listQuery() {
//        return null;
//    }
//
//    @Override
//    public MainRoute findByName(String name) throws DaoException {
//        return null;
//    }
//}
