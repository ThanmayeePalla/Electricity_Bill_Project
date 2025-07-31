// DBConnection.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/project2"; // Change DB name if needed
        String user = "root";
        String password = ""; // Add your MySQL password if any

        return DriverManager.getConnection(url, user, password);
    }
}
