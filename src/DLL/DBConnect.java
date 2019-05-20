package DLL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    public static Connection getConnection()
    {
        try {
            //gets driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //connection string properties
            // different connection strings for mysql8...
            String url = "jdbc:mysql://localhost:3306/travelexperts?serverTimezone=UTC";
            String username = "admin";
            String password = "P@ssw0rd";

            //returns connection
            return DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException e) { e.printStackTrace();}

        return null;
    }
}
