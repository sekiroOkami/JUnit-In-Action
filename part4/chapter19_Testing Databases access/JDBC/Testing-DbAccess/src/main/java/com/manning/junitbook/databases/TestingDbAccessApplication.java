package com.manning.junitbook.databases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.manning.junitbook.databases.model.ConnectionManager.openConnection;

@SpringBootApplication
public class TestingDbAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingDbAccessApplication.class, args);
		var c = openConnection();
	}

}
