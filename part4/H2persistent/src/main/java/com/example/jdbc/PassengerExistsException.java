package com.example.jdbc;

import com.example.Passenger;

public class PassengerExistsException extends Exception {
    private Passenger passenger;

    public PassengerExistsException(Passenger passenger, String message) {
        super(message);
        this.passenger = passenger;
    }
}
