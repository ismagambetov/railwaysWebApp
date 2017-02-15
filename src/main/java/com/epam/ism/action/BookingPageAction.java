package com.epam.ism.action;

import com.epam.ism.entity.Station;
import com.epam.ism.entity.Train;
import com.epam.ism.entity.User;
import com.epam.ism.entity.Wagon;
import com.epam.ism.service.*;
import com.epam.ism.utils.DateTimeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookingPageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String username = request.getParameter("username");
        String depStationId = request.getParameter("depStationId");
        String arrStationId = request.getParameter("arrStationId");
        String depDate = request.getParameter("depDate");
        String arrDate = request.getParameter("arrDate");
        String trainId = request.getParameter("trainId");
        String wagonNum = request.getParameter("wagonNum");
        String placeNum = request.getParameter("placeNum");

        try {
            LoginService loginService = new LoginService();
            User user = loginService.find(username, "");

            // TODO: 14.02.2017 нужно ли тут делать валидацию ?
//            if (user==null) {
//                request.setAttribute("msg","User '" + username + "' is not found in the database.");
//                return "error";
//            }

            StationService stationService = new StationService();
            Station departureStation = stationService.find(depStationId, "byId");
            Station arrivalStation = stationService.find(arrStationId, "byId");
//            if (departureStation==null || arrivalStation==null) {
//                request.setAttribute("msg","Either the station of departure or arrival is not found in the database.");
//                return "error";
//            }

            TrainService trainService = new TrainService();
            Train train = trainService.find(trainId, "byId");
//            if (train==null) {
//                request.setAttribute("msg","Train '" + trainId + "' is not found in the database.");
//                return "error";
//            }

            Wagon wagon = trainService.findWagon(train, wagonNum);



        } catch (ServiceException e) {
            throw new ActionException(e.getMessage());
        }



        return "booking";
    }

}
