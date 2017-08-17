
import java.sql.*;

/**
 * Created by drake on 16/08/17.
 */
public class GetCountForDepartment {

    private static final String url = "jdbc:mysql://localhost:3306/demo";
    private static final String user = "root";
    private static final String password = "1q2w3e4r";

    public static void main(String[] args) {

        Connection myConn;
        CallableStatement myStmt;

        try {

            myConn = DriverManager.getConnection(url, user, password);

            String theDepartment = "Engineering";

            myStmt = myConn.prepareCall("{call get_count_for_department(?,?)}");

            myStmt.setString(1, theDepartment);
            myStmt.registerOutParameter(2, Types.INTEGER);

            myStmt.execute();

            int theCount = myStmt.getInt(2);

            System.out.println("\nThe count = " + theCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
