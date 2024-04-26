import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    public static Connection openConnection() {
        try {
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(
                    "jdbc:h2:~/test",
                    "sa",
                    ""
            );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
