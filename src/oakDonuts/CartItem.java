package oakDonuts;

public class CartItem {
    private int itemId;
    private String name;
    private double price;
    private int qty;

    public CartItem(int itemId, String name, double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.qty = 1;
    }

    public void increaseQty() { qty++; }
    public void decreaseQty() { if (qty > 1) qty--; }

    public int getItemId() { return itemId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQty() { return qty; }
    public double getTotal() { return qty * price; }
}

