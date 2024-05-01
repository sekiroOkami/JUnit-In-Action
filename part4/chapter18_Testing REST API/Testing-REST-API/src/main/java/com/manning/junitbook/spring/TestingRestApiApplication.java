package com.manning.junitbook.spring;

import com.manning.junitbook.spring.beans.FlightBuilder;
import com.manning.junitbook.spring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Map;

@SpringBootApplication
@Import(FlightBuilder.class)
public class TestingRestApiApplication {

	@Autowired
	private Map<String, Country> countriesMap;

	@Autowired
	private Flight flight;

	public static void main(String[] args) {
		SpringApplication.run(TestingRestApiApplication.class, args);
	}

	/*@Bean
	// Spring functional interface, gives access to application arguments as a string array.
	// The created bean browses all the countries in countriesMap and saves them into countryRepository
	// This CommandLineRunner interface is created and its single method is executed just before the run() method
		// from SpringApplication completes
	CommandLineRunner configureRepository(CountryRepository countryRepository) {
		return args -> {
			for (Country country: countriesMap.values()) {
				countryRepository.save(country);
			}
		};
	}*/

	@Bean
	CommandLineRunner configureRepository(
			CountryRepository countryRepository, PassengerRepository passengerRepository
	) {
		return args -> {
			for (Country country:countriesMap.values()) {
				countryRepository.save(country);
			}
			for (Passenger passenger: flight.getPassengers()) {
				passengerRepository.save(passenger);
			}
		};
	}


}
