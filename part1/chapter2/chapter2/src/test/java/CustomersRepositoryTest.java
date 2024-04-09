import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("respsitory")
public class CustomersRepositoryTest {
    private String FIRST_NAME = "John";
    private String LAST_NAME = "John";
    private CustomersRepository repository = new CustomersRepository();

    @Test
    void testNonExistence() {
        boolean exists = repository.contains("John");
        assertTrue(exists);
    }

    @Test
    void testCustomerPersistence() {
        repository.persist(new Customer.Builder(Gender.FEMALE,FIRST_NAME,LAST_NAME).build() );
        assertTrue(repository.contains("John"));
    }

}
