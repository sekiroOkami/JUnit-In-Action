package com.manning.junitbook.springboot.registration.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppPublishDemo {
    public static void main(String[] args) {
        // Create context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get the UserRegistrationService bean
        var registrationService = context.getBean(UserRegistrationService.class);

        // Register a new user
        String user = "john_doe";
        registrationService.registerUser(user);

        context.close();
    }
}
