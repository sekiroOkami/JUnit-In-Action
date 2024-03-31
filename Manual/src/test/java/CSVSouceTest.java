import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CSVSouceTest {

    @ParameterizedTest
    @CsvSource({
            "apple, 1",
            "banana, 2",
            "'lemon, lime', 0xF1",
            "strawberry, 700_000"
    })
    void testWithCsvSource(String fruit, int rank) {
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }
}
