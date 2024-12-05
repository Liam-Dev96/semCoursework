package com.napier.semCW;
import org.junit.jupiter.api.*;
import java.sql.*;

public class CityReportsUnitTest {
    private static Connection connection;
    private static CityReports cityReports;

    @BeforeAll
    static void setup() {
        try {
            // Set up the test database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "example");
            cityReports = new CityReports();
        } catch (Exception e) {
            Assertions.fail("Failed to connect to the test database: " + e.getMessage());
        }
    }

    @AfterAll
    static void teardown() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }

    @Test
    void testReportAllCitiesByPopulation() {
        try {
            cityReports.reportAllCitiesByPopulation(connection);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportCitiesByContinent() {
        try {
            cityReports.reportCitiesByContinent(connection, "Europe");
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportCitiesByRegion() {
        try {
            cityReports.reportCitiesByRegion(connection, "Western Europe");
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportCitiesByCountry() {
        try {
            cityReports.reportCitiesByCountry(connection, "USA");
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportCitiesByDistrict() {
        try {
            cityReports.reportCitiesByDistrict(connection, "California");
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportTopNCitiesByPopulation() {
        try {
            cityReports.reportTopNCitiesByPopulation(connection, 10);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportTopNCitiesByContinent() {
        try {
            cityReports.reportTopNCitiesByContinent(connection, "Asia", 5);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportTopNCitiesByRegion() {
        try {
            cityReports.reportTopNCitiesByRegion(connection, "Southeast Asia", 5);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportTopNCitiesByCountry() {
        try {
            cityReports.reportTopNCitiesByCountry(connection, "USA", 10);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportTopNCitiesByDistrict() {
        try {
            cityReports.reportTopNCitiesByDistrict(connection, "Texas", 5);
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testReportCity() {
        try {
            cityReports.reportCity(connection, "New York");
        } catch (Exception e) {
            Assertions.fail("Method threw an exception: " + e.getMessage());
        }
    }
}
