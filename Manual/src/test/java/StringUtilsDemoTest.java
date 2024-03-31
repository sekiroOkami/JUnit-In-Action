import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsDemoTest {
    StringUtilsDemo stringUtilsDemo;
    @BeforeEach
    void init() {
        stringUtilsDemo = new StringUtilsDemo();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testPossibleInputParameters(String word) {
        assertThrows(
                IllegalArgumentException.class,
                ()-> StringUtilsDemo.reverse(word)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"hello"})
    void testReverseMethod(String word) {
        assertEquals("olleh", StringUtilsDemo.reverse(word));
    }
    static Stream<Arguments> getWord() {
        return Stream.of(
                Arguments.arguments("hello")
        );
    }

}