package com.epam.ism;

import com.epam.ism.entity.Station;
import com.epam.ism.service.StationService;
import junit.framework.TestCase;

public class StationServiceTest extends TestCase {
    public static Station station;

    public static void testFind(StationService service, String name) {
        station = service.find(name,"");
        assertNotNull("Station object expected.", station);
    }

}
