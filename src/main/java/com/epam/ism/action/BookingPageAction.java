package com.epam.ism.action;

import com.epam.ism.entity.User;
import com.epam.ism.entity.Wagon;
import com.epam.ism.service.OrderService;
import com.epam.ism.service.TrainService;
import com.epam.ism.utils.DateTimeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookingPageAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        DateTimeUtil.setAttributes(request,"username,route,depDate,arrDate,train,wagonNum,placeNum");

        try {
            OrderService orderService = new OrderService();
            TrainService trainService = new TrainService();
            int cost = orderService.calcCost();
            User passenger = orderService.getUser();
            Wagon wagon = trainService.getWagon();

            request.setAttribute("cost",cost);
            request.setAttribute("passenger",passenger.toString());
            request.setAttribute("wagonCat",wagon.getWagonCategory());


        } catch (Exception e) {
            throw new ActionException(e.getMessage());
        }



        return "booking";
    }

}
