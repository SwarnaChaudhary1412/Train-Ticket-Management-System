
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    // Method to establish MySQL database connection
    public static Connection getConnection() {

        Connection con = null;

        try {
            String url = "jdbc:mysql://localhost:3306/train_management";
            String username = "root";
            String password = "YOUR_MYSQL_PASSWORD";
            

            con = DriverManager.getConnection(url, username, password);

            System.out.println("Database Connected Successfully!");

        } catch (Exception e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }

        return con;
    }

    // Main method to test database connection
    public static void main(String[] args) {

        Connection con = getConnection();

        if (con != null) {
            System.out.println("JDBC Connection Test Passed!");

            try {
                con.close();
                System.out.println("Database Connection Closed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

