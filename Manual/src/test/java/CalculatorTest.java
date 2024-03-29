import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    public static void main(String[] args) {
        var calculator = new Calculator();
        double result = calculator.add(5, 50);
        if (result != 60) {
            System.out.println("Bad result: " + result);
        }
    }
}