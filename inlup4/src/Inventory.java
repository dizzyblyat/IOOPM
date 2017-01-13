import java.util.ArrayList;

/**
 * <h1>Inventory constructor</h1>
 * Creates and operates the Inventory
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2017-01-08
 */
public class Inventory {
	private ArrayList<Item> inventory;  // Avatar inventory list
	private int invSpaceMax;            // Space in inventory
	private int invSpacePrint;          // For printing inventory space
	
	/**
	 * Constructor for Inventory
	 */
	public Inventory() {
		this.inventory = new ArrayList<Item>();
		this.invSpaceMax = 5;
		this.invSpacePrint = 0;
	}
	
	/**
	 * Adds an Item into the inventory
	 * 
	 * @param item the Item to be added
	 * @return boolean True if Item was added, else False
	 */
	public boolean addItem(Item item) {
		int itemWeight = item.getWeight();
		
		if(this.invSpaceMax - itemWeight >= 0) {
			this.inventory.add(item);
			this.invSpaceMax -= itemWeight;
			this.invSpacePrint += itemWeight;
			return true;
		}
		return false;
	}
	
	/**
	 * Deletes an Item from the inventory
	 * 
	 * @param itemName The name of the Item to be deleted
	 * @return boolean True if Item was deleted, else False
	 */
	public boolean removeItem(String itemName) {
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).toString().toLowerCase().equals(itemName)) {
				invSpaceMax += inventory.get(i).getWeight();
				invSpacePrint -= inventory.get(i).getWeight();
				inventory.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if an Item exists in the inventory
	 * 
	 * @param itemName The name of the Item to be checked
	 * @return boolean True if Item exists, else False
	 */
	public boolean hasItem(String itemName) {
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).toString().equals(itemName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return int the inventory space taken
	 */
	public int getCurrentSpaceTaken() {
		return this.invSpacePrint;
	}
	
	/**
	 * @return int the inventory space left
	 */
	public int getMaxSpace() {
		return this.invSpaceMax;
	}
	
	/**
	 * Prints the contents of the Inventory 
	 */
	public void printInventoryContents() {
		if(inventory.isEmpty()) {
			System.out.println("Your Inventory is empty!\n");
		} else {
			int keys = 0;
			int keyWeight = 0;
			for(Item i : inventory) {
				if(i instanceof Book) {
					System.out.println(((Book) i).inspect());
				} else {
					keyWeight = i.getWeight();
					keys++;
				}
			}
			System.out.println("Keys: " + keys);
			System.out.println(" -Weight: " + keys * keyWeight);
			System.out.println("\nSpace occupied: " + invSpacePrint + "/" + (invSpaceMax + invSpacePrint) + "\n");
		}
	}
	
}
