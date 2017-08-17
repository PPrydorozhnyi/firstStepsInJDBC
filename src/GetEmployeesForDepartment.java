import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by drake on 17/08/17.
 */
public class GetEmployeesForDepartment {

    private static final String url = "jdbc:mysql://localhost:3306/demo";
    private static final String user = "root";
    private static final String password = "1q2w3e4r";

    public static void main(String[] args) {

        Connection myConn = null;
        CallableStatement myStmt = null;
        ResultSet myRs = null;

        try {

            myConn = DriverManager.getConnection(url, user, password);

            myStmt = myConn.prepareCall("{call get_employees_for_department(?)}");

            myStmt.setString(1, "Engineering");

            myStmt.execute();

            myRs = myStmt.getResultSet();

            display(myRs);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void display(ResultSet myRs) throws Exception {

        while (myRs.next()) {
            String lastName = myRs.getString("last_name");
            String firstName = myRs.getString("first_name");
            double salary = myRs.getDouble("salary");
            String department = myRs.getString("department");

            System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
        }

    }

}
