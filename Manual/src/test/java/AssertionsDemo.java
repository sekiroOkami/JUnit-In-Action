

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionsDemo {
    private final Calculator calculator = new Calculator();

    private final Person person = new Person("Jane", "Doe");

    @Test
    void standardAssertions() {
        Assertions.assertEquals(3, calculator.add(1,2) );
        Assertions.assertEquals(4, calculator.multiply(2,2)
        ,"The Optional failure message is now the last parameter");
        assertTrue('a' < 'b',
                () -> "Assertion messages can be lazily evaluated -- "
        + "to avoid constructing complex messages unnecessarily");

    }

    @Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        assertAll("This is heading person",
                () -> Assertions.assertEquals("Jane", person.getFirstName()),
                () -> Assertions.assertEquals("Doe", person.getLastName())
        );
    }

    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.

        assertAll("properties",
                () -> {
                    String firstName = person.getFirstName();
                    assertNotNull(firstName);

                    //executed only if the previous assertion is valid.
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("J")),
                            () -> assertTrue(firstName.endsWith("e"))
                    );

                },
                () -> {
                    // Grouped assertion, so processed independently
                    // of results of first name assertions.
                    String lastName = person.getLastName();
                    assertNotNull(lastName);

                    //executed only if the previous assertion is valid.
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("D")),
                            () -> assertTrue(lastName.endsWith("e"))
                    );

                }
        );
    }

    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(1,0)
        );
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        assertTimeout(Duration.ofMinutes(2),
                () -> {
                    // Perform task that takes less than 2 minutes.
                }
        );
    }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actual = assertTimeout(Duration.ofMinutes(2),
                () -> {
                    return "a result";
                });
        assertEquals("a result", actual);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        String actual = assertTimeout(Duration.ofMinutes(2),
                AssertionsDemo::greeting);
        assertEquals("Hello, World!", actual);

    }

    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(Duration.ofMillis(10),
                () -> {
                    // Simulate task that takes more that 10ms.
                    Thread.sleep(100);
                });
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            new CountDownLatch(1).await();
        });
    }
    private static String greeting() {
        return "Hello, World!";
    }
}
