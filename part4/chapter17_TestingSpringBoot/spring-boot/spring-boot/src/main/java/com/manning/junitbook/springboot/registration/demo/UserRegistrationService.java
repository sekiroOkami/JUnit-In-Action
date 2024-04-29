package com.manning.junitbook.springboot.registration.demo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Create a component that publishes this event whenever a new user is registered
 */
@Service
public class UserRegistrationService {

    //public the event
    private final ApplicationEventPublisher eventPublisher;

    public UserRegistrationService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void registerUser(String username) {
        eventPublisher.publishEvent(new UserRegisteredEvent(this, username));
    }
}
