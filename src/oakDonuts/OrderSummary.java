package oakDonuts;

import java.sql.Timestamp;

public class OrderSummary {
    private int orderId;
    private Timestamp date;
    private double subtotal;
    private double tax;
    private double total;

    public OrderSummary(int orderId, Timestamp date, double subtotal, double tax, double total) {
        this.orderId = orderId;
        this.date = date;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
    }

    public int getOrderId() { return orderId; }
    public Timestamp getDate() { return date; }
    public double getSubtotal() { return subtotal; }
    public double getTax() { return tax; }
    public double getTotal() { return total; }
}
