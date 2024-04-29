package com.manning.junitbook.springboot.registration.demo;

import org.springframework.context.ApplicationEvent;

/**
 * let'say we have an application where we want to send notifications whenever a new user is registered.
 * We'll define a custom event by 'UserRegisteredEvent
 */
public class UserRegisteredEvent extends ApplicationEvent {
    private final String username;

    public UserRegisteredEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
