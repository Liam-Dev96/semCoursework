package com.napier.semCW;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


public class AppIntegrationTest {
    static Main app;

    @BeforeAll
    static void init(){
        app = new Main();
        app.connect("localhost:33060", 30000);
    }

    @Test
    void testGetCountry(){
        Country country = app.getCountry();
        assertEquals(country.Name, "Aruba");
        assertEquals(country.Region, "Caribbean");
        assertEquals(country.Continent, "North America");
        assertEquals(country.Population, 103000);
    }

    @BeforeEach
    void setUp() {
        main = new Main();
        main.connect();
    }

    @AfterEach
    void tearDown() {
        main.disconnect();
    }

    @Test
    void testConnect() {
        assertNotNull(main.con, "Database connection should be established.");
        try {
            assertFalse(main.con.isClosed(), "Database connection should be open.");
        } catch (SQLException e) {
            fail("An exception occurred while checking the connection status: " + e.getMessage());
        }
    }

    @Test
    void testCountriesByRegion_ValidRegion() {
        // Assuming the "world" database has a region called "South America".
        main.countriesByRegion("South America");

        // You can also use a logger or database assertions to validate the query output.
        // For manual verification, ensure no exceptions are thrown and the expected data is printed.
    }

    @Test
    void testCountriesByRegion_InvalidRegion() {
        main.countriesByRegion("UnknownRegion");

        // Since there's no data for "UnknownRegion", this should not throw an error.
        // Verify it handles the case gracefully.
    }

    @Test
    void testCountriesByRegion_NullRegion() {
        main.countriesByRegion(null);

        // Verify it handles null input gracefully without throwing exceptions.
    }

    @Test
    void testDisconnect() {
        main.disconnect();
        assertNull(main.con, "Database connection should be null after disconnecting.");
    }
}
