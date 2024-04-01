import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NumberExtractor {


    public static List<Integer> extractNumber(String... input) {
        Objects.requireNonNull(input);
        if (input.length == 0) throw new IllegalArgumentException(
                "illegal argument: [" + input + "]"
        );

        List<Integer> result = new ArrayList<>(0);
        return result;
    }
}
