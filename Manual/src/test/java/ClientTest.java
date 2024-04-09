import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientTest {
    private Address addressA = new Address("street A");
    private Address addressB = new Address("street B");
    Client client = new Client();

    @BeforeEach
    public void init() {
        client = new Client();
    }

    @Test
    void afterCreationShouldHaveNoAddress() {
        assertEquals(0, client.getAddresses().size());
    }

    @Test
    void shouldAllowToAddAddress() {
        client.addAddress(addressA);
        assertEquals(1, client.getAddresses().size());
        assertTrue(client.getAddresses().contains(addressA));
    }

    @Test
    void shouldAllowToAddManyAddress() {
        client.addAddress(addressA);
        client.addAddress(addressB);
        assertEquals(2, client.getAddresses().size());
        assertTrue(client.getAddresses().contains(addressA));
        assertTrue(client.getAddresses().contains(addressB));
    }
}
