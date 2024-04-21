import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PassengerTest {

    @Test
    void testPassengerCreation() {
        var passenger = new Passenger("123-45-6789",
                "John Smith", "US");
        assertNotNull(passenger);
    }

    @Test
    void testInvalidCountryCode() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    var passenger = new Passenger("900-45-6789",
                            "John Smith", "GJ");
                }
        );
    }
}
