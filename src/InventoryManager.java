import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryManager {

    public void addItem(InventoryItem item) {
        String sql = "INSERT INTO items (id, name, quantity, price) VALUES (?, ?, ?, ?)";

        try (
                Connection connection = DatabaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, item.getId());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getQuantity());
            statement.setDouble(4, item.getPrice());

            statement.executeUpdate();
            System.out.println("Item added successfully.");
        } catch (SQLException e) {
            System.out.println("Error: Could not add item.");
            System.out.println(e.getMessage());
        }
    }

    public void viewAllItems() {
        String sql = "SELECT id, name, quantity, price FROM items";

        try (
                Connection connection = DatabaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            boolean foundAny = false;

            System.out.println("\n--- Inventory Items ---");

            while (resultSet.next()) {
                foundAny = true;

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");

                InventoryItem item = new InventoryItem(id, name, quantity, price);
                System.out.println(item);
            }

            if (!foundAny) {
                System.out.println("Inventory is currently empty.");
            }

        } catch (SQLException e) {
            System.out.println("Error: Could not retrieve inventory.");
            System.out.println(e.getMessage());
        }
    }

    public InventoryItem searchByName(String name) {
        String sql = "SELECT id, name, quantity, price FROM items WHERE LOWER(name) = LOWER(?)";

        try (
                Connection connection = DatabaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String itemName = resultSet.getString("name");
                    int quantity = resultSet.getInt("quantity");
                    double price = resultSet.getDouble("price");

                    return new InventoryItem(id, itemName, quantity, price);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: Could not search inventory.");
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean updateQuantity(int id, int newQuantity) {
        String sql = "UPDATE items SET quantity = ? WHERE id = ?";

        try (
                Connection connection = DatabaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, newQuantity);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error: Could not update item.");
            System.out.println(e.getMessage());
        }

        return false;
    }

    public boolean deleteItem(int id) {
        String sql = "DELETE FROM items WHERE id = ?";

        try (
                Connection connection = DatabaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Error: Could not delete item.");
            System.out.println(e.getMessage());
        }

        return false;
    }

    public int getItemCount() {
        String sql = "SELECT COUNT(*) AS count FROM items";

        try (
                Connection connection = DatabaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }

        } catch (SQLException e) {
            System.out.println("Error: Could not count items.");
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public void clearInventory() {
        String sql = "DELETE FROM items";

        try (
                Connection connection = DatabaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: Could not clear inventory.");
            System.out.println(e.getMessage());
        }
    }
}