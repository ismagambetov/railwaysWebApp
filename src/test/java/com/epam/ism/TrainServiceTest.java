package com.epam.ism;

import com.epam.ism.entity.Place;
import com.epam.ism.entity.Station;
import com.epam.ism.entity.Train;
import com.epam.ism.service.TrainService;
import junit.framework.TestCase;

import java.util.Date;
import java.util.List;


public class TrainServiceTest extends TestCase {

    public static void testFindAll(Station f, Station t, Date date) throws Exception {

        TrainService trainService = new TrainService();
        List<Train> trains = trainService.findAll(f, t, date);
        assertNotNull("List of trains expected.", trains);
    }


    public static void testFindPlaces(String name) {
        TrainService trainService = new TrainService();
        List<Place> places = trainService.findPlaces(name);
        assertNotNull("List of places expected.",places);
    }
}
