import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demoh2 {
    public static void main(String[] args) {
        try (var con = ConnectionManager.openConnection();){
            System.out.println("Connected successfully");
            String sql = "INSERT INTO students (name) values ('Kuro')";
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);
            int rows = statement.executeUpdate(sql);

            if (rows > 0) {
                System.out.println("A row inserted.");
            }
            sql = "SELECT * from students";
            ResultSet resultSet = statement.executeQuery(sql);

            int count = 0;
            while (resultSet.next()) {
                count++;
                int ID = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                System.out.println("Student #" + count + ": " + ID + ", " + name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
