import java.util.ArrayList;

/**
 * <h1>Room constructor</h1>
 * Creates and operates a Room
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2017-01-08
 */
public class Room {
	private String roomName;
	private Room northDir;
	private Room eastDir;
	private Room southDir;
	private Room westDir;
	private boolean northLock;
	private boolean eastLock;
	private boolean southLock;
	private boolean westLock;
	private ArrayList<Creature> creatures;
	private ArrayList<Item> items;
	
	/**
	 * Constructor for Room
	 * 
	 * @param roomName The name of the Room
	 */
	public Room(String roomName) {
		this.roomName = roomName;
		this.creatures = new ArrayList<Creature>();
		this.items = new ArrayList<Item>();
	}
	
	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * @return the northDir
	 */
	public Room getNorthDir() {
		return northDir;
	}

	/**
	 * @param northDir the northDir to set
	 */
	public void setNorthDir(Room northDir) {
		this.northDir = northDir;
	}
	
	/**
	 * @return the eastDir
	 */
	public Room getEastDir() {
		return eastDir;
	}

	/**
	 * @param eastDir the eastDir to set
	 */
	public void setEastDir(Room eastDir) {
		this.eastDir = eastDir;
	}

	/**
	 * @return the southDir
	 */
	public Room getSouthDir() {
		return southDir;
	}

	/**
	 * @param southDir the southDir to set
	 */
	public void setSouthDir(Room southDir) {
		this.southDir = southDir;
	}

	/**
	 * @return the westDir
	 */
	public Room getWestDir() {
		return westDir;
	}

	/**
	 * @param westDir the westDir to set
	 */
	public void setWestDir(Room westDir) {
		this.westDir = westDir;
	}

	/**
	 * @return the northLock
	 */
	public boolean isNorthLock() {
		return !northLock;
	}

	/**
	 * @param northLock the northLock to set
	 */
	public void setNorthUnlock(boolean northLock) {
		this.northLock = northLock;
	}

	/**
	 * @return the eastLock
	 */
	public boolean isEastLock() {
		return !eastLock;
	}

	/**
	 * @param eastLock the eastLock to set
	 */
	public void setEastUnlock(boolean eastLock) {
		this.eastLock = eastLock;
	}

	/**
	 * @return the southLock
	 */
	public boolean isSouthLock() {
		return !southLock;
	}

	/**
	 * @param southLock the southLock to set
	 */
	public void setSouthUnlock(boolean southLock) {
		this.southLock = southLock;
	}

	/**
	 * @return the westLock
	 */
	public boolean isWestLock() {
		return !westLock;
	}

	/**
	 * @param westLock the westLock to set
	 */
	public void setWestUnlock(boolean westLock) {
		this.westLock = westLock;
	}

	/**
	 * @return ArrayList of the creatures in the Room
	 */
	public ArrayList<Creature> getCreatures() {
		return creatures;
	}
	
	/**
	 * Checks if a Creature with creatureName is in the Room
	 * 
	 * @param creatureName The name of the Creature
	 * @return boolean True if Creature is found in the Room, else False
	 */
	public boolean isCreatureThere(String creatureName) {
		for(Creature c : creatures) {
			if(c.creatureName.toLowerCase().equals(creatureName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets a Creature with creatureName in the Room
	 * 
	 * @param creatureName The Creatures name
	 * @return Creature c with the creatureName, or null if not found
	 */
	public Creature getCreature(String creatureName) {
		for(Creature c : creatures) {
			if(c.creatureName.toLowerCase().equals(creatureName)) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Checks if a Teacher is in the Room
	 * 
	 * @return boolean True if Teacher is in the Room, else False
	 */
	public boolean isTeacherThere() {
		for(Creature c : creatures) {
			if(c instanceof Teacher) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Finds and returns a Teacher in the Room
	 * 
	 * @return Teacher c, or null if not found
	 */
	public Teacher getTeacher() {
		for(Creature c : creatures) {
			if(c instanceof Teacher) {
				return (Teacher) c;
			}
		}
		return null;
	}
	
	/**
	 * Adds a Creature to the Room
	 * 
	 * @param creature the Creature to add to the Room
	 */
	public void addCreature(Creature creature) {
		this.creatures.add(creature);
	}

	/**
	 * @return ArrayList of the Items in the Room
	 */
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	/**
	 * Checks if an Item exists in the Room
	 * 
	 * @param itemName the name of an Item to be checked
	 * @return boolean True if Item exists in Room, else False
	 */
	public boolean hasItem(String itemName) {
		for(Item i : items) {
			if(i.toString().toLowerCase().equals(itemName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param item the Item to add to a Room
	 */
	public void addItem(Item item) {
		this.items.add(item);
	}
	
	/**
	 * Removes specified Item from the Room
	 * 
	 * @param itemName The name of the Item to be removed
	 * @return Item The removed Item, or null if no Item was removed
	 */
	public Item removeItem(String itemName) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).toString().toLowerCase().equals(itemName)) {
				return items.remove(i);
			}
		}
		return null;
	}
	
	/**
	 * String s with the room name, room name of what room is/isn't in each of the directions, names of Items in the room, names of Creatures in the room
	 */
	public String toString() {
		String s =  "You are in " + roomName + ".\n\n";
		
		s += "Directions: \n";
		if(northDir == null) {
			s += " -North: Nothing!\n";
		} else {
			s += " -North: " + northDir.getRoomName() + "\n";
		}
		if(eastDir == null) {
			s += " -East: Nothing!\n";
		} else {
			s += " -East: " + eastDir.getRoomName() + "\n";
		}
		if(southDir == null) {
			s += " -South: Nothing!\n";
		} else {
			s += " -South: " + southDir.getRoomName() + "\n";
		}
		if(westDir == null) {
			s += " -West: Nothing!\n";
		} else {
			s += " -West: " + westDir.getRoomName() + "\n";
		}
		s += "\n";
		
		if(items.size() == 0) {
			s += "Items: No items seen in the room.\n\n";
		} else {
			s += "Items: \n";
			for(Item i : items) {
				s += " -" + i.toString() + "\n";
			}
			s += "\n";
		}
		
		if(creatures.size() == 0) {
			s += "Creatures: No creatures seen in the room.\n";
		} else {
			s += "Creatures: \n";
			for(Creature c : creatures) {
				s += " -" + c.toString() + "\n";
			}
		}
		return s;
	}
	
}
