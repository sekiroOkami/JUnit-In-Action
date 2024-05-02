package com.manning.junitbook.databases.dao;

import com.manning.junitbook.databases.model.Country;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {

    public static final String NAME = "name";
    public static final String CODE_NAME = "code_name";


    public Country mapRow(ResultSet resultSet, int i) throws SQLException {
        Country country = new Country(resultSet.getString(NAME), resultSet.getString(CODE_NAME));
        return country;
    }

    @Override
    public int[] getRowsForPaths(TreePath[] path) {
        return new int[0];
    }
}
