import java.util.ArrayList;
public class InventoryManager {
    // ArrayList store multiple invetory item objects
    private ArrayList<InventoryItem> items;

    // Constructor creates an empty inventory list
    public InventoryManager(){
        items = new ArrayList<>();
    }

    // Adds a new item to the inventory
    public void addItem(InventoryItem item){
        items.add(item);
        System.out.println("Item added successfully.");
    }

    // Displays every item in the inventory
    public void viewAllItems(){
        if(items.isEmpty()){
            System.out.println("Inventory is currently empty.");
            return;
        }

        System.out.println("\n--- Inventory Items ---");
        for (InventoryItem item : items)
        {
            System.out.println(item);
        }
    }

    // Searches for item by name
    public InventoryItem searchByName(String name){
        for(InventoryItem item : items)
        {
            if(item.getName().equalsIgnoreCase(name))
            {
                return item;
            }
        }

        return null;
    }

    // Updates quantity of an item based on ID
    public boolean updateQuantity(int id, int newQuantity){
        for(InventoryItem item : items){
            if(item.getId() == id){
                item.setQuantity(newQuantity);
                return true;
            }
        }

        return false;
    }

    // Deletes an item based on ID
    public boolean deleteItem(int id){
        for(InventoryItem item : items)
        {
            if(item.getId() == id)
            {
                items.remove(item);
                return true;
            }
        }

        return false;
    }
}
