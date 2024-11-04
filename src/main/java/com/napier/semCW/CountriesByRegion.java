package com.napier.semCW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesByRegion {

    private Connection con;

    // Method to connect to the MySQL database
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
            System.out.println("Successfully connected to MySQL database.");
        } catch (Exception e) {
            System.out.println("Could not connect to the database.");
            e.printStackTrace();
        }
    }

    // Method to retrieve and display countries by region
    public void displayCountriesByRegion(String region) {
        String query = "SELECT name, population FROM country WHERE region = ? ORDER BY population DESC";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, region);  // Set the region parameter
            ResultSet rs = stmt.executeQuery();

            System.out.println("Countries in region: " + region);
            while (rs.next()) {
                String countryName = rs.getString("name");
                int population = rs.getInt("population");
                System.out.println("Country: " + countryName + ", Population: " + population);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query.");
            e.printStackTrace();
        }
    }

    // Method to disconnect from the database
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Disconnected from database.");
            } catch (SQLException e) {
                System.out.println("Error closing connection.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Instantiate the class and connect to the database
        CountriesByRegion countryService = new CountriesByRegion();
        countryService.connect();

        // Display countries by specified region
        countryService.displayCountriesByRegion("Asia");

        // Disconnect from the database
        countryService.disconnect();
    }
}
