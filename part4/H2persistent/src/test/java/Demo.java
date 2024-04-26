import com.example.extensions.ExecutionContextExtension;
import com.example.extensions.LogArithmeticExceptionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ExecutionContextExtension.class,
LogArithmeticExceptionHandler.class})
public class Demo {
    @Test
    void test() {
        String test = "Hello";
        assertNotNull(test);
    }

    @Test
    void testArithMeticException() {
        assertThrows(
                ArithmeticException.class,
                () -> {
                    int result = 1 /0;
                }
        );
    }
}
