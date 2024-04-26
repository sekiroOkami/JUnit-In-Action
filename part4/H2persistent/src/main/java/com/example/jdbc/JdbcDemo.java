package com.example.jdbc;

import java.sql.SQLException;

public class JdbcDemo {
    public static void main(String[] args) {
        try (var con = ConnectionManager.openConnection()){
            System.out.println("Connected to H2 successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
