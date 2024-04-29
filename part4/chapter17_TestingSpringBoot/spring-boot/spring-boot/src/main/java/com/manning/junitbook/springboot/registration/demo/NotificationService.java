package com.manning.junitbook.springboot.registration.demo;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        String user = event.getUsername();
        System.out.println("Notification sent to user " + user + ": Welcome.");
    }
}
