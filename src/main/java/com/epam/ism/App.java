package com.epam.ism;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/transporter_db","admin","admin");
            ps = con.prepareStatement("SELECT * FROM ORDERS");
            resultSet = ps.executeQuery();
            while (resultSet.next()) {

                String address_to = resultSet.getString("ADDRESS_TO");
                System.out.println(address_to);

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }


    }
}
