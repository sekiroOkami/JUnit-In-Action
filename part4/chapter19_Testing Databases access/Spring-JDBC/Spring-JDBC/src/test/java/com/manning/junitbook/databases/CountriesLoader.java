package com.manning.junitbook.databases;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.Arrays;


// JdbcDaoSupport is a Spring JDBC that facilitates configuring and transferring database parameters.
public class CountriesLoader extends JdbcDaoSupport {

    private static final String LOAD_COUNTRIES_SQL =
            "insert into country (name, code_name) values";
    public static final String[][] COUNTRY_INIT_DATA = {
            { "Australia", "AU"}, { "Canada", "CA" }, { "France", "FR" },
            { "Germany", "DE" }, { "Italy", "IT" }, { "Japan", "JP" },
            { "Romania", "RO" },{ "Russian Federation", "RU" },
            { "Spain", "ES" }, { "Switzerland", "CH" },
            { "United Kingdom", "UK" }, { "United States", "US" } };

    public void loadCountries() {
        Arrays.stream(COUNTRY_INIT_DATA)
                .forEach(strings -> {
                    String sql = LOAD_COUNTRIES_SQL + "('" + strings[0] +
                            "','" + strings[1] + "');";
                    getJdbcTemplate().execute(sql);
                });
    }
}
