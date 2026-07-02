import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/inventory_db";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "MakeYourown";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            DATABASE_URL,
            DATABASE_USER,
            DATABASE_PASSWORD
        );
    }


    public static void initializeDatabase() {
        String sql = """
                CREATE TABLE IF NOT EXISTS items (
                    id INT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    quantity INT NOT NULL,
                    price DECIMAL(10, 2) NOT NULL
                );
                """;
        
        try (
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
        ) {
            statement.execute(sql);
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            System.out.println("Error: Could not initialize database.");
            System.out.println(e.getMessage());
        }
    }
}
