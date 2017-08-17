import java.sql.*;

/**
 * Created by drake on 16/08/17.
 */
public class IncreaseSalary {

    private static final String url = "jdbc:mysql://localhost:3306/demo";
    private static final String user = "root";
    private static final String password = "1q2w3e4r";

    public static void main(String[] args) {
        Connection myConn = null;
        CallableStatement myStmt = null;

        try {

            myConn = DriverManager.getConnection(url, user, password);

            String theDepartment = "Engineering";
            int theIncreaseAmount = 10000;

            System.out.println("Salaries BEFORE\n");
            showSalaries(myConn, theDepartment);

            myStmt = myConn.prepareCall("{call increase_salaries_for_department(?,?)}");

            myStmt.setString(1, theDepartment);
            myStmt.setDouble(2, theIncreaseAmount);

            myStmt.execute();

            System.out.println("\nSalaries AFTER\n");
            showSalaries(myConn, theDepartment);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showSalaries(Connection myConn, String theDepartment) throws Exception {

        Statement myStmt = myConn.createStatement();

        ResultSet myRs = myStmt.executeQuery("SELECT * FROM employees " +
                "WHERE department = '" + theDepartment + "';");

        while (myRs.next()) {
            String lastName = myRs.getString("last_name");
            String firstName = myRs.getString("first_name");
            double salary = myRs.getDouble("salary");
            String department = myRs.getString("department");

            System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
        }

    }

}
