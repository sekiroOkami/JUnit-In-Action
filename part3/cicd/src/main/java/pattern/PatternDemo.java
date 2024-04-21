package pattern;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternDemo {


    public static void main(String[] args) {
        //  define a regex for email format
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // compile the regex expression pattern into a Pattern obj;
        Pattern pattern = Pattern.compile(emailRegex);

        String[] emails =
                {
                        "john@example.com",     // Valid
                        "jane.doe@example",     // Invalid (missing top-level domain)
                        "invalid_email",        // Invalid (no '@' symbol)
                        "user@com"              // Invalid (invalid top-level domain)
                };

        Arrays.stream(emails)
                .forEach(
                        item -> {
                            Matcher matcher = pattern.matcher(item);
                            if (matcher.matches()) {
                                System.out.println(item+ " is a valid email address");
                            } else {
                                System.out.println(item + " is an invalid email address");
                            }
                        }

                );
    }
}
