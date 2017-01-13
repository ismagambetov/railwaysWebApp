package com.epam.ism.dao;

import java.sql.SQLException;

public class DaoManagerException extends RuntimeException {
    public DaoManagerException(String s, SQLException e) {
    }

    public DaoManagerException(String message) {
        super(message);
    }

    public DaoManagerException(Throwable cause) {
        super(cause);
    }

    public DaoManagerException(String s, Exception e) {

    }
}
