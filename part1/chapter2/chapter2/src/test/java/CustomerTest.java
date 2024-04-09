import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Tag("individual")
public class CustomerTest {
    private final String FIRST_NAME = "JOHN";
    private final String LAST_NAME = "DOE";


    @Test
    void testCustomer() {
        var customer = new Customer.Builder(Gender.MALE,FIRST_NAME,LAST_NAME).build();

        assertEquals("JOHN", customer.getFirstName());
    }

}
