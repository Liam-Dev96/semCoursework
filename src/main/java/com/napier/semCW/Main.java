package com.napier.semCW;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    /**
     * Connection to MySQL database.
     */
    public Connection con;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

                // Let's wait before attempting to reconnect
                shouldWait = true;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
                System.out.println("connection closed successfully");
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

public ArrayList<Country> getCounCon(){
        try {
            Statement countrycon = con.createStatement();
            String ccltsQuery = "SELECT Name, Continent, Region, Population FROM country a order by a.Population DESC";
            ResultSet rsetcclts = countrycon.executeQuery(ccltsQuery);
            ArrayList<Country> countries = new ArrayList<>();
            while (rsetcclts.next()) {
                Country country = new Country();
                country.Name = rsetcclts.getString("Name");
                country.Continent = rsetcclts.getString("Continent");
                country.Region = rsetcclts.getString("Region");
                country.Population = rsetcclts.getInt("Population");
                countries.add(country);
            }
            return countries.isEmpty() ? null:countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("failed to get country details");
            return null;
        }
}

public void PrintCountry(ArrayList<Country> countries){
    //runs an output to show all countries and continents sorted by population large to small
    if (countries != null) {
        for (Country coun : countries) {
            System.out.println( "--------------------------------------------------------------------------\n" +
                    "Country Name: "+coun.Name + "\n"+
                    "Continent: "+ coun.Continent + "\n"+
                    "Region: "+ coun.Region + "\n"+
                    "Population: "+ coun.Population +
                    "\n--------------------------------------------------------------------------"
            );
        }
    } else {
        System.out.println("No countries found or there was an error.");
    }

}

    public static void main(String[] args) {
        // Create new Application
        Main a = new Main();

        // Connect to database
        if (args.length < 1) {
            a.connect("localhost:33060", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }
        ArrayList<Country> countries = a.getCounCon();  // Now it's a list of countries
        System.out.println(countries.size());


        // Disconnect from database
        a.disconnect();


    }
}