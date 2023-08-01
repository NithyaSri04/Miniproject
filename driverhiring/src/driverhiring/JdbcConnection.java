package driverhiring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    // Database credentials (encapsulation)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/miniproject";
    private static final String USER = "root";
    private static final String PASSWORD = "01@Jekook";

    // JDBC driver and connection
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection = null;
    
    // Static method to get a connection instance (Singleton design pattern)
    public static Connection getConnection() {
        if (connection != null) {
            return connection; // If the connection is already established, return it.
        }

        try {
            // Step 1: Register the JDBC driver(abstraction)
            Class.forName(JDBC_DRIVER);

            // Step 2: Open a connection
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // Connection successful
            System.out.println("Connected to the database!");

        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load MySQL JDBC driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }

        return connection;
    }
 
     // Static method to close the connection

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close the database connection.");
                e.printStackTrace();
            }
        }
    }
}
