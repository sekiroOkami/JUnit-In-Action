package com.manning.junitbook.databases.dao;

import com.manning.junitbook.databases.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.manning.junitbook.databases.model.ConnectionManager.closeConnection;
import static com.manning.junitbook.databases.model.ConnectionManager.openConnection;

public class CountryDao {
    private static final String GET_ALL_COUNTRIES_SQl =
            "select * from country";
    private static final String GET_COUNTRIES_BY_NAME_SQL =
            "select * from country where name like ?";
    public List<Country> getCountryLIst() {
        List<Country> countryList = new ArrayList<>();
        try {
            Connection connection = openConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL_COUNTRIES_SQl);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                countryList.add(new Country(resultSet.getString(2), resultSet.getString(3)));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return countryList;
    }

    public List<Country> getCountryListStartWith(String name) {
        List<Country> countryList = new ArrayList<>();
        try {
            var connection = openConnection();
            PreparedStatement statement = connection.prepareStatement(GET_COUNTRIES_BY_NAME_SQL);
            // parameters are numbered starting from 1, index of parameter in SQL query
            // '%' is used as a wildcard in SQL queries to match any sequence of characters
            statement.setString(1, name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                countryList.add(new Country(resultSet.getString(2),
                        resultSet.getString(3)));
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return countryList;
    }
}
