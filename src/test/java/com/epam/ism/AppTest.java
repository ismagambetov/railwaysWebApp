package com.epam.ism;

import com.epam.ism.dao.jdbc.JdbcDAOFactory;
import com.epam.ism.dao.UserDAO;
import com.epam.ism.entity.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
//    private void doTest() {
//        JdbcDAOFactory railways_db = JdbcDAOFactory.getInstance("railways_db.jdbc");
//        System.out.println("DAOFactory successfully obtained: " + railways_db);
//
//        // Obtain UserDAO.
//        UserDAO userDAO = railways_db.getUserDAO();
//        System.out.println("UserDAO successfully obtained: " + userDAO);
//
//        // Create user.
//        User user = new User();
//        user.setEmail("foo@bar.com");
//        user.setPassword("password");
//        userDAO.create(user);
//        System.out.println("User successfully created: " + user);
//
//        // Create another user.
//        User anotherUser = new User();
//        anotherUser.setEmail("bar@foo.com");
//        anotherUser.setPassword("anotherPassword");
//        anotherUser.setFirstName("Bar");
//        anotherUser.setLastName("Foo");
//        userDAO.create(anotherUser);
//        System.out.println("Another user successfully created: " + anotherUser);
//
//        // Update user.
//        user.setFirstName("Foo");
//        user.setLastName("Bar");
//        userDAO.update(user);
//        System.out.println("User successfully updated: " + user);
//
//        // List all users.
//        List<User> users = userDAO.list();
//        System.out.println("List of users successfully queried: " + users);
//        System.out.println("Thus, amount of users in database is: " + users.size());
//
//        // Delete user.
//        userDAO.delete(user);
//        System.out.println("User successfully deleted: " + user);
//
//        // Check if email exists.
//        boolean exist = userDAO.existEmail("foo@bar.com");
//        System.out.println("This email should not exist anymore, so this should print false: " + exist);
//
//        // Change password.
//        anotherUser.setPassword("newAnotherPassword");
//        userDAO.changePassword(anotherUser);
//        System.out.println("Another user's password successfully changed: " + anotherUser);
//
//        // Get another user by email and password.
//        User foundAnotherUser = userDAO.find("bar@foo.com", "newAnotherPassword");
//        System.out.println("Another user successfully queried with new password: " + foundAnotherUser);
//
//        // Delete another user.
//        userDAO.delete(foundAnotherUser);
//        System.out.println("Another user successfully deleted: " + foundAnotherUser);
//
//        // List all users again.
//        users = userDAO.list();
//        System.out.println("List of users successfully queried: " + users);
//        System.out.println("Thus, amount of users in database is: " + users.size());
//    }
//
//


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {

        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
       // doTest();
        assertTrue( true );
    }
}
