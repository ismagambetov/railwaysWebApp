package com.epam.ism.action;

import com.epam.ism.dao.jdbc.JdbcDAOUtil;
import com.epam.ism.entity.MainRoute;
import com.epam.ism.entity.Station;
import com.epam.ism.entity.Train;
import com.epam.ism.service.RouteService;
import com.epam.ism.service.StationService;
import com.epam.ism.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public class FindTrainsAction implements Action {
    private Station from;
    private Station to;
    private Date departureDay;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        //validate data here
        String dateStr = request.getParameter("departureDate");
        String f = request.getParameter("from");
        String t = request.getParameter("to");

        boolean validate = isValidate(dateStr, f, t);
        if (!validate) return "error";

        //call RouteService
        RouteService routeService = new RouteService();
        MainRoute route = routeService.getRoute(from, to);

        if (route == null) return "error";

        //call TrainService
        TrainService trainService = new TrainService();
        List<Train> trains = trainService.findAll(route, departureDay);

        HttpSession session = request.getSession();
        session.setAttribute("availableTrains", trains);

        //return new view
        return "available-trains";
    }

    private boolean isValidate(String dateStr, String f, String t) {

        departureDay = JdbcDAOUtil.getDateFromString(dateStr);
        Date today = new Date();
        if (departureDay.before(today)) {
            return false;
        }

        StationService stationService = new StationService();
        from = stationService.find(f);
        to = stationService.find(t);

        return !(from == null || to == null);

    }
}
