package com.epam.ism.action;

import com.epam.ism.entity.Route;
import com.epam.ism.entity.Station;
import com.epam.ism.entity.Train;
import com.epam.ism.entity.Wagon;
import com.epam.ism.service.RouteService;
import com.epam.ism.service.ServiceException;
import com.epam.ism.service.StationService;
import com.epam.ism.service.TrainService;
import com.epam.ism.utils.DateTimeUtil;
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
            throw new ActionException("");
        }

        String trainId = request.getParameter("trainId");
        String depStationId = request.getParameter("depStationId");
        String arrStationId = request.getParameter("arrStationId");
        String depDate = request.getParameter("depDate");
        String arrDate = request.getParameter("arrDate");
        String distance = request.getParameter("distance");

        TrainService trainService = new TrainService();
        StationService stationService = new StationService();
        RouteService routeService = new RouteService();

        Route route;
        List<Wagon> wagons;
        try {
            Train train = trainService.find(trainId,"byId");
            Station depStation = stationService.find(depStationId,"byId");
            Station arrStation = stationService.find(arrStationId,"byId");

            Date departureDate = DateTimeUtil.getDateFromString(depDate);

            if (train == null || depStation == null || arrStation == null || departureDate == null) {
                request.setAttribute("msg","A value of the given parameters is null.");
                return "error";
            }

            route = routeService.getRoute(train,depStation,arrStation,distance,depDate,arrDate);

            wagons = trainService.getWagons(train, departureDate);
        } catch (ServiceException e) {
            throw new ActionException("Something failed at database level. " + e.getMessage());
        }

        request.setAttribute("route", route);
        request.setAttribute("wagons", wagons);
        request.setAttribute("depDate", request.getParameter("depDate"));

        return "train-info";
    }
}
