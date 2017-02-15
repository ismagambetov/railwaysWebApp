package com.epam.ism.service;

import com.epam.ism.dao.DaoCommand;
import com.epam.ism.dao.DaoFactory;
import com.epam.ism.dao.DaoManager;
import com.epam.ism.dao.UserDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.Role;
import com.epam.ism.entity.User;
import com.epam.ism.utils.DateTimeUtil;
import com.epam.ism.utils.Password;
import com.epam.ism.utils.RowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    final static Logger logger = LoggerFactory.getLogger(LoginService.class);
    private DaoFactory factory;
    private DaoManager daoManager;

    public LoginService() {
        factory = DaoFactory.getFactory();
        logger.info("DaoFactory.getFactory() from class LoginService" + factory);
        daoManager = DaoFactory.getDaoManager();
    }

    public User find(String username, String password) throws ServiceException {

        User user = (User) daoManager.transactionAndReturnCon(new DaoCommand() {
            @Override
            public Object execute() throws SQLException, DaoException {
                UserDao userDao = factory.getUserDao(daoManager);

                userDao.map(new RowMapper() {
                    @Override
                    public User mapRow(ResultSet rs) throws SQLException {
                        User user = new User();
                        user.setUsername(rs.getString(1));
                        user.setFirstName(rs.getString(2));
                        user.setLastName(rs.getString(3));
                        user.setPersonalCode(rs.getString(4));
                        user.setBirthday(DateTimeUtil.toUtilDate(rs.getDate(5)));

                        if (!password.isEmpty()) user.setPassword(rs.getString(6));

                        user.setRole(Role.valueOf(rs.getString(7).toUpperCase()));
                        return user;
                    }
                });

                return userDao.findByName(username);
            }
        });

        if (!password.isEmpty()) {
            try {
                boolean isValid = Password.check(password, user.getPassword());
                if (!isValid) return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;

    }


}
