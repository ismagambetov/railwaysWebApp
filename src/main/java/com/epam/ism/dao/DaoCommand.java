package com.epam.ism.dao;

import java.sql.SQLException;

public interface DaoCommand {
    Object execute() throws SQLException;
}
