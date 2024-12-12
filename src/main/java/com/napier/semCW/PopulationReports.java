package com.napier.semCW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PopulationReports {

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

    // Method to retrieve population report for a continent
    public void displayPopulationReportByContinent(String continent) {
        String totalPopulationQuery = "SELECT SUM(Population) AS total_population FROM country WHERE Continent = ?";
        String cityPopulationQuery = "SELECT SUM(city.Population) AS city_population FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Continent = ?";

        try (PreparedStatement totalStmt = con.prepareStatement(totalPopulationQuery);
             PreparedStatement cityStmt = con.prepareStatement(cityPopulationQuery)) {

            // Set the continent parameter
            totalStmt.setString(1, continent);
            cityStmt.setString(1, continent);

            // Execute queries to get total population and city population
            ResultSet totalRs = totalStmt.executeQuery();
            ResultSet cityRs = cityStmt.executeQuery();

            if (totalRs.next() && cityRs.next()) {
                long totalPopulation = totalRs.getLong("total_population");
                long cityPopulation = cityRs.getLong("city_population");
                long nonCityPopulation = totalPopulation - cityPopulation;

                double cityPopulationPercentage = (double) cityPopulation / totalPopulation * 100;
                double nonCityPopulationPercentage = (double) nonCityPopulation / totalPopulation * 100;

                // Display the report
                System.out.println("Population Report for Continent: " + continent);
                System.out.println("Total Population: " + totalPopulation);
                System.out.println("City Population: " + cityPopulation + " (" + String.format("%.2f", cityPopulationPercentage) + "%)");
                System.out.println("Non-City Population: " + nonCityPopulation + " (" + String.format("%.2f", nonCityPopulationPercentage) + "%)");
            } else {
                System.out.println("No data found for continent: " + continent);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query.");
            e.printStackTrace();
        }
    }

    // Method to retrieve population report for a region
    public void displayPopulationReportByRegion(String region) {
        String totalPopulationQuery = "SELECT SUM(Population) AS total_population FROM country WHERE Region = ?";
        String cityPopulationQuery = "SELECT SUM(city.Population) AS city_population FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Region = ?";

        try (PreparedStatement totalStmt = con.prepareStatement(totalPopulationQuery);
             PreparedStatement cityStmt = con.prepareStatement(cityPopulationQuery)) {

            // Set the region parameter
            totalStmt.setString(1, region);
            cityStmt.setString(1, region);

            // Execute queries to get total population and city population
            ResultSet totalRs = totalStmt.executeQuery();
            ResultSet cityRs = cityStmt.executeQuery();

            if (totalRs.next() && cityRs.next()) {
                long totalPopulation = totalRs.getLong("total_population");
                long cityPopulation = cityRs.getLong("city_population");
                long nonCityPopulation = totalPopulation - cityPopulation;

                double cityPopulationPercentage = (double) cityPopulation / totalPopulation * 100;
                double nonCityPopulationPercentage = (double) nonCityPopulation / totalPopulation * 100;

                // Display the report
                System.out.println("Population Report for Region: " + region);
                System.out.println("Total Population: " + totalPopulation);
                System.out.println("City Population: " + cityPopulation + " (" + String.format("%.2f", cityPopulationPercentage) + "%)");
                System.out.println("Non-City Population: " + nonCityPopulation + " (" + String.format("%.2f", nonCityPopulationPercentage) + "%)");
            } else {
                System.out.println("No data found for region: " + region);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query.");
            e.printStackTrace();
        }
    }

    // Method to retrieve population report for a country
    public void displayPopulationReportByCountry(String countryName) {
        String totalPopulationQuery = "SELECT Population AS total_population FROM country WHERE Name = ?";
        String cityPopulationQuery = "SELECT SUM(city.Population) AS city_population FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Name = ?";

        try (PreparedStatement totalStmt = con.prepareStatement(totalPopulationQuery);
             PreparedStatement cityStmt = con.prepareStatement(cityPopulationQuery)) {

            // Set the country parameter
            totalStmt.setString(1, countryName);
            cityStmt.setString(1, countryName);

            // Execute queries to get total population and city population
            ResultSet totalRs = totalStmt.executeQuery();
            ResultSet cityRs = cityStmt.executeQuery();

            if (totalRs.next() && cityRs.next()) {
                long totalPopulation = totalRs.getLong("total_population");
                long cityPopulation = cityRs.getLong("city_population");
                long nonCityPopulation = totalPopulation - cityPopulation;

                double cityPopulationPercentage = (double) cityPopulation / totalPopulation * 100;
                double nonCityPopulationPercentage = (double) nonCityPopulation / totalPopulation * 100;

                // Display the report
                System.out.println("Population Report for Country: " + countryName);
                System.out.println("Total Population: " + totalPopulation);
                System.out.println("City Population: " + cityPopulation + " (" + String.format("%.2f", cityPopulationPercentage) + "%)");
                System.out.println("Non-City Population: " + nonCityPopulation + " (" + String.format("%.2f", nonCityPopulationPercentage) + "%)");
            } else {
                System.out.println("No data found for country: " + countryName);
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
        PopulationReports reportService = new PopulationReports();
        reportService.connect();

        // Display population reports for a specified continent, region, or country
        reportService.displayPopulationReportByContinent("Asia"); // Change as needed
        reportService.displayPopulationReportByRegion("Southeast Asia"); // Change as needed
        reportService.displayPopulationReportByCountry("Japan"); // Change as needed

        // Disconnect from the database
        reportService.disconnect();
    }
}
