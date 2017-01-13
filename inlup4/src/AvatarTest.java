import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class AvatarTest {
	TestWorld world = new TestWorld();
	ArrayList<Course> courseTestList = new ArrayList<Course>();
	Avatar avatar = new Avatar(courseTestList, world.roomTestList.get(0));
	
	
	@Test
	public void testTake() {
		assertFalse(avatar.hasInInventory("Key"));
		
		avatar.take("key");
		
		assertTrue(avatar.hasInInventory("Key"));
	}
	
	@Test
	public void testDrop() {
		avatar.take("key");
		
		assertTrue(avatar.hasInInventory("Key"));
		
		avatar.drop("key");
		
		assertFalse(avatar.hasInInventory("Key"));
	}
	
	@Test
	public void testAddItemToInventory() {
		Item key = new Key();
		avatar.addItemToInventory(key);
		
		assertTrue(avatar.hasInInventory("Key"));
	}
	
	@Test
	public void testMove() {
		assertEquals("Middle", avatar.getPosition().getRoomName());
		
		avatar.move("e");

		assertEquals("East", avatar.getPosition().getRoomName());

		avatar.move("w");
		
		assertEquals("Middle", avatar.getPosition().getRoomName());
	}

	@Test
	public void testUnlock() {
		Item key = new Key();
		avatar.addItemToInventory(key);
		world.lockNorth();
		
		assertTrue(avatar.getPosition().isNorthLock());
		
		avatar.unlock("n");
		
		assertFalse(avatar.getPosition().isNorthLock());
		
		avatar.move("n");
		
		assertEquals("North", avatar.getPosition().getRoomName());
		
		avatar.move("s");
		
		assertEquals("Middle", avatar.getPosition().getRoomName());
	}

}
