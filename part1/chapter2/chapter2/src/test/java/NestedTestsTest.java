import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NestedTestsTest {
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";

    @Nested
    class BuilderTest {
        private String MIDDLE_NAME = "Michael";

        @Test
        void customerBuilder() throws ParseException {
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat("yyyy-MM-dd");
            Date customerDate = simpleDateFormat.parse("2024-03-28");
            Customer customer = new Customer.Builder(
                    Gender.MALE, FIRST_NAME, LAST_NAME)
                    .withMiddleName(MIDDLE_NAME)
                    .withBecomeCustomer(customerDate)
                    .build();

        }
    }
}
