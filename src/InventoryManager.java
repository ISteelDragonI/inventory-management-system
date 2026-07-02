import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class InventoryManager {
    // ArrayList store multiple invetory item objects
    private ArrayList<InventoryItem> items;

    // Constructor creates an empty inventory list
    public InventoryManager(){
        items = new ArrayList<>();
    }

    // Returns how many items are currently in the inventory.
    // This is useful for testing and for checking inventory size.
    public int getItemCount() {
        return items.size();
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

    public void saveToFile(String filename){
        try {
            //FileWriter opens the file so we can write text into it.
            FileWriter writer = new FileWriter(filename);
            for (InventoryItem item : items)
            {
                writer.write(
                    item.getId() + "," +
                    item.getName() + "," +
                    item.getQuantity() + "," +
                    item.getPrice()  + "\n"
                );
            }
            writer.close();
            System.out.println("Iventory saved successfully"); 
        } catch (IOException e) {
            System.out.println("Error: Could not save inventory.");
        }
    }

    // Loads inventtory items from a CSV file
    public void loadFromFile(String filename){
        try {
            File file = new File(filename);

            // If the file does not exist uet the is nothing to load.
            if (!file.exists())
            {
                System.out.println("No saved inventory file found. Starting with empty inventory");
                return;
            }

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine())
            {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                
                //make sure line has only 4 values
                if (parts.length == 4)
                {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);

                    InventoryItem item = new InventoryItem(id, name, quantity, price);
                    items.add(item);
                }
            }

            fileScanner.close();

            System.out.println("Inventory loaded successfully");
        } catch (IOException e) {
            System.out.println("Error: Could not load inventory file");
        } catch (NumberFormatException e) {
            System.out.println("Error: Inventory file contains invalid number data.");
        }
         
    }
}
