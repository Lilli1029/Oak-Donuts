package oakDonuts;

public class Calculations {
    private int itemId;
    private String name;
    private double price;
    private String category;
    private boolean available;

    public Calculations(int itemId, String name, double price, String category, boolean available) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    public int getItemId() { return itemId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return available; }

    @Override
    public String toString() {
        return itemId + ": " + name + " - $" + price + " (" + category + ")";
    }
}