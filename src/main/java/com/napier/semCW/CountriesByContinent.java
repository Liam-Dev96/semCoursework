package com.napier.semCW;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CountriesByContinent {
    public String Continent;
    public String Country;
    public int Population;



    public void StatementRunner(){
        Connection con = null;

        // Create an SQL statement
        Statement stmt = con.createStatement();
        // Create string for SQL statement
        String strSelect =
                "SELECT population";
        // Execute SQL statement
        ResultSet rset = stmt.executeQuery(strSelect);
        // Return new employee if valid.
        // Check one is returned
        if (rset.next()) {
            //world population code for getting info from db
            CountriesByContinent councon = new CountriesByContinent();
            councon.Continent = rset.getString("continent");
            councon.Country = rset.getString("country");
            councon.Population = rset.getInt("population");
            return councon;
        } else
            return null;
    }

    @Override
    public String toString() {
        return "CountriesByContinent{" +
                "Continent='" + Continent + '\'' +
                ", Country='" + Country + '\'' +
                ", Population=" + Population +
                '}';
    }
}
