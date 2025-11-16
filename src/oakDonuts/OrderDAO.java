package oakDonuts;

import java.sql.*;
import java.util.List;

public class OrderDAO {

    private final String url = "jdbc:derby:/Users/lisab/DerbyDatabase/DonutShop";

    public int saveOrder(List<CartItem> items, double subtotal, double tax, double total) {
        int generatedOrderId = -1;

        try (Connection conn = DriverManager.getConnection(url)) {
            conn.setAutoCommit(false);

            // Insert ORDER
            PreparedStatement orderStmt = conn.prepareStatement(
                    "INSERT INTO ORDERS (SUBTOTAL, TAX, TOTAL) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            orderStmt.setDouble(1, subtotal);
            orderStmt.setDouble(2, tax);
            orderStmt.setDouble(3, total);
            orderStmt.executeUpdate();

            ResultSet rs = orderStmt.getGeneratedKeys();
            if (rs.next()) {
                generatedOrderId = rs.getInt(1);
            }

            // Insert ORDER_DETAILS rows
            PreparedStatement detailStmt = conn.prepareStatement(
                    "INSERT INTO ORDER_DETAILS (ORDER_ID, ITEM_ID, QUANTITY, LINE_PRICE) VALUES (?,?,?,?)");

            for (CartItem c : items) {
                detailStmt.setInt(1, generatedOrderId);
                detailStmt.setInt(2, c.getItemId());
                detailStmt.setInt(3, c.getQty());
                detailStmt.setDouble(4, c.getTotal());
                detailStmt.addBatch();
            }

            detailStmt.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedOrderId;
    }
}
