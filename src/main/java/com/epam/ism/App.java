package com.epam.ism;

import com.epam.ism.dao.PassengerDAO;
import com.epam.ism.dao.TrainDAO;

import com.epam.ism.dao.UserDAO;
import com.epam.ism.dao.jdbc.JdbcDAOFactory;
import com.epam.ism.entity.Passenger;
import com.epam.ism.entity.Train;
import com.epam.ism.entity.User;
import com.epam.ism.utils.Password;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {


        JdbcDAOFactory railways_db = JdbcDAOFactory.getInstance("railways_db.jdbc");
        System.out.println("DAOFactory successfully obtained: " + railways_db);

//        Passenger passenger = new Passenger();
//        passenger.setFirstName("Jhon");
//        passenger.setLastName("Roberts");
//        passenger.setIdentificationCode("880504350147");

        PassengerDAO passengerDAO = railways_db.getPassengerDAO();

        //passengerDAO.create(passenger);
        List<User> list = passengerDAO.list();




//        User passenger = new Passenger();
//        passenger.setId(Long.parseLong("1"));
//        passenger.setFirstName("Jhon");
//        passenger.setLastName("Roberts");
//
//        PassengerDAO passengerDAO = railways_db.getPassengerDAO();
//      //  passengerDAO.create(passenger);
//        passengerDAO.list(Passenger.class);


//        Passenger passenger = new Passenger();
//        PassengerDAO passengerDAO = railways_db.getPassengerDAO();
//        passengerDAO.create(passenger);
//        Passenger user = (Passenger) passengerDAO.find((long) 1);
//
//        Passenger passenger1 = new Passenger();
//
//        Long id = passenger.getId();



//
//        Train train = new Train();
//        train.setName("038CP");
//
//        trainDAO.create(train);
//        System.out.println("Train successfully created: " + train);
//
//        // List all trains.
//        List<Train> trains = trainDAO.list();
//        System.out.println("List of trains successfully queried: " + trains);
//        System.out.println("Thus, amount of trains in database is: " + trains.size());
//
//
//        // Get another train by email and password.
//        Train foundAnotherUser = trainDAO.find("038CP");
//        System.out.println("Train successfully queried with name: " + foundAnotherUser);
//
//        // Delete user.
//        trainDAO.delete(train);
//        System.out.println("Train successfully deleted: " + train);
//
//        // List all trains.
//        trains = trainDAO.list();
//        System.out.println("List of trains successfully queried: " + trains);
//        System.out.println("Thus, amount of trains in database is: " + trains.size());

    }
}
