public class InventoryItem {
    // These are the attributes/fields of an inventory item
    private int id;
    private String name;
    private int quantity;
    private double price;

    // Contstructor methods let other classes read private fields safely.
    public InventoryItem(int id, String name, int quantity, double price)
    {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter methods let other classes read private fields safely
    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getPrice(){
        return price;
    }

    // Setter method lets other classes update private fields safely
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setPrice(double price){
        this.price = price;
    }

    // This comntrols how the item is printed
    @Override
    public String toString() {
        return "ID: " + id +
                " | Name: " + name +
                " | Quantity: " + quantity +
                " | Price: $" + price;
    }
}
