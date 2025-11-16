package oakDonuts;

import java.sql.*;

public class MenuItemsSeeder {

    public static void seedMenu() {

        String url = "jdbc:derby:/Users/lisab/DerbyDatabase/DonutShop";

        try (Connection conn = DriverManager.getConnection(url)) {

            // 1. Check if data already exists
            Statement checkStmt = conn.createStatement();
            ResultSet rs = checkStmt.executeQuery("SELECT COUNT(*) FROM MENU_ITEMS");
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                System.out.println("Menu already seeded — skipping.");
                return;     // ⛔ DO NOT seed again
            }

            System.out.println("Seeding menu...");
            String sql = "INSERT INTO MENU_ITEMS (NAME, PRICE, CATEGORY, AVAILABLE) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            insert(ps, "Glazed Donut",       1.25, "Classic",    true);
            insert(ps, "Chocolate Donut",    1.50, "Classic",    true);
            insert(ps, "Strawberry Frosted", 1.75, "Specialty",  true);
            insert(ps, "Boston Creme",       2.00, "Filled",     true);
            insert(ps, "Apple Fritter",      2.75, "Specialty",  true);
            insert(ps, "Maple Bar",          2.50, "Specialty",  true);

            System.out.println("Menu inserted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insert(PreparedStatement ps,
                               String name,
                               double price,
                               String category,
                               boolean available) throws Exception {

        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.setString(3, category);
        ps.setBoolean(4, available);
        ps.executeUpdate();
    }
}
