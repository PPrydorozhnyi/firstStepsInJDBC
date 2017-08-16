import java.sql.*;

/**
 * Created by drake on 16/08/17.
 */
public class Preparedsratements {

    private static final String url = "jdbc:mysql://localhost:3306/acme";
    private static final String user = "root";
    private static final String password = "1q2w3e4r";

    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {

            myConn = DriverManager.getConnection(url, user, password);

            myStmt = myConn.prepareStatement("SELECT * FROM customers WHERE id = ? OR firstName=?");

            myStmt.setDouble(1, 2);
            myStmt.setString(2, "John");

            myRs = myStmt.executeQuery();

            display(myRs);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }

    }

    private static void display(ResultSet myRs) throws SQLException {
        while (myRs.next()) {
            String lastName = myRs.getString("lastName");
            String firstName = myRs.getString("firstName");
            //double salary = myRs.getDouble("salary");
            //String department = myRs.getString("department");

            System.out.printf("%s, %s\n", lastName, firstName);
        }
    }

}
