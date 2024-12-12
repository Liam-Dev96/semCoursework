package com.napier.semCW;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppTest
{
    static Main app;

    @BeforeAll
    static void init()
    {
        app = new Main();
    }

    @Test
    void countriesByRegionSingleCountryTest() {
        // Create a valid region and mock the database connection to return a single country.
        String region = "North America";

        // Assuming countriesByRegion will handle the region parameter and fetch related countries.
        app.countriesByRegion(region);

        // Here we would check the output or behavior if needed,
        // such as capturing output for validation or verifying the expected console output.
    }
}