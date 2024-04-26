package com.chapter14;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ExecutionContextExtension.class, DatabaseOperationsExtension.class})
class PassengerTest {
    private PassengerDao passengerDao;

    public PassengerTest(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @Test
    void testPassenger() {
        var passenger = new Passenger("123-456-789", "Ja");
        assertEquals("Passenger John Smith with identifier: 123-456-789",
        passenger.toString());
    }
}