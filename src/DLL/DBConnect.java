package DLL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    public static Connection getConnection()
    {
        try {
            //gets driver
            Class.forName("com.mysql.jdbc.Driver");

            //connection string properties
            String url = "jdbc:mysql://localhost:3306/travelexperts";
            String username = "root";
            String password = null;

            //returns connection
            return DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException | SQLException e) { e.printStackTrace();}

        return null;
    }
}
