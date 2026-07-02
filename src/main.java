import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        String filename = "data/inventory.csv";

        // Load saved inventory when the program starts.
        manager.loadFromFile(filename);

        int choice;

        do {
            displayMenu();

            // Safely get the user's menu choice.
            choice = getIntInput(scanner, "Choose an option: ");

            switch (choice) {
                case 1:
                    addItem(scanner, manager);
                    break;

                case 2:
                    manager.viewAllItems();
                    break;

                case 3:
                    searchItem(scanner, manager);
                    break;

                case 4:
                    updateItemQuantity(scanner, manager);
                    break;

                case 5:
                    deleteItem(scanner, manager);
                    break;

                case 6:
                    manager.saveToFile(filename);
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1 through 6.");
            }

        } while (choice != 6);

        scanner.close();
    }

    // Displays the menu options.
    public static void displayMenu() {
        System.out.println("\n==== Inventory Management System ====");
        System.out.println("1. Add item");
        System.out.println("2. View all items");
        System.out.println("3. Search item by name");
        System.out.println("4. Update item quantity");
        System.out.println("5. Delete item");
        System.out.println("6. Save and exit");
    }

    // Handles adding a new inventory item.
    public static void addItem(Scanner scanner, InventoryManager manager) {
        int id = getIntInput(scanner, "Enter item ID: ");
        String name = getStringInput(scanner, "Enter item name: ");
        int quantity = getIntInput(scanner, "Enter quantity: ");
        double price = getDoubleInput(scanner, "Enter price: ");

        InventoryItem item = new InventoryItem(id, name, quantity, price);
        manager.addItem(item);
    }

    // Handles searching for an item by name.
    public static void searchItem(Scanner scanner, InventoryManager manager) {
        String searchName = getStringInput(scanner, "Enter item name to search: ");

        InventoryItem foundItem = manager.searchByName(searchName);

        if (foundItem != null) {
            System.out.println("Item found:");
            System.out.println(foundItem);
        } else {
            System.out.println("Item not found.");
        }
    }

    // Handles updating an item's quantity.
    public static void updateItemQuantity(Scanner scanner, InventoryManager manager) {
        int updateId = getIntInput(scanner, "Enter item ID to update: ");
        int newQuantity = getIntInput(scanner, "Enter new quantity: ");

        if (manager.updateQuantity(updateId, newQuantity)) {
            System.out.println("Quantity updated successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    // Handles deleting an item.
    public static void deleteItem(Scanner scanner, InventoryManager manager) {
        int deleteId = getIntInput(scanner, "Enter item ID to delete: ");

        if (manager.deleteItem(deleteId)) {
            System.out.println("Item deleted successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    // Gets a valid integer from the user.
    public static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // Clears leftover newline
                return value;
            } else {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.nextLine(); // Clears invalid input
            }
        }
    }

    // Gets a valid decimal number from the user.
    public static double getDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);

            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine(); // Clears leftover newline
                return value;
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clears invalid input
            }
        }
    }

    // Gets a non-empty string from the user.
    public static String getStringInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
    }
}