package com.epam.ism;

import com.epam.ism.entity.Route;
import com.epam.ism.entity.Station;
import com.epam.ism.service.RouteService;
import com.epam.ism.service.ServiceException;
import junit.framework.TestCase;

import java.util.Date;
import java.util.List;

public class RouteServiceTest extends TestCase {

    public static void testFindAll(Station f, Station t) throws ServiceException {

        RouteService routeService = new RouteService();

        List<Route> routes = routeService.findAll(f, t);

        assertNotNull("List of routes expected.", routes);

    }

}
