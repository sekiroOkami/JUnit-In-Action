package com.manning.junitbook.spring.beans;

import com.manning.junitbook.spring.model.Flight;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootTest()
@Import(FlightBuilder.class)
public class FlightBuilderDemo {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FlightBuilderDemo.class,args);
        Flight flight = context.getBean(Flight.class);
        System.out.println(flight);
    }
}
