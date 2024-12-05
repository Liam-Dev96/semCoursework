package com.napier.semCW;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://db/world-db/world.sql:3306/world?useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "example";

    // Retry parameters
    private static final int MAX_RETRIES = 10;
    private static final int RETRY_DELAY_MS = 5000;  // 5 seconds

    // Private connection instance
    private Connection connection;

    // Constructor
    public DatabaseConnection() {
        initializeConnection();
    }

    /**
     * Initializes the database connection with retry logic.
     */
    private void initializeConnection() {
        try {
            // Load MySQL database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Could not load MySQL driver: " + e.getMessage());
            System.exit(-1);
        }

        // Attempt to establish a connection with retries
        for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {
            try {
                System.out.println("Attempting to connect to the database (Attempt " + attempt + " of " + MAX_RETRIES + ")");
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                System.out.println("Successfully connected to the database");
                break;
            } catch (SQLException e) {
                System.err.println("Connection attempt " + attempt + " failed: " + e.getMessage());
                if (attempt == MAX_RETRIES) {
                    System.err.println("Max retry attempts reached. Exiting application.");
                    System.exit(-1);
                }
                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException ie) {
                    System.err.println("Retry delay interrupted. Exiting.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    /**
     * Returns the active database connection.
     * If the connection is closed or null, it attempts to reconnect.
     *
     * @return an active database connection
     */
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                initializeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Error checking connection status: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Closes the database connection.
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("Error closing the connection: " + e.getMessage());
        }
    }
}

