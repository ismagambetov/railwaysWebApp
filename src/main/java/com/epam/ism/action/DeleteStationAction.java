package com.epam.ism.action;

import com.epam.ism.entity.Station;
import com.epam.ism.service.StationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteStationAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String stationName = request.getParameter("station");
        Station station = new Station();
        station.setId(Long.parseLong("1"));
        station.setName(stationName);

        StationService stationService = new StationService();
        stationService.delete(station);


        return "success-page";
    }
}
