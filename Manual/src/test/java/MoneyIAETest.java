import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyIAETest {
    private final static int VALID_AMOUNT = 5;
    private final static String VALID_CURRENCY = "USD";

    static Stream<Arguments> getInvalidAmount() {
        return Stream.of(
                Arguments.arguments(-123),
                Arguments.arguments(-5)
        );
    }

    static Stream<Arguments> getInvalidCurrency() {
        return Stream.of(
                Arguments.arguments((String) null),
                Arguments.arguments("")
        );
    }

    @ParameterizedTest
    @MethodSource("getInvalidAmount")
    void constructorShouldThrowIAEForInvalidAmount(int invalidAmount) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Money(invalidAmount, VALID_CURRENCY)
        );
    }

    @ParameterizedTest
    @MethodSource("getInvalidCurrency")
    void constructorShouldThrowIAEForInvalidCurrency(String invalidCurrency) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Money(VALID_AMOUNT, invalidCurrency)
        );
    }
}
