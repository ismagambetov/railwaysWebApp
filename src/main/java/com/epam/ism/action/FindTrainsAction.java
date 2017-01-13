package com.epam.ism.action;

import com.epam.ism.dao.jdbc.JdbcDaoUtil;
import com.epam.ism.entity.MainRoute;
import com.epam.ism.entity.Route;
import com.epam.ism.entity.Station;
import com.epam.ism.entity.Train;
import com.epam.ism.service.RouteService;
import com.epam.ism.service.ServiceException;
import com.epam.ism.service.StationService;
import com.epam.ism.service.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FindTrainsAction implements Action {
    final static Logger logger = LoggerFactory.getLogger(FindTrainsAction.class);

    private Station departureStation;
    private Station arrivalStation;
    private Date departureDate;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //validate data here
        String dateStr = request.getParameter("departureDate");
        String f = request.getParameter("from");
        String t = request.getParameter("to");

        logger.info("Retrieving trains by user query: " + f + "," + t + "," + dateStr);

        logger.info("Data validation process is started.");
        boolean validate = isValid(dateStr, f, t);
        if (!validate) return "error";
        logger.info("Validation result: successfully.");

        logger.info("Call RouteService...");
        // TODO: 26.12.2016 implement calling services using factory
        RouteService routeService = new RouteService();

        List<Route> routes;
        try {
            routes = routeService.findAll(departureStation, arrivalStation);
            logger.info("Founded trains by user query: " + routes.size());
        } catch (ServiceException e) {
            throw new ActionException("Something failed at database level.", e);
        }

        request.setAttribute("route",f+"-"+t);
        request.setAttribute("routes", routes);

        //return new view
        return "available-trains";
    }

    // TODO: 26.12.2016 to rid out of class variables.
    private boolean isValid(String dateStr, String f, String t) {

        departureDate = JdbcDaoUtil.getDateFromString(dateStr);
        Date today = new Date();
        if (departureDate.before(today)) {
            return false;
        }

        StationService stationService = new StationService();
        departureStation = stationService.find(f);
        arrivalStation = stationService.find(t);

        return !(departureStation == null || arrivalStation == null);

    }
}
