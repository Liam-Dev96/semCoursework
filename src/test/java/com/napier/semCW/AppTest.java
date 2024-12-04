package com.napier.semCW;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    static Main app;

    @BeforeAll
    static void init(){
        app = new Main();
        app.connect("localhost:33060", 30000);
    }

    @Test
    void testGetCountry(){
        Country country = app.getCountry("ABW");
        assertEquals(country.Code, "ABW");
        assertEquals(country.Name, "Aruba");
        assertEquals(country.Region, "Caribbean");
        assertEquals(country.Continent, "North America");
        assertEquals(country.Population, 103000);
    }

    @Test
    public void testAlwaysPasses() {
        // This test will always pass since it's checking a simple true condition
        assertTrue(true, "This condition is guaranteed to be true");
    }
}
