package com.epam.ism;

import com.epam.ism.dao.jdbc.JdbcDAOUtil;
import com.epam.ism.entity.Station;
import com.epam.ism.service.StationService;
import junit.framework.TestCase;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    public void testMain() throws Exception {
        StationService stationService = new StationService();
        StationServiceTest.testFind(stationService, "Костанай");
        Station from = StationServiceTest.station;

        StationServiceTest.testFind(stationService, "Астана");
        Station to = StationServiceTest.station;

        Date date = JdbcDAOUtil.getDateFromString("2016-12-25");

        TrainServiceTest.testFindAll(from, to, date);

    }
}
