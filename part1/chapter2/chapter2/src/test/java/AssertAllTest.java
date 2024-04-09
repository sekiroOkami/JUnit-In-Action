import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertAllTest {

    @Test
    @DisplayName(
            "SUT should default to not being under current verification"
    )
    void testSystemNotVerified() {
        SUT systemUnderTest = new SUT("Our system under test");
        assertAll(
                "By default, SUT is not under current verification",
                () -> assertEquals("Our system under test",
                        systemUnderTest.getSystemName()),
                () -> assertFalse(systemUnderTest.isVerified())
        );
    }
}
