import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "classpath:application-context.xml"
                );
        Passenger passenger = (Passenger) context.getBean("passenger");
        Country country = (Country) context.getBean("country");

        System.out.println(passenger);
        System.out.println(country);
    }

}
