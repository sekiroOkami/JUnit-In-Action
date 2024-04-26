
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleAppTest {
    private static final String APPLICATION_CONTEXT_XML_FILE_NAME =
            "classpath:application-context.xml";
    private ClassPathXmlApplicationContext context;
    private Passenger expectedPassenger;

    @BeforeEach
    public void setUp() {
        context = new ClassPathXmlApplicationContext(
                APPLICATION_CONTEXT_XML_FILE_NAME);
        expectedPassenger = PassengerUtil.getExpectedPassenger();
    }

    @Test
    public void testInitPassenger() {
        Passenger passenger = (Passenger) context.getBean("passenger");
        assertEquals(expectedPassenger, passenger);
        System.out.println(passenger);
    }
}
