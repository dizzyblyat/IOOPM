import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class RoomTest {
	TestWorld world = new TestWorld();
	Room room = world.getRoom(0);

	@Test
	public final void testGetNorthDir() {
		assertEquals(world.getRoom(1), room.getNorthDir());
	}

	@Test
	public final void testIsNorthLock() {
		assertFalse(room.isNorthLock());
	}

	@Test
	public final void testSetNorthUnlock() {
		assertFalse(room.isNorthLock());
		
		room.setNorthUnlock(false);
		
		assertTrue(room.isNorthLock());
	}
	
	@Test
	public final void testSetNorthDir() {
		assertEquals(world.getRoom(1), room.getNorthDir());
		
		room.setNorthDir(null);

		assertEquals(null, room.getNorthDir());
	}

	@Test
	public final void testGetCreatures() {
		assertEquals("Teacher1", room.getCreatures().get(0).creatureName);
	}

	@Test
	public final void testIsCreatureThere() {
		room.isCreatureThere("teacher1");
	}

	@Test
	public final void testGetCreature() {
		assertNotEquals(null, room.getCreature("teacher1"));
	}

	@Test
	public final void testIsTeacherThere() {
		assertTrue(room.isTeacherThere());
	}
	
	@Test
	public final void testAddCreature() {
		Creature c = new Student(1, null, null, null, null);
		room.addCreature(c);
		
		assertTrue(room.isCreatureThere("student1"));
	}

	@Test
	public final void testGetItems() {
		assertEquals("Key", room.getItems().get(0).itemName);
	}

	@Test
	public final void testHasItem() {
		assertTrue(room.hasItem("key"));
	}
	
	@Test
	public final void testAddItem() {
		Item book = new Book("Testbook", null, null, 1);
		
		room.addItem(book);
		
		assertTrue(room.hasItem("testbook"));
	}

	@Test
	public final void testRemoveItem() {
		assertTrue(room.hasItem("key"));
		
		room.removeItem("key");
		
		assertFalse(room.hasItem("key"));
	}

}

/* 
 * TestWorld needed to accurately test most classes
 * 
 * World Layout (of Rooms):
 * 
 *        [NORTH]
 *           | <-- Lines represent doors/where they lead
 * [WEST]-[MIDDLE]-[EAST]
 *           |
 *        [SOUTH]
 * 
 * All doors are unlocked, Middle Room gets a Key spawned
 */
final class TestWorld {
	ArrayList<Room> roomTestList;
	
	public TestWorld() {
		this.roomTestList = initTestRooms();
	}
	
	// Adding Rooms with their names
	public ArrayList<Room> initTestRooms() {
		ArrayList<Room> roomTestList = new ArrayList<Room>();
		roomTestList.add(new Room("Middle"));
		roomTestList.add(new Room("North"));
		roomTestList.add(new Room("East"));
		roomTestList.add(new Room("South"));
		roomTestList.add(new Room("West"));
		
		// Adding Middle Room parameters
		roomTestList.get(0).setNorthDir(roomConnector("North", roomTestList));
		roomTestList.get(0).setEastDir(roomConnector("East", roomTestList));
		roomTestList.get(0).setSouthDir(roomConnector("South", roomTestList));
		roomTestList.get(0).setWestDir(roomConnector("West", roomTestList));
		roomTestList.get(0).setNorthUnlock(true);
		roomTestList.get(0).setEastUnlock(true);
		roomTestList.get(0).setSouthUnlock(true);
		roomTestList.get(0).setWestUnlock(true);
		
		// Adding North Room parameters
		roomTestList.get(1).setNorthDir(roomConnector(null, roomTestList));
		roomTestList.get(1).setEastDir(roomConnector(null, roomTestList));
		roomTestList.get(1).setSouthDir(roomConnector("Middle", roomTestList));
		roomTestList.get(1).setWestDir(roomConnector(null, roomTestList));
		roomTestList.get(1).setNorthUnlock(true);
		roomTestList.get(1).setEastUnlock(true);
		roomTestList.get(1).setSouthUnlock(true);
		roomTestList.get(1).setWestUnlock(true);
		
		// Adding East Room parameters
		roomTestList.get(2).setNorthDir(roomConnector(null, roomTestList));
		roomTestList.get(2).setEastDir(roomConnector(null, roomTestList));
		roomTestList.get(2).setSouthDir(roomConnector(null, roomTestList));
		roomTestList.get(2).setWestDir(roomConnector("Middle", roomTestList));
		roomTestList.get(2).setNorthUnlock(true);
		roomTestList.get(2).setEastUnlock(true);
		roomTestList.get(2).setSouthUnlock(true);
		roomTestList.get(2).setWestUnlock(true);
		
		// Adding South Room parameters
		roomTestList.get(3).setNorthDir(roomConnector("Middle", roomTestList));
		roomTestList.get(3).setEastDir(roomConnector(null, roomTestList));
		roomTestList.get(3).setSouthDir(roomConnector(null, roomTestList));
		roomTestList.get(3).setWestDir(roomConnector(null, roomTestList));
		roomTestList.get(3).setNorthUnlock(true);
		roomTestList.get(3).setEastUnlock(true);
		roomTestList.get(3).setSouthUnlock(true);
		roomTestList.get(3).setWestUnlock(true);
		
		// Adding West Room parameters
		roomTestList.get(4).setNorthDir(roomConnector(null, roomTestList));
		roomTestList.get(4).setEastDir(roomConnector("Middle", roomTestList));
		roomTestList.get(4).setSouthDir(roomConnector(null, roomTestList));
		roomTestList.get(4).setWestDir(roomConnector(null, roomTestList));
		roomTestList.get(4).setNorthUnlock(true);
		roomTestList.get(4).setEastUnlock(true);
		roomTestList.get(4).setSouthUnlock(true);
		roomTestList.get(4).setWestUnlock(true);
		
		// Adding a Key to Middle Room
		roomTestList.get(0).addItem(new Key());
		
		roomTestList.get(0).addCreature(new Teacher(1, null, null));
	
		return roomTestList;
	}
	
	// Connects the Rooms
	public Room roomConnector(String s, ArrayList<Room> roomList) {
		for(Room r : roomList) {
			if(r.getRoomName().equals(s)) {
				return r;
			}
		}
		return null;
	}
	
	// Dummy method for locking North door on both sides (Used for AvatarTest)
	public void lockNorth() {
		roomTestList.get(0).setNorthUnlock(false);
		roomTestList.get(1).setSouthUnlock(false);
	}
	
	// Gets index Room
	public Room getRoom(int i) {
		return this.roomTestList.get(i);
	}
	
}
