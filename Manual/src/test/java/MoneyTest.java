import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {
    @Test
    void constructorShouldSetAmountAndCurrency() {
        Money money = new Money(10, "USD");
        assertEquals(10, money.getAmount()  );
        assertEquals("USD", money.getCurrency());
    }

    static Stream<Arguments> getMoney() {
        return Stream.of(
                Arguments.of(10, "USD"),
                Arguments.of(20, "EUR")
        );
    }

    @ParameterizedTest
    @MethodSource("getMoney")
    void constructorShouldSetAmountAndCurrency(int amount, String currency) {
        var money = new Money(amount, currency);

        assertEquals(amount, money.getAmount());
        assertEquals(currency, money.getCurrency());
    }




}
