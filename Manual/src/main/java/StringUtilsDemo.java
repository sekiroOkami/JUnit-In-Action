import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class StringUtilsDemo {
    public static boolean isPalinDrome(String word) {
        String temp =  word.replaceAll("\\s+", "").toLowerCase();
        return IntStream.range(0, temp.length() /2 )
                .noneMatch(i->temp.charAt(i) != temp.charAt(temp.length()-i-1));
    }

    public static String reverse(String word) {
        if (word == "" | word == null){
            throw new IllegalArgumentException(
                    "illegal argument: [" + word + "]"
            );
        }
        List<String> tempArray = new ArrayList<>(word.length());
        for (int i = 0; i < word.length(); i++) {
            tempArray.add(word.substring(i, i+1));
        }

        StringBuilder reversedString = new StringBuilder(word.length());
        for (int i = tempArray.size() -1; i >= 0; i--) {
            reversedString.append(tempArray.get(i));
        }
        return reversedString.toString();
    }
}
