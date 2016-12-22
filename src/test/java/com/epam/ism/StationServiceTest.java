package com.epam.ism;

import com.epam.ism.entity.Station;
import com.epam.ism.service.StationService;
import junit.framework.TestCase;

public class StationServiceTest extends TestCase {

    public void testFindStation(String name) {
        StationService stationService = new StationService();
        Station station = stationService.find(name);
        assertNotNull("An instance of station is expected.", station);
    }

}
