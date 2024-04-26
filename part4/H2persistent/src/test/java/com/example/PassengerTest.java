package com.example;
import com.example.extensions.DataAccessObjectParameterResolver;
import com.example.extensions.DatabaseOperationsExtension;
import com.example.extensions.ExecutionContextExtension;
import com.example.extensions.LogPassengerExistsExceptionExtension;
import com.example.jdbc.PassengerDao;
import com.example.jdbc.PassengerExistsException;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ExecutionContextExtension.class,
        DatabaseOperationsExtension.class,
        DataAccessObjectParameterResolver.class,
        LogPassengerExistsExceptionExtension.class})
public class PassengerTest {

    private PassengerDao passengerDao ;

    public PassengerTest(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @Test
    void testPassenger() {
        var passenger = new Passenger("123-456-789", "John Smith");
        assertNotNull(passenger);
    }

    @Test
    void testInsertPassenger() throws PassengerExistsException {
        var passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        assertEquals("John Smith", passengerDao.getById("123-456-789").getName());
    }

    @Test
    void testUpdatePassenger() throws PassengerExistsException {
        var passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        passengerDao.update("123-456-789", "Michale Smith");
        assertEquals("Michale Smith", passengerDao.getById("123-456-789").getName());
    }

    @Test
    void testDeletePassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        passengerDao.delete(passenger);
        assertNull(passengerDao.getById("123-456-789"));
    }

    @Test
    void testInsertExistingPassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);
        assertThrows(
                PassengerExistsException.class,
                () -> passengerDao.insert(passenger)
        );
    }
}
