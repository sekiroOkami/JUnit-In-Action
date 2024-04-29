package com.manning.junitbook.springboot.registration;


import com.manning.junitbook.springboot.model.Passenger;
import org.springframework.context.ApplicationEvent;

public class PassengerRegistrationEvent extends ApplicationEvent {
    private Passenger passenger;

    public PassengerRegistrationEvent(Passenger passenger) {
        super(passenger);
        this.passenger = passenger;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
