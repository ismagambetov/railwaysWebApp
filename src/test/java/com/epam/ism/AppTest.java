package com.epam.ism;

import com.epam.ism.dao.jdbc.JdbcDaoUtil;
import com.epam.ism.dao.jdbc.JdbcStationDao;
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
//        StationServiceTest.testDelete(stationService, JdbcStationDao.list.get(0));

        StationServiceTest.testFind(stationService, "Костанай");
        Station from = StationServiceTest.station;

        StationServiceTest.testFind(stationService, "Астана");
        Station to = StationServiceTest.station;

        Date date = JdbcDaoUtil.getDateFromString("2016-12-25");

        TrainServiceTest.testFindAll(from, to, date);
        TrainServiceTest.testFindPlaces("038Т");

    }
}
