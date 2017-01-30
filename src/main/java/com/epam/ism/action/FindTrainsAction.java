package com.epam.ism.action;

import com.epam.ism.dao.jdbc.JdbcDaoUtil;
import com.epam.ism.entity.Route;
import com.epam.ism.entity.Station;
import com.epam.ism.service.RouteService;
import com.epam.ism.service.ServiceException;
import com.epam.ism.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

public class FindTrainsAction implements Action {
    final static Logger logger = LoggerFactory.getLogger(FindTrainsAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        Station departureStation;
        Station arrivalStation;
        Date departureDate;

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

        departureDate = JdbcDaoUtil.getDateFromString(dateStr);
        Date today = new Date();
        if (departureDate.before(today)) {
            request.setAttribute("msg","Departure date can't be before today's date.");
            return "error";
        }

        StationService stationService = new StationService();
        departureStation = stationService.find(f);
        arrivalStation = stationService.find(t);

        if (departureStation == null || arrivalStation == null) {
            request.setAttribute("msg","Stations must be filled.");
            return "error";
        }

        logger.info("Call RouteService...");
        RouteService routeService = new RouteService(); // TODO: 26.12.2016 implement calling services using factory

        List<Route> routes;
        try {
            routes = routeService.findAll(departureStation, arrivalStation);
            logger.info("Founded routes by user query: " + routes.size());
        } catch (ServiceException e) {
            throw new ActionException("Something failed at database level.", e);
        }

        request.setAttribute("route",f+"-"+t);
        request.setAttribute("routes", routes);
        request.setAttribute("depDate", dateStr);

        return "available-trains";
    }

}
