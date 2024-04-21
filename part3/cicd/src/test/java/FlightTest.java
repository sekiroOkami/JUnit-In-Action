import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Test
    void  testFlightCreation() {
        var flight = new Flight("AA123", 100);
        assertNotNull(flight);
    }

    @Test
    void testInvalidFlightNumber() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    var flight = new Flight("AA12", 123);
                }
        );
    }

}