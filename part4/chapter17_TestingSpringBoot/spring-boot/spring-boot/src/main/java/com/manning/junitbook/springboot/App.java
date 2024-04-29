package com.manning.junitbook.springboot;

import com.manning.junitbook.springboot.model.Passenger;
import com.manning.junitbook.springboot.registration.PassengerRegistrationEvent;
import com.manning.junitbook.springboot.registration.RegistrationManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
       ConfigurableApplicationContext context =  SpringApplication.run(App.class, args);
       var registrationManager = context.getBean(RegistrationManager.class);

       var passenger = new Passenger("Sekiro");
        PassengerRegistrationEvent event = new PassengerRegistrationEvent(passenger);
        registrationManager.getApplicationContext().publishEvent(event);
    }
}
