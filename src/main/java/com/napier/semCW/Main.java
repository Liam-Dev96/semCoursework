package com.napier.semCW;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

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

public void PrintCountry(ArrayList<Country> countries, String filename){


   StringBuilder sb = new StringBuilder();

   sb.append("| Country Name | Continent | Region | Population |");
   sb.append("| --- | --- | --- | --- |");
    //runs an output to show all countries and continents sorted by population large to small
    for (Country coun : countries) {
        if (coun == null) continue;
        sb.append("| " + coun.Name + " |" +
                "| " + coun.Continent + " |" +
                "| " + coun.Region + " |" +
                "| " + coun.Population + " |");
    }
    try {
        new File("./reports/").mkdir();
        BufferedWriter writer = new BufferedWriter(new FileWriter("./reports/" + filename));
        writer.write(sb.toString());
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
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
        a.PrintCountry(countries, "countries.md");


        // Disconnect from database
        a.disconnect();


    }
}