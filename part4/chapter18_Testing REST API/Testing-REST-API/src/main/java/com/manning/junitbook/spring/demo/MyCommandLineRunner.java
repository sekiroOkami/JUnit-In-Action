package com.manning.junitbook.spring.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Executing som initialization logic...");
        for (String arg: args) {
            System.out.println("Argument: " + arg);
        }
    }
}
