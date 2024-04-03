import org.junit.jupiter.api.*;

public class SUTTest {

    @BeforeAll
    static void initAll(){}

    @BeforeEach
    void init(){}

    @Test
    void succeedingTest(){}

    @Test
    void failingTest(){}

    @Test
    @Disabled("for demonstration purpose")
    void skippedTest(){}

    @Test
    void abortedTest(){}

    @AfterEach
    void tearDown(){}

    @AfterAll
    void tearDownAll(){}


}
