package oakDonuts;

public class OrderRow {
    private String name;
    private double price;
    private int qty;

    public OrderRow(String name, double price, int qty) {
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQty() { return qty; }
}