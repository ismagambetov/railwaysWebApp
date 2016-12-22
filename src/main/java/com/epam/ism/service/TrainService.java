package com.epam.ism.service;

import com.epam.ism.dao.jdbc.JdbcDAOUtil;
import com.epam.ism.entity.MainRoute;
import com.epam.ism.entity.Train;

import java.util.Date;
import java.util.List;

public class TrainService {

    public List<Train> findAll(MainRoute route, Date date) {

        List<Train> list = JdbcDAOUtil.getEmptyList(Train.class);
        list.add(route.getTrain());

        return list;
    }
}
