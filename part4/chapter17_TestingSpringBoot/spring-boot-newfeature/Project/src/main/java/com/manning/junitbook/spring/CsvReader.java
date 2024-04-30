package com.manning.junitbook.spring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CsvReader {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(".\\src\\test\\resources\\flights_information.csv"))) {
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] passengerString = line.toString().split(";");
                    System.out.println( passengerString[0] + passengerString[1]);
                }
            } while(line != null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
