package com.napier.semCW;
import java.sql.*;

public class CapitalCityReports {
    /// Report 1: All capital cities in the world organized by largest to smallest population
    public void reportAllCapitalCitiesByPopulation(Connection connection) {
        String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-30s %-30s %-15s%n", "Capital City", "Country", "Population");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                String cityName = rs.getString("Name");
                String countryName = rs.getString("Country");
                int population = rs.getInt("Population");
                System.out.printf("%-30s %-30s %-15d%n", cityName, countryName, population);
            }
            System.out.println("--------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }


    /// Report 2: All capital cities in a continent organized by largest to smallest population
    public void reportCapitalCitiesInContinentByPopulation(Connection connection, String continent) {
        String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, continent);
            ResultSet rs = stmt.executeQuery();

            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-30s %-30s %-15s%n", "Capital City", "Country", "Population");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                String cityName = rs.getString("Name");
                String countryName = rs.getString("Country");
                int population = rs.getInt("Population");
                System.out.printf("%-30s %-30s %-15d%n", cityName, countryName, population);
            }
            System.out.println("--------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }

    /// Report 3: All capital cities in a region organized by largest to smallest population
    public void reportCapitalCitiesInRegionByPopulation(Connection connection, String region) {
        String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, region);
            ResultSet rs = stmt.executeQuery();

            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-30s %-30s %-15s%n", "Capital City", "Country", "Population");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                String cityName = rs.getString("Name");
                String countryName = rs.getString("Country");
                int population = rs.getInt("Population");
                System.out.printf("%-30s %-30s %-15d%n", cityName, countryName, population);
            }
            System.out.println("--------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }

    /// Report 4: The top N populated capital cities in the world where N is provided by the user
    public void reportTopNCapitalCitiesInWorld(Connection connection, int n) {
        String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC " +
                "LIMIT ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, n);
            ResultSet rs = stmt.executeQuery();

            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-30s %-30s %-15s%n", "Capital City", "Country", "Population");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                String cityName = rs.getString("Name");
                String countryName = rs.getString("Country");
                int population = rs.getInt("Population");
                System.out.printf("%-30s %-30s %-15d%n", cityName, countryName, population);
            }
            System.out.println("--------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }

    /// Report 5: The top N populated capital cities in a continent where N is provided by the user
    public void reportTopNCapitalCitiesInContinent(Connection connection, String continent, int n) {
        String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC " +
                "LIMIT ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, continent);
            stmt.setInt(2, n);
            ResultSet rs = stmt.executeQuery();

            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-30s %-30s %-15s%n", "Capital City", "Country", "Population");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                String cityName = rs.getString("Name");
                String countryName = rs.getString("Country");
                int population = rs.getInt("Population");
                System.out.printf("%-30s %-30s %-15d%n", cityName, countryName, population);
            }
            System.out.println("--------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }

    /// Report 6: The top N populated capital cities in a region where N is provided by the user
    public void reportTopNCapitalCitiesInRegion(Connection connection, String region, int n) {
        String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC " +
                "LIMIT ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, region);
            stmt.setInt(2, n);
            ResultSet rs = stmt.executeQuery();

            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-30s %-30s %-15s%n", "Capital City", "Country", "Population");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                String cityName = rs.getString("Name");
                String countryName = rs.getString("Country");
                int population = rs.getInt("Population");
                System.out.printf("%-30s %-30s %-15d%n", cityName, countryName, population);
            }
            System.out.println("--------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }

    /// Report 7: Capital City Report - Name, Country, Population
    public void reportCapitalCityDetails(Connection connection) {
        String query = "SELECT city.Name, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Name ASC";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-30s %-30s %-15s%n", "Capital City", "Country", "Population");
            System.out.println("--------------------------------------------------------------------------------");

            while (rs.next()) {
                String cityName = rs.getString("Name");
                String countryName = rs.getString("Country");
                int population = rs.getInt("Population");
                System.out.printf("%-30s %-30s %-15d%n", cityName, countryName, population);
            }
            System.out.println("--------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Error generating report: " + e.getMessage());
        }
    }
}
