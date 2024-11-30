package com.napier.semCW;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;



public class AppIntegrationTest
{

    @Test
    void testGetPopulation()
    {
        ArrayList<Country> countries = new ArrayList<>();
        Country count = new Country();
        count.Name = "China";
        count.Continent = "Asia";
        count.Region = "East Asia";
        count.Population = 1277558000;
        countries.add(count);
        for (Country c : countries){
            System.out.println(c.Name + c.Continent + c.Region+ c.Population);
        }
    }

}