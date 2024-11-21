package com.napier.semCW;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static Main app;

    @BeforeAll
    static void init()
    {
        app = new Main();
        app.connect("localhost:33060", 30000);

    }

    @Test
    void testGetPopulation()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country count = new Country();
        count.Name = "China";
        count.Continent = "Asia";
        count.Region = "East Asia";
        count.Population = 1277558000;
        countries.add(count);
        app.PrintCountry(countries);
    }

}