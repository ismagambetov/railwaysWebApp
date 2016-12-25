package com.epam.ism;

import com.epam.ism.entity.*;

import java.util.ArrayList;
import java.util.List;

public class FactoryMethod {
    private static List<Station> stationList = null;
    private static List<MainRoute> mainRouteList = null;
    private static List<Route> routeList = null;

    public static List<Station> getStationList() {
        stationList  = new ArrayList<>();
        createStations("Костанай,Рудный,Тобол,Кушмурун,Койбагар,Есиль,Атбасар,Астана");
        return stationList;
    }


    private static void createStations(String stations) {
        String[] arr = stations.split(",");

        for (int i = 0; i < arr.length; i++) {
            Station station = new Station();
            station.setId((long) i+1);
            station.setName(arr[i].trim());

            stationList.add(station);
        }

    }


    private static Station findStation(String name) {
        for (Station station : stationList) {
            if (station.getName().equals(name)) return station;
        }

        return null;
    }


    public static List<Train> getTrainList() {
        List<Train> list  = new ArrayList<>();

        Train train = new Train();
        train.setId(Long.parseLong("1"));
        train.setName("038Т");
        train.setMainRoute(mainRouteList.get(0));

        list.add(train);

        return list;

    }


    public static List<MainRoute> getMainRouteList() {
        mainRouteList = new ArrayList<>();

        MainRoute mainRoute = new MainRoute();
        mainRoute.setId(Long.parseLong("1"));
        mainRoute.setFrom(findStation("Костанай"));
        mainRoute.setTo(findStation("Астана"));
        mainRoute.setInterval(Double.parseDouble("707"));
        mainRoute.setPriceForOpenSection(2626.00);
        mainRoute.setPriceForCloseSection(5400.00);
        mainRoute.setRoutes(getRouteList());
        mainRouteList.add(mainRoute);

        return mainRouteList;
    }

    /**
     * Kostanay-Astana.
     * @return Route list.
     */
    private static List<Route> getRouteList() {
        routeList = new ArrayList<>();
        routeList.add(createRoute("1,Костанай,Рудный,22:10,22:50,46.00, 1336.00, 2084.00, 5"));
        routeList.add(createRoute("2,Рудный,Тобол,22:55,23:43,50.00, 1336.00, 2084.00, 20"));
        routeList.add(createRoute("3,Тобол,Кушмурун,00:03,01:39,148.00, 2299.00, 3597.00,   15"));
        routeList.add(createRoute("4,Кушмурун,Койбагар,01:54,02:18,36.00, 1636.00,2582.00, 5"));
        routeList.add(createRoute("5,Койбагар,Есиль,02:23,03:26,104.00, 2054.00,3236.00, 10"));
        routeList.add(createRoute("6,Есиль,Атбасар,03:36,05:08,147.00, 2411.00, 3782.00, 5"));
        routeList.add(createRoute("7,Атбасар,Астана,05:13,07:38,229.00, 2918.00, 3750.00,  0"));

        return routeList;
    }

    private static Route createRoute(String param) {
        Route route = new Route();

        String[] arr = param.split(",");
        route.setId(Long.parseLong(arr[0]));
        route.setFrom(findStation(arr[1].trim()));
        route.setTo(findStation(arr[2].trim()));
        route.setDepartureTime(arr[3].trim());
        route.setArrivalTime(arr[4].trim());
        route.setInterval(Double.parseDouble(arr[5].trim()));
        route.setPriceForOpenSection(Double.parseDouble(arr[6].trim()));
        route.setPriceForCloseSection(Double.parseDouble(arr[7].trim()));
        route.setParkingTime(Integer.parseInt(arr[8].trim()));

        return route;
    }


}
