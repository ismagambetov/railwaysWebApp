package com.epam.ism.action;

import com.epam.ism.entity.Argument;
import com.epam.ism.service.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RouteAddAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String routeType = request.getParameter("routeType");
        Argument.add(request.getParameter("from"));
        Argument.add(request.getParameter("to"));
        Argument.add(request.getParameter("departureTime"));
        Argument.add(request.getParameter("arrivalTime"));
        Argument.add(request.getParameter("interval"));
        Argument.add(request.getParameter("priceForOpenSection"));
        Argument.add(request.getParameter("priceForCloseSection"));
        Argument.add(request.getParameter("train"));
        Argument.add(request.getParameter("routes"));

        //validation a data
        //................

        RouteService routeService = new RouteService();
        routeService.add(Argument.get());


        return "route-add";
    }
}
