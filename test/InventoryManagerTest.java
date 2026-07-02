import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryManagerTest {

    @Test
    public void testAddItem() {
        // Arrange: create a manager and an item.
        InventoryManager manager = new InventoryManager();
        InventoryItem item = new InventoryItem(1, "Laptop", 5, 799.99);

        // Act: add the item.
        manager.addItem(item);

        // Assert: check that the item count is now 1.
        assertEquals(1, manager.getItemCount());
    }

    @Test
    public void testSearchByNameFound() {
        InventoryManager manager = new InventoryManager();
        InventoryItem item = new InventoryItem(1, "Laptop", 5, 799.99);

        manager.addItem(item);

        InventoryItem result = manager.searchByName("Laptop");

        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        assertEquals(1, result.getId());
    }

    @Test
    public void testSearchByNameIgnoresCase() {
        InventoryManager manager = new InventoryManager();
        InventoryItem item = new InventoryItem(1, "Laptop", 5, 799.99);

        manager.addItem(item);

        InventoryItem result = manager.searchByName("laptop");

        assertNotNull(result);
        assertEquals("Laptop", result.getName());
    }

    @Test
    public void testSearchByNameNotFound() {
        InventoryManager manager = new InventoryManager();

        InventoryItem result = manager.searchByName("Keyboard");

        assertNull(result);
    }

    @Test
    public void testUpdateQuantitySuccess() {
        InventoryManager manager = new InventoryManager();
        InventoryItem item = new InventoryItem(1, "Laptop", 5, 799.99);

        manager.addItem(item);

        boolean updated = manager.updateQuantity(1, 10);

        assertTrue(updated);
        assertEquals(10, item.getQuantity());
    }

    @Test
    public void testUpdateQuantityItemNotFound() {
        InventoryManager manager = new InventoryManager();

        boolean updated = manager.updateQuantity(99, 10);

        assertFalse(updated);
    }

    @Test
    public void testDeleteItemSuccess() {
        InventoryManager manager = new InventoryManager();
        InventoryItem item = new InventoryItem(1, "Laptop", 5, 799.99);

        manager.addItem(item);

        boolean deleted = manager.deleteItem(1);

        assertTrue(deleted);
        assertEquals(0, manager.getItemCount());
    }

    @Test
    public void testDeleteItemNotFound() {
        InventoryManager manager = new InventoryManager();

        boolean deleted = manager.deleteItem(99);

        assertFalse(deleted);
    }
}