import static org.junit.Assert.*;

import org.junit.Test;

public class InventoryTest {
	Inventory inventory = new Inventory();
	Item key = new Key();

	@Test
	public final void testAddItem() {
		assertFalse(inventory.hasItem("Key"));
		
		inventory.addItem(key);
		
		assertTrue(inventory.hasItem("Key"));
	}

	@Test
	public final void testRemoveItem() {
		assertFalse(inventory.hasItem("Key"));
		
		inventory.addItem(key);
		
		assertTrue(inventory.hasItem("Key"));
		
		inventory.removeItem("key");
		
		assertFalse(inventory.hasItem("Key"));
	}

}
