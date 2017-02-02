package com.epam.ism;

import com.epam.ism.dao.jdbc.JdbcDaoUtil;
import com.epam.ism.dao.jdbc.JdbcStationDao;
import com.epam.ism.entity.Station;
import com.epam.ism.service.StationService;
import com.epam.ism.utils.Password;
import junit.framework.TestCase;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    public void testMain() throws Exception {
        //admin
        //hello
        //super
        String admin = Password.getSaltedHash("pass");

        System.out.println(admin);

        boolean admin1 = Password.check("admin", admin);

        System.out.println(admin1);

//        StationService stationService = new StationService();
//
//        StationServiceTest.testFind(stationService, "Костанай");
//        Station from = StationServiceTest.station;
//
//        assertNotNull("Departure station - 'Костанай' expected: ", from.getName());
//
//        StationServiceTest.testFind(stationService, "Астана");
//        Station to = StationServiceTest.station;
//        assertNotNull("Arrival station - 'Астана' expected: ", to.getName());
//
//        //Date date = JdbcDaoUtil.getDateFromString("2016-12-25");
//
//        RouteServiceTest.testFindAll(from, to);


    }
}
