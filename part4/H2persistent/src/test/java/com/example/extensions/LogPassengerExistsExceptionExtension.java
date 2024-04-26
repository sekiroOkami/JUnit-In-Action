package com.example.extensions;

import com.example.jdbc.PassengerExitsException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.util.logging.Logger;

// Exception handling
public class LogPassengerExistsExceptionExtension implements TestExecutionExceptionHandler {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        if (throwable instanceof PassengerExitsException) {
            logger.severe("Passenger exists: " + throwable.getMessage());
            return;
        }
        throw throwable;
    }
}
