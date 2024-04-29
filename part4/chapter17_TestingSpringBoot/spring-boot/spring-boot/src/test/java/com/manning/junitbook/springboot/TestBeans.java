package com.manning.junitbook.springboot;

import com.manning.junitbook.springboot.model.Country;
import com.manning.junitbook.springboot.model.Passenger;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration // used to define additional beans or cutomizations for the test
public class TestBeans {

    // create and configure a Passenger bean that will be injected into the tests
    @Bean
    Passenger createPassenger() {
        var passenger = new Passenger("Sekiro");
        passenger.setCountry(createCountry());
        passenger.setIsRegistered(false);
        return passenger;
    }

    // create and configure a Country bean that will be injected into the tests
    @Bean
    public Country createCountry() {
        var country = new Country("Japan", "JP");
        return country;
    }
}
