import java.util.Scanner;
//test
public class main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();
        int choice;
        String filename = "data/inventory.csv";
        
        manager.loadFromFile(filename); 

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); //clears leftover newline

            switch(choice){
                case 1:
                    System.out.print("Enter item ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    
                    System.out.print("Enter price ");
                    double price = scanner.nextDouble();

                    InventoryItem item = new InventoryItem(id, name, quantity, price);
                    manager.addItem(item);
                    break;
                
                case 2:
                    manager.viewAllItems();
                    break;

                case 3:
                    System.out.print("Enter item name to search: ");
                    String searchName = scanner.nextLine();

                    InventoryItem foundItem = manager.searchByName(searchName);
                    if(foundItem != null) {
                        System.out.println("Item found: ");
                        System.out.println(foundItem);
                    } else {
                        System.out.println("Item not found");
                    }
                    break;

                case 4: 
                    System.out.print("Enter item ID to update: ");
                    int updateId = scanner.nextInt();

                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();

                    if(manager.updateQuantity(updateId, newQuantity))
                    {
                        System.out.println("Quantity updated successfully.");
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter item ID to delete: ");
                    int deleteId = scanner.nextInt();

                    if (manager.deleteItem(deleteId)) {
                        System.out.println("Item deleted successfully.");
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 6:
                    manager.saveToFile(filename);
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            
            }
        } while(choice != 6);

        scanner.close();
    }

    public static void displayMenu(){
        System.out.println("\n==== Inventory Management System ====");
        System.out.println("1. Add item");
        System.out.println("2. View all items");
        System.out.println("3. Search item by name");
        System.out.println("4. Update item quantity");
        System.out.println("5. Delete item");
        System.out.println("6. Save and Exit");
        System.out.print("Choose an option: ");
    }
}