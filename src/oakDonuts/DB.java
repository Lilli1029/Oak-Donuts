package oakDonuts;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private static final String URL =
            "jdbc:derby:/Users/lisab/DerbyDatabase/DonutShop;create=true";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (Exception e) {
            throw new RuntimeException("Database connection failed", e);
        }
    }
}
