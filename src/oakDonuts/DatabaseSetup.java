package oakDonuts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    public static void main(String[] args) {
        String url = "jdbc:derby:/Users/lisab/DerbyDatabase/DonutShop;create=true";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // ---- MENU_ITEMS table ----
            try {
                stmt.executeUpdate(
                        "CREATE TABLE MENU_ITEMS (" +
                                "ITEM_ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                                "NAME VARCHAR(100) NOT NULL, " +
                                "PRICE DECIMAL(6,2) NOT NULL, " +
                                "CATEGORY VARCHAR(50), " +
                                "AVAILABLE BOOLEAN" +
                                ")"
                );
                System.out.println("MENU_ITEMS table created.");
            } catch (SQLException e) {
                // table already exists
            }

            // ---- ORDERS table ----
            try {
                stmt.executeUpdate(
                        "CREATE TABLE ORDERS (" +
                                "ORDER_ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                                "ORDER_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                                "SUBTOTAL DOUBLE, " +
                                "TAX DOUBLE, " +
                                "TOTAL DOUBLE" +
                                ")"
                );
                System.out.println("ORDERS table created.");
            } catch (SQLException e) {
                // table already exists
            }

            // ---- ORDER_DETAILS table ----
            try {
                stmt.executeUpdate(
                        "CREATE TABLE ORDER_DETAILS (" +
                                "DETAIL_ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                                "ORDER_ID INT NOT NULL, " +
                                "ITEM_ID INT NOT NULL, " +
                                "QUANTITY INT NOT NULL, " +
                                "LINE_PRICE DOUBLE, " +
                                "FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ORDER_ID), " +
                                "FOREIGN KEY (ITEM_ID) REFERENCES MENU_ITEMS(ITEM_ID)" +
                                ")"
                );
                System.out.println("ORDER_DETAILS table created.");
            } catch (SQLException e) {
                // table already exists
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}