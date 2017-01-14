package com.epam.ism.action;

import com.epam.ism.dao.DaoFactory;
import com.epam.ism.dao.DaoManager;
import com.epam.ism.dao.TrainDao;
import com.epam.ism.dao.exception.DaoException;
import com.epam.ism.entity.Train;
import com.epam.ism.service.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CheckTrainAction implements Action {

    final static Logger logger = LoggerFactory.getLogger(CheckTrainAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        DaoFactory factory = DaoFactory.getFactory();
        DaoManager daoManager = DaoFactory.getDaoManager();

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //validation
        String trainName = request.getParameter("name");
        Train train;

        if (trainName == null) {
            request.setAttribute("msg","Unexceptable parameter");
            return "error";
        } else {
            TrainService trainService = new TrainService();
            train = trainService.findByName(trainName);
            if (train == null) {
                request.setAttribute("msg", "Train №" + trainName +" does not exist in the database.");
                return "error";
            }
        }




        return "train-info";
    }
}
