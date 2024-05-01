package com.manning.junitbook.spring.beans;

import com.manning.junitbook.spring.model.Country;
import com.manning.junitbook.spring.model.Flight;
import com.manning.junitbook.spring.model.Passenger;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FlightBuilder {

    private Map<String, Country> countriesMap = new HashMap<>();

    public FlightBuilder() {
        try(BufferedReader reader = new BufferedReader(new FileReader(".\\src\\main\\resources\\countries_information.csv"))){
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] countriesString = line.toString().split(";");
                    String trim = countriesString[1].trim();
                    Country country = new Country(countriesString[0].trim(), trim);
                    countriesMap.put(trim, country);
                }
            } while (line != null) ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    Map<String, Country> getCountriesMap() {
        return Collections.unmodifiableMap(countriesMap);
    }

    @Bean
    public Flight buildFlightFromCsv() throws IOException {
        Flight flight = new Flight("AA1234", 30);
        try(var reader = new BufferedReader(new FileReader(".\\src\\main\\resources\\flights_information.csv"))) {
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] passengerString = line.toString().split(";");
                    Passenger passenger = new Passenger(passengerString[0].trim());
                    passenger.setCountry(
                            countriesMap.get(passengerString[1].trim())
                    );
                    passenger.setIsRegistered(false);
                    flight.addPassenger(passenger);
                }
            } while (line != null);
        }
        return flight;
    }
}
