package com.example.jdbc;

import com.example.Passenger;

public class PassengerExitsException extends Exception {
    private Passenger passenger;

    public PassengerExitsException(Passenger passenger, String message) {
        super(message);
        this.passenger = passenger;
    }
}
