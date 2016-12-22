package com.epam.ism;

import com.epam.ism.action.FindTrainsAction;
import com.epam.ism.entity.Train;
import com.epam.ism.service.TrainService;
import junit.framework.TestCase;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    public void testMain() throws Exception {



        TrainServiceTest.findAllTrains("Караганда","Алматы","2016-12-23");

    }
}
