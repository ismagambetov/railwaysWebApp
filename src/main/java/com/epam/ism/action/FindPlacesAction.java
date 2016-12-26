package com.epam.ism.action;

import com.epam.ism.entity.Place;
import com.epam.ism.service.TrainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class FindPlacesAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String train = request.getParameter("train");

        TrainService trainService = new TrainService();
        Map<String,List<Place>> places = trainService.findPlaces(train);


        return "available-places";
    }
}
