package com.manning.junitbook.databases;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.manning.junitbook.databases.model.ConnectionManager.closeConnection;
import static com.manning.junitbook.databases.model.ConnectionManager.openConnection;

public class CountriesLoader {
    private static final String LOAD_COUNTRIES_SQL =
            "insert into country(name, code_name) values";
    public static final String [][] COUNTRY_INIT_DATA = {
            { "Australia", "AU"}, { "Canada", "CA" }, { "France", "FR" },
            { "Germany", "DE" }, { "Italy", "IT" }, { "Japan", "JP" },
            { "Romania", "RO" },{ "Russian Federation", "RU" },
            { "Spain", "ES" }, { "Switzerland", "CH" },
            { "United Kingdom", "UK" }, { "United States", "US" }
    };

    public void loadCountries() {
        for (var countryData: COUNTRY_INIT_DATA) {
            String sql = LOAD_COUNTRIES_SQL + "('" + countryData[0] + "', '" + countryData[1] + "');";

            try {
                var connection  = openConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                closeConnection();
            }
        }
    }
}
