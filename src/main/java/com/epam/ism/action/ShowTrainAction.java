package com.epam.ism.action;

import com.epam.ism.dao.jdbc.JdbcDaoUtil;
import com.epam.ism.entity.Wagon;
import com.epam.ism.service.ServiceException;
import com.epam.ism.service.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

public class ShowTrainAction implements Action {

    final static Logger logger = LoggerFactory.getLogger(ShowTrainAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int trainId = Integer.parseInt(request.getParameter("trainId"));
        String trainName = request.getParameter("train");
        String depDate = request.getParameter("depDate");
        Date departureDate = JdbcDaoUtil.getDateFromString(depDate);

        TrainService trainService = new TrainService();

        List<Wagon> wagons;
        try {
            wagons = trainService.getWagons(trainId, departureDate);
        } catch (ServiceException e) {
            throw new ActionException("Something failed at database level. " + e.getMessage());
        }

        request.setAttribute("train", trainName);
        request.setAttribute("depStation", request.getParameter("depStation"));
        request.setAttribute("arrStation", request.getParameter("arrStation"));
        request.setAttribute("depTime", request.getParameter("depTime"));
        request.setAttribute("arrTime", request.getParameter("arrTime"));
        request.setAttribute("depDate", request.getParameter("depDate"));
        request.setAttribute("wagons", wagons);

        return "train-info";
    }
}
