package com.napier.semCW;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.fail;

public class CapitalCityReportsUnitTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/world";
    private static final String USER = "root";
    private static final String PASSWORD = "example";

    @Test
    void testReportAllCapitalCitiesByPopulation() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CapitalCityReports reports = new CapitalCityReports();
            reports.reportAllCapitalCitiesByPopulation(connection);
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void testReportCapitalCitiesInContinent() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CapitalCityReports reports = new CapitalCityReports();
            reports.reportCapitalCitiesInContinentByPopulation(connection, "Europe");
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void testReportCapitalCitiesInRegion() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CapitalCityReports reports = new CapitalCityReports();
            reports.reportCapitalCitiesInRegionByPopulation(connection, "Western Europe");
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void testReportTopNCapitalCitiesInWorld() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CapitalCityReports reports = new CapitalCityReports();
            reports.reportTopNCapitalCitiesInWorld(connection, 10);
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void testReportTopNCapitalCitiesInContinent() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CapitalCityReports reports = new CapitalCityReports();
            reports.reportTopNCapitalCitiesInContinent(connection, "Europe", 10);
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void testReportTopNCapitalCitiesInRegion() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CapitalCityReports reports = new CapitalCityReports();
            reports.reportTopNCapitalCitiesInRegion(connection, "Western Europe", 10);
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void testReportCapitalCityDetails() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            CapitalCityReports reports = new CapitalCityReports();
            reports.reportCapitalCityDetails(connection);
        } catch (Exception e) {
            fail("An exception was thrown: " + e.getMessage());
        }
    }
}
