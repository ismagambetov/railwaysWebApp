package com.epam.ism.action;

import com.epam.ism.service.RouteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class  RouteAddAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        RouteService routeService = new RouteService();

        //routeService.add();

        return "route-add";
    }
}
