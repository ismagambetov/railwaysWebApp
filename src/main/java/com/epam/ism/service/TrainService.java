package com.epam.ism.service;

import com.epam.ism.dao.DaoFactory;
import com.epam.ism.dao.OrderDao;
import com.epam.ism.dao.TrainDao;
import com.epam.ism.dao.jdbc.JdbcDaoUtil;
import com.epam.ism.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TrainService {
    final static Logger logger = LoggerFactory.getLogger(TrainService.class);
    private DaoFactory factory;

    public TrainService() {
        factory = DaoFactory.getFactory();
        logger.info("DaoFactory.getFactory() from class TrainService" + factory);
    }


}
