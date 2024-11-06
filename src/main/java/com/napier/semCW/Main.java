package com.napier.semCW;

import java.sql.*;
import java.util.concurrent.TimeUnit;

public class Main {

    /**
     * Connection to MySQL database.
     */
    public Connection con;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 100;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(6000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

public void getCounCon() throws SQLException {
        Statement countrycon = con.createStatement();
        String ccltsQuery = "SELECT * " +
                            "FROM country";
        ResultSet rsetcclts = countrycon.executeQuery(ccltsQuery);
        if (rsetcclts.next()){
            System.out.println(rsetcclts.getString("continent"));
        }
}



    public static void main(String[] args) throws InterruptedException, SQLException {
        // Create new Application
        Main a = new Main();

        // Connect to database
        a.connect();
        TimeUnit.SECONDS.sleep(3);
        a.getCounCon();
        // Disconnect from database
        a.disconnect();


    }
}