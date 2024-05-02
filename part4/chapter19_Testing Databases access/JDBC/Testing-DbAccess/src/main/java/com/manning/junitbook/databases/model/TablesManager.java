package com.manning.junitbook.databases.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.manning.junitbook.databases.model.ConnectionManager.closeConnection;
import static com.manning.junitbook.databases.model.ConnectionManager.openConnection;

public class TablesManager {
    public static void createTable() {
        String sql = "CREATE TABLE COUNTRY( ID IDENTITY, NAME VARCHAR(255), CODE_NAME VARCHAR(255));";
        executeStatement(sql);
    }

    private static void executeStatement(String sql) {
        PreparedStatement statement ;
        Connection connection = openConnection();
        try {
            statement = openConnection().prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }

    }

    public static void dropTable() {
        String sql = "DROP TABLE IF EXISTS COUNTRY;";
        executeStatement(sql);
    }
}
