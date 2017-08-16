/**
 * Created by drake on 16/08/17.
 */

import java.sql.*;

public class Driver {

    public static void main(String[] args) {

        try {

            //get a connection to database
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/acme", "root", "1q2w3e4r");

            //create a statement
            Statement myStmnt = myConn.createStatement();

            //execute SQL query
            String sql = "INSERT INTO customers(firstName, lastName)" +
                    "VALUES ('David', 'Brown')";

            myStmnt.executeUpdate(sql);


            ResultSet myRs = myStmnt.executeQuery("SELECT * FROM customers");

            //process the result set

            while (myRs.next()) {
                System.out.println(myRs.getString("firstName") + " " + myRs.getString("lastName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
