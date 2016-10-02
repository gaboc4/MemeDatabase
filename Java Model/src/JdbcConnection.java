/**
 * Created by JoshHoffman on 10/1/16.
 */

import java.sql.*;

public class JdbcConnection {

    public static void main(String[] args) {
        try {
            // Step 1: "Load" the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Step 2: Establish the connection to the database
            String url = "jdbc:mysql://104.236.77.117:22/images";
            Connection conn = DriverManager.getConnection(url,
                    "josh",
                    "joshy");
        }
        catch (Exception e) {
            System.err.println("D'oh! Got an exception!");
            System.err.println(e.getMessage());
        }
    }

}
