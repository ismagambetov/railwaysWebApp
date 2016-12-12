package com.epam.ism;

import com.epam.ism.dao.*;
import com.epam.ism.dao.jdbc.*;
import com.epam.ism.entity.*;
import com.epam.ism.utils.Password;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    final static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) throws Exception {

        JdbcDAOFactory daoFactory = JdbcDAOFactory.getInstance("railways_db.jdbc");
        logger.info("DAOFactory successfully obtained: " + daoFactory);

        logger.info("KOSTANAY-ASTANA");

        StationDAO stationDAO = daoFactory.getStationDAO();
        logger.info("StationDAO successfully obtained: " + stationDAO);

        //carriages......
        logger.info("....creating train Kostanay-Astana....");
        TrainDAO trainDAO = daoFactory.getTrainDAO();
        logger.info("TrainDAO successfully obtained: " + trainDAO);

        CarriageDAO carriageDAO = daoFactory.getCarriageDAO();
        logger.info("CarriageDAO successfully obtained: " + carriageDAO);
        for (int i = 0; i < 5; i++) carriageDAO.add(carriageDAO.createAndGet());
        List<Carriage> carriages = carriageDAO.getList();
        logger.info("Carriages list was created and got successfully: " + carriages);

        trainDAO.add(trainDAO.createAndGet("091Т СКРСТ"));
        Train tKostanayAstana = trainDAO.getByName("091Т СКРСТ");
        tKostanayAstana.setCarriages(carriages); //добавили вагоны к поезду
        logger.info("Train Kostanay-Astana was created successfully: " + tKostanayAstana); //состав сформирован

        logger.info("....creating stations between Kostanay-Astana");
        stationDAO.add(stationDAO.createAndGet("Kostanay"));
        stationDAO.add(stationDAO.createAndGet("Rudniy"));
        stationDAO.add(stationDAO.createAndGet("Tobol"));
        stationDAO.add(stationDAO.createAndGet("Kushmurun"));
        stationDAO.add(stationDAO.createAndGet("Koibagar"));
        stationDAO.add(stationDAO.createAndGet("Esil"));
        stationDAO.add(stationDAO.createAndGet("Atbasar"));
        stationDAO.add(stationDAO.createAndGet("Astana"));

        List<Station> stations = stationDAO.getList();

        logger.info("Stations located between Kostanay-Astana were created: " + stations);

        RouteDAO routeDAO = daoFactory.getRouteDAO();
        logger.info("RouteDAO successfully obtained: " + routeDAO);

        logger.info(".....creating list of routes which covered Main route Kostanay-Astana.....");
        routeDAO.add(stationDAO.getByName("Kostanay"), stationDAO.getByName("Rudniy"), "22:10", "22:50", 46.00, 5);
        routeDAO.add(stationDAO.getByName("Rudniy"), stationDAO.getByName("Tobol"), "22:55", "23:43", 50.00, 20);
        routeDAO.add(stationDAO.getByName("Tobol"),stationDAO.getByName("Kushmurun"), "00:03", "01:39", 148.00, 15);
        routeDAO.add(stationDAO.getByName("Kushmurun"),stationDAO.getByName("Koibagar"), "01:54", "02:18", 36.00, 5);
        routeDAO.add(stationDAO.getByName("Koibagar"),stationDAO.getByName("Esil"), "02:23", "03:26", 104.00, 10);
        routeDAO.add(stationDAO.getByName("Esil"),stationDAO.getByName("Atbasar"), "03:36", "05:08", 147.00, 5);
        routeDAO.add(stationDAO.getByName("Atbasar"),stationDAO.getByName("Astana"), "05:13", "07:38", 229.00, 0);
        List<Route> routes = routeDAO.getList();
        logger.info("Routes Kostanay-Astana was created successfully and got: " + routes);

        MainRouteDAO mainRouteDAO = daoFactory.getMainRouteDAO();
        logger.info("MainRouteDAO successfully obtained: " + mainRouteDAO);

        logger.info("......creating list of Main routes Kostanay-Astana......");
        mainRouteDAO.add(mainRouteDAO.createAndGet(stationDAO.getByName("Kostanay"), stationDAO.getByName("Astana"),
                "22:10", "07:38", 707.00, 11.00, 6.00, tKostanayAstana, routes));
        //here another main routes can be added....
        //here another main routes can be added....
        //here another main routes can be added....

        List<MainRoute> mainRoutes = mainRouteDAO.getList();
        logger.info("Main routes list was got successfully: " + mainRoutes);

        //logger.info("KOSTANAY-KARAGANDA");
        // next route, train ....

        logger.info("...catching passenger's query....");
        UserDAO userDAO = daoFactory.getUserDAO();
        logger.info("UserDAO successfully obtained: " + userDAO);

        //Date parser.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        String saltedPassword = Password.getSaltedHash("password");
        User passenger1 = userDAO.createAndGet("Jhon","Roberts","880519350478",dateFormat.parse("09/01/1985"),
                saltedPassword, "roberts@gmail.com",Role.PASSENGER);
        logger.info("User was created successfully: " + passenger1);
        userDAO.add(passenger1);

        logger.info("...creating request according to passenger's query.....");
        RequestDAO requestDAO = daoFactory.getRequestDAO();
        logger.info("RequestDAO successfully obtained: " + requestDAO);
        Date departureDate = dateFormat.parse("10/12/2016");
        Request request1 = requestDAO.createAndGet(userDAO.getByCode("880519350478"), departureDate,
                stationDAO.getByName("Kostanay"), stationDAO.getByName("Tobol"));
        logger.info("Order was created successfully: " + request1);
        requestDAO.add(request1);
        List<Request> requests = requestDAO.getList();
        logger.info("Order list was created successfully: " + requests);

        //// TODO: 12.12.2016 TrainDAO instead of MainRouteDAO to get trains
        List<Train> trains = mainRouteDAO.findByRequest(mainRoutes, request1);
        if (trains.isEmpty()) {
            logger.info("Trains going through requested route was not found.");
            return;
        }

        OrderDAO orderDAO = daoFactory.getOrderDAO();
        logger.info("OrderDAO successfully obtained: " + orderDAO);

        List<Order> orders = orderDAO.getList();
        logger.info("Orders list was got successfully.");

        List<Place> bookedPlaces = orderDAO.getBookedPlaces(departureDate,trains,orders);
        logger.info("Booked places list was got from Orders list successfully: " + bookedPlaces);

        //trainDAO.getPlaceInfo(bookedPlaces);



    }
}
