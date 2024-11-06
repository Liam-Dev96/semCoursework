package com.napier.semCW;

import java.sql.*;

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
                Thread.sleep(3000);
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

    public void countriesByRegion(String region) {
        // Check if the region is null or database connection is null
        if (region == null || con == null) {
            System.out.println("Invalid input or database connection not established.");
            return;
        }

        // SQL query to select countries by region, ordered by population
        String query = "SELECT name, population FROM country WHERE region = ? ORDER BY population DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, region);  // Set the region parameter
            ResultSet rs = stmt.executeQuery();

            // Print header
            System.out.println(String.format("%-25s %-15s", "Country Name", "Population"));
            System.out.println("--------------------------------------------------");

            // Loop over all countries in the result set
            while (rs.next()) {
                String countryName = rs.getString("name");
                Integer population = rs.getInt("population");

                // If countryName or population is null, skip this entry
                if (countryName == null || population == null) {
                    System.out.println("Incomplete data for a country, skipping entry.");
                    continue;
                }

                // Format and print each row
                String countryString = String.format("%-25s %-15d", countryName, population);
                System.out.println(countryString);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query.");
            e.printStackTrace();
        }
    }

//    public void countriesByRegion(String region) {
//        // Check if the region is null
//        if (region == null || con == null) {
//            System.out.println("Invalid input or database connection not established.");
//            return;
//        }
//
//        // SQL query to select countries by region, ordered by population
//        String query = "SELECT name, population FROM country WHERE region = ? ORDER BY population DESC";
//
//        try (PreparedStatement stmt = con.prepareStatement(query)) {
//            stmt.setString(1, region);  // Set the region parameter
//            ResultSet rs = stmt.executeQuery();
//
//            // Print header
//            System.out.println(String.format("%-25s %-15s", "Country Name", "Population"));
//            System.out.println("--------------------------------------------------");
//
//            // Loop over all countries in the result set
//            while (rs.next()) {
//                String countryName = rs.getString("name");
//                int population = rs.getInt("population");
//
//                // Format and print each row
//                String countryString = String.format("%-25s %-15d", countryName, population);
//                System.out.println(countryString);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error executing query.");
//            e.printStackTrace();
//        }
//    }

//    public void CountriesByRegion(String region) {
//        String query = "SELECT name, population FROM country WHERE region = ? ORDER BY population DESC";
//        try (PreparedStatement stmt = con.prepareStatement(query)) {
//            stmt.setString(1, region);  // Set the region parameter
//            ResultSet rs = stmt.executeQuery();
//
//            System.out.println("Countries in region: " + region);
//            while (rs.next()) {
//                String countryName = rs.getString("name");
//                int population = rs.getInt("population");
//                System.out.println("Country: " + countryName + ", Population: " + population);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error executing query.");
//            e.printStackTrace();
//        }
//    }



    public static void main(String[] args) {
        // Create new Application
        Main a = new Main();

        // Connect to database
        a.connect();
        // Disconnect from database
        a.disconnect();


    }
}