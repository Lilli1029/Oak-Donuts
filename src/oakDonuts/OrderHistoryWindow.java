package oakDonuts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class OrderHistoryWindow extends JFrame {

    public OrderHistoryWindow() {
        setTitle("Order History");
        setSize(600, 400);
        setLocationRelativeTo(null);

        String[] cols = {"Order ID", "Date", "Subtotal", "Tax", "Total"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);
        table.setRowHeight(22);

        add(new JScrollPane(table));

        try (Connection conn = DriverManager.getConnection(
                "jdbc:derby:/Users/lisab/DerbyDatabase/DonutShop")) {

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT ORDER_ID, ORDER_DATE, SUBTOTAL, TAX, TOTAL FROM ORDERS ORDER BY ORDER_ID DESC"
            );
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("ORDER_ID"),
                        rs.getTimestamp("ORDER_DATE"),
                        String.format("$%.2f", rs.getDouble("SUBTOTAL")),
                        String.format("$%.2f", rs.getDouble("TAX")),
                        String.format("$%.2f", rs.getDouble("TOTAL"))
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}