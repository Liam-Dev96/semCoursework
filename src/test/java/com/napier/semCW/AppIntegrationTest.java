package com.napier.semCW;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{

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
        System.out.println(countries.size());
    }

}