package com.epam.ism.dao;

import com.epam.ism.dao.exception.DaoException;

import java.sql.SQLException;

public interface DaoCommand {
    Object execute() throws SQLException, DaoException;
}
