import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("skipped")
public class NumberExtractorTest {
    private NumberExtractor numberExtractor;
    private static final String ANY_INPUT = "abc123def4567ghi89jkl0";
    @Test
    void testExtractNumberWithThreeOrMoreDigits() {
        // arrange

        // act
        List<Integer> result =  NumberExtractor.extractNumber(ANY_INPUT);

        // assert
        assertTrue(result instanceof List<Integer>, "return type should be List<Integer>");
        assertTrue(result.size() >= 3, "return value should be 3 or more digits" );
    }

    @ParameterizedTest
    @NullSource
    void argumentsShouldNotBeNull(String argument) {
        assertThrows(
                NullPointerException.class,
                () -> NumberExtractor.extractNumber(argument)
        );
    }

    @ParameterizedTest
    @EmptySource
    void argumentsShouldNotBeEmpty(String argument) {
        assertThrows(
                IllegalArgumentException.class,
                () -> NumberExtractor.extractNumber(argument)
        );
    }
}
