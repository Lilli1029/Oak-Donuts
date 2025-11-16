package oakDonuts;

public class MenuItems {

    private int itemId;
    private String name;
    private double price;
    private String category;
    private boolean available;

    public MenuItems(int itemId, String name, double price, String category, boolean available) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
    }

    // Getters
    public int getItemId() { return itemId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return available; }

    // Setters
    public void setItemId(int itemId) { this.itemId = itemId; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}
