package oakDonuts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    private final String url = "jdbc:derby:/Users/lisab/DerbyDatabase/DonutShop";

    // -------------------------
    // GET ALL MENU ITEMS
    // -------------------------
    public List<MenuItems> getAllMenuItems() {
        List<MenuItems> items = new ArrayList<>();

        String sql = "SELECT ITEM_ID, NAME, PRICE, CATEGORY, AVAILABLE FROM MENU_ITEMS";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MenuItems item = new MenuItems(
                        rs.getInt("ITEM_ID"),
                        rs.getString("NAME"),
                        rs.getDouble("PRICE"),
                        rs.getString("CATEGORY"),
                        rs.getBoolean("AVAILABLE")
                );
                items.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    // -------------------------
    // INSERT MENU ITEM
    // -------------------------
    public void insertMenuItem(MenuItems item) {
        String sql = "INSERT INTO MENU_ITEMS (NAME, PRICE, CATEGORY, AVAILABLE) VALUES (?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getCategory());
            ps.setBoolean(4, item.isAvailable());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------------
    // UPDATE MENU ITEM
    // -------------------------
    public void updateMenuItem(MenuItems item) {
        String sql = "UPDATE MENU_ITEMS SET NAME=?, PRICE=?, CATEGORY=?, AVAILABLE=? WHERE ITEM_ID=?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setString(3, item.getCategory());
            ps.setBoolean(4, item.isAvailable());
            ps.setInt(5, item.getItemId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------------
    // DELETE MENU ITEM
    // -------------------------
    public void deleteMenuItem(int itemId) {
        String sql = "DELETE FROM MENU_ITEMS WHERE ITEM_ID=?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, itemId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
