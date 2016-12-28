package com.epam.ism.action;

import com.epam.ism.dao.jdbc.JdbcDAOUtil;
import com.epam.ism.entity.Station;
import com.epam.ism.entity.Train;
import com.epam.ism.service.ServiceException;
import com.epam.ism.service.StationService;
import com.epam.ism.service.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public class FindTrainsAction implements Action {
    final static Logger logger = LoggerFactory.getLogger(FindTrainsAction.class);

    private Station departureFrom;
    private Station arrivalTo;
    private Date departureDate;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        //validate data here
        String dateStr = request.getParameter("departureDate");
        String f = request.getParameter("from");
        String t = request.getParameter("to");

        logger.info("Retrieving trains by user query: " + f + "," + t + "," + dateStr);

        logger.info("Data validation process is started.");
        boolean validate = isValid(dateStr, f, t);
        if (!validate) return "error";
        logger.info("Validation result: successfully.");

        logger.info("Call TrainService...");
        // TODO: 26.12.2016 implement calling services using factory
        TrainService trainService = new TrainService();

        List<Train> trains;
        try {
            trains = trainService.findAll(departureFrom, arrivalTo, departureDate);
            logger.info("Founded trains by user query: " + trains.size());
        } catch (ServiceException e) {
            throw new ActionException("Something failed at database level.", e);
        }

        request.setAttribute("availableTrains", trains);

        //return new view
        return "available-trains";
    }

    // TODO: 26.12.2016 to rid out of class variables.
    private boolean isValid(String dateStr, String f, String t) {

        departureDate = JdbcDAOUtil.getDateFromString(dateStr);
        Date today = new Date();
        if (departureDate.before(today)) {
            return false;
        }

        StationService stationService = new StationService();
        departureFrom = stationService.find(f);
        arrivalTo = stationService.find(t);

        return !(departureFrom == null || arrivalTo == null);

    }
}
