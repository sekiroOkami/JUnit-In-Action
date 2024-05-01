package com.manning.junitbook.spring.model;

public class PassengerNotFoundException extends RuntimeException{
    public PassengerNotFoundException(Long id) {
        super("Passenger id not found: " + id);
    }
}
