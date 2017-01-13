import java.util.ArrayList;
import java.util.Random;

/**
 * <h1>Avatar constructor</h1>
 * Creates and operates an Avatar
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2017-01-08
 */
public class Avatar {
	private ArrayList<Course> completeCourses;   // List of complete Courses
	private ArrayList<Course> incompleteCourses; // List of incomplete Courses
	private int doneHP;                          // Amount of HP Avatar has
	private Inventory inventory;                 // Avatar inventory
	private Room position;
	
	/**
	 * Constructor for Avatar
	 * 
	 * @param completeCourses For storing a list of Avatars currently completed Courses
	 * @param position For storing Avatars current position
	 */
	public Avatar(ArrayList<Course> completeCourses, Room position) {
		this.completeCourses = completeCourses;
		this.incompleteCourses = new ArrayList<Course>();
		this.doneHP = 0;
		for(Course c : completeCourses) {
			doneHP += c.getWorthHP();
		}
		this.inventory = new Inventory();
		this.position = position;
	}
	
	/**
	 * Prints all Creatures and Items at Avatars current position
	 */
	public void look() {
		System.out.println(this.position);
	}
	
	/**
	 * Moves the Avatar into the Room of specified direction
	 * 
	 * @param direction "n" for north, "e" for east, "s" for south and "w" for west
	 */
	public void move(String direction) {
		switch(direction) {
			case "n":
				if(this.position.getNorthDir() == null) {
					System.out.println("Nothing to the North of you!");
				} else if(this.position.isNorthLock()) {
					System.out.println("The door to the North is locked!");
				} else {
					this.position = this.position.getNorthDir();
					System.out.println("Moving North to \"" + position.getRoomName() + "\"...");
					teacherQuestioning();
				}
				break;
				
			case "e":
				if(this.position.getEastDir() == null) {
					System.out.println("Nothing to the East of you!");
				} else if(this.position.isEastLock()) {
					System.out.println("The door to the East is locked!");
				} else {
					this.position = this.position.getEastDir();
					System.out.println("Moving East to \"" + this.position.getRoomName() + "\"...");
					teacherQuestioning();
				}
				break;
				
			case "s":
				if(this.position.getSouthDir() == null) {
					System.out.println("Nothing to the South of you!");
				} else if(this.position.isSouthLock()) {
					System.out.println("The door to the South is locked!");
				} else {
					this.position = this.position.getSouthDir();
					System.out.println("Moving South to \"" + this.position.getRoomName() + "\"...");
					teacherQuestioning();
				}
				break;
				
			case "w":
				if(this.position.getWestDir() == null) {
					System.out.println("Nothing to the West of you!");
				} else if(this.position.isWestLock()) {
					System.out.println("The door to the West is locked!");
				} else {
					this.position = this.position.getWestDir();
					System.out.println("Moving West to \"" + this.position.getRoomName() + "\"...");
					teacherQuestioning();
				}
				break;
		}
	}
	
	/**
	 * Unlocks door in specified direction
	 * 
	 * @param direction "n" for north, "e" for east, "s" for south and "w" for west
	 */
	public void unlock(String direction) {
		switch(direction) {
			case "n":
				if(this.position.isNorthLock()) {
					if(this.inventory.removeItem("key")) {
						System.out.println("Removed a key from your Inventory...");
						this.position.setNorthUnlock(true);
						this.position.getNorthDir().setSouthUnlock(true);
						System.out.println("Unlocked North door to room \"" + this.position.getNorthDir().getRoomName() + "\".");
					} else {
						System.out.println("No keys found in your Inventory!");
					}
				} else {
					System.out.println("No locked door to the North of you!");
				}
				break;
				
			case "e":
				if(this.position.isEastLock()) {
					if(this.inventory.removeItem("key")) {
						System.out.println("Removed a key from your Inventory...");
						this.position.setEastUnlock(true);
						this.position.getEastDir().setWestUnlock(true);
						System.out.println("Unlocked East door to room \"" + this.position.getEastDir().getRoomName() + "\".");
					} else {
						System.out.println("No keys found in your Inventory!");
					}
				} else {
					System.out.println("No locked door to the East of you!");
				}
				break;
				
			case "s":
				if(this.position.isSouthLock()) {
					if(this.inventory.removeItem("key")) {
						System.out.println("Removed a key from your Inventory...");
						this.position.setSouthUnlock(true);
						this.position.getSouthDir().setNorthUnlock(true);
						System.out.println("Unlocked South door to room \"" + this.position.getSouthDir().getRoomName() + "\".");
					} else {
						System.out.println("No keys found in your Inventory!");
					}
				} else {
					System.out.println("No locked door to the South of you!");
				}
				break;
				
			case "w":
				if(this.position.isWestLock()) {
					if(this.inventory.removeItem("key")) {
						System.out.println("Removed a key from your Inventory...");
						this.position.setWestUnlock(true);
						this.position.getWestDir().setEastUnlock(true);
						System.out.println("Unlocked West door to room \"" + this.position.getWestDir().getRoomName() + "\".");
					} else {
						System.out.println("No keys found in your Inventory!");
					}
				} else {
					System.out.println("No locked door to the West of you!");
				}
				break;
		}
	}
	
	/**
	 * Adds an Item to the Avatars inventory list
	 * 
	 * @param itemName The name of the Item to be added
	 */
	public void take(String itemName) {
		Item item = position.removeItem(itemName);
		if(this.inventory.addItem(item)) {
			System.out.println("Picked up \"" + item.toString() + "\".");
		} else {
			position.addItem(item);
			System.out.println("Not enough space in your Inventory!");
			System.out.println("\"" + item.toString() + "\" was not picked up.");
		}
	}
	
	/**
	 * Removes an Item from the avatars inventory list
	 * 
	 * @param itemName The name of the Item to be removed
	 */
	public void drop(String itemName) {
		if(this.inventory.removeItem(itemName)) {
			System.out.println("\"" + itemName + "\" removed from Inventory...");
		} else {
			System.out.println("\"" + itemName + "\" is not in your Inventory!");
		}
	}
	
	/**
	 * Checks if Avatar has the specified Item in its inventory
	 * 
	 * @param itemName The name of the Item to be checked
	 * @return boolean True if Item exists in Avatars inventory, else False
	 */
	public boolean hasItem(String itemName) {
		if(this.inventory.hasItem(itemName)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Decides (randomly) if a Teacher will question the Avatar upon entering a Room
	 */
	public void teacherQuestioning() {
		if(this.getPosition().isTeacherThere()) {
			Teacher t = this.getPosition().getTeacher();
			Random rand = new Random();
			
			if(this.isCourseComplete(t.returnCourseName().toLowerCase())) {
				if(rand.nextInt(99) < 50 && !t.askQuestion(this.hasBook(t.returnBookName()))) {
					Course course = t.returnCourse(t.returnCourseName().toLowerCase());
					this.doneHP -= course.getWorthHP();
					this.completeCourses.remove(course);
					this.incompleteCourses.add(course);
				}
				
			} else if (this.isCourseIncomplete(t.returnCourseName().toLowerCase())) {
				if(rand.nextInt(99) < 75 && t.askQuestion(this.hasBook(t.returnBookName()))) {
					Course course = t.returnCourse(t.returnCourseName().toLowerCase());
					this.doneHP += course.getWorthHP();
					this.incompleteCourses.remove(course);
					this.completeCourses.add(course);
				}
			}
		}
	}
	
	/**
	 * Check if Avatar can graduate
	 * 
	 * @return boolean True if doneHP is equal to or more than 180, else False
	 */
	public boolean isDone() {
		return this.doneHP >= 180;
	}
	
	/**
	 * Check if Avatar has specified Course in completed courses
	 * 
	 * @param courseName Name of the Course to be checked
	 * @return boolean True if Course is in Avatars complete courses, else False
	 */
	public boolean isCourseComplete(String courseName) {
		for(Course c : this.completeCourses) {
			if(c.getCourseName().toLowerCase().equals(courseName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if Avatar has specified Course in incompleted courses
	 * 
	 * @param courseName Name of the Course to be checked
	 * @return boolean True if Course is in Avatars incomplete courses, else False
	 */
	public boolean isCourseIncomplete(String courseName) {
		for(Course c : this.incompleteCourses) {
			if(c.getCourseName().toLowerCase().equals(courseName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if Avatar has enrolled the specified course
	 * @param courseName Name of the Course to be checked
	 * @return boolean True if course is enrolled, else False
	 */
	public boolean isCourseEnrolled(String courseName) {
		if(isCourseComplete(courseName)) {
			return true;
		} else if(isCourseIncomplete(courseName)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a Item to the Avatars inventory
	 * 
	 * @param item to be added
	 */
	public void addItemToInventory(Item item) {
		this.inventory.addItem(item);
	}
	
	/**
	 * Check if Avatar has specified Book in inventory
	 * @param bookName Name of the Book to be checked
	 * @return boolean True if Book is in inventory, else False
	 */
	public boolean hasBook(String bookName) {
		if(this.inventory.hasItem(bookName)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return ArrayList completeCourses the completed courses
	 */
	public ArrayList<Course> getCompleteCourses() {
		return this.completeCourses;
	}

	/**
	 * @param completeCourses The completeCourses to set
	 */
	public void setCompleteCourses(ArrayList<Course> completeCourses) {
		this.completeCourses = completeCourses;
	}
	
	/**
	 * @return ArrayList incompleteCourses the incomplete courses
	 */
	public ArrayList<Course> getIncompleteCourses() {
		return this.incompleteCourses;
	}

	/**
	 * @param incompleteCourses The incompleteCourses to set
	 */
	public void setIncompleteCourses(ArrayList<Course> incompleteCourses) {
		this.incompleteCourses = incompleteCourses;
	}
	
	/**
	 * Adds specified Course to incompleteCourses
	 * 
	 * @param course The Course to be added
	 */
	public void addIncompleteCourse(Course course) {
		this.incompleteCourses.add(course);
	}
	
	/**
	 * Prints out the contents of the Avatars inventory
	 */
	public void getInventoryList() {
		this.inventory.printInventoryContents();
	}
	
	
	public boolean hasInInventory(String itemName) {
		if(this.inventory.hasItem(itemName)) {
			return true;			
		}
		return false;
	}
	
	/**
	 * @return int current inventory space taken
	 */
	public int getInventoryWeight() {
		return this.inventory.getCurrentSpaceTaken();
	}
	
	/**
	 * @return int current inventory max space
	 */
	public int getInventoryMaxWeight() {
		return this.inventory.getMaxSpace();
	}
	
	/**
	 * Gets the current Room of the Avatar
	 * 
	 * @return Room The position
	 */
	public Room getPosition() {
		return this.position;
	}
	
	/**
	 * @return int The doneHP to get
	 */
	public int getDoneHP() {
		return this.doneHP;
	}
	
	/**
	 * Sets doneHP to specified value
	 * 
	 * @param i The Int value to set
	 */
	public void setDoneHP(int i) {
		this.doneHP = i;
	}
	
	/**
	 * Prints out the diploma with a list of completeCourses and doneHP
	 */
	public void diploma() {
		String s = "\n";
		s += "Congratulations! The diploma was always\n";
		s += "inside you. Literally.\n";
		s += " ________________________________________\n";
		s += "|                                        |\n";
		s += "|  ██▄   ▄█ █ ▄▄  █    ████▄ █▀▄▀█ ██    |\n";   
		s += "|  █  █  ██ █   █ █    █   █ █ █ █ █ █   |\n"; 
		s += "|  █   █ ██ █▀▀▀  █    █   █ █ ▄ █ █▄▄█  |\n";
		s += "|  █  █   █ █     ███▄ ▀████ █   █ █  █  |\n";
		s += "|  ███▀      █        ▀         █     █  |\n";
		s += "|             ▀                ▀     █   |\n";
		s += "|                                        |\n";
		s += "|  ____ _____  __  __    _  _____ _____  |\n";
		s += "| / ___|__  / |  \\/  |  / \\|_   _| ____| |\n";
		s += "|| |  _  / /  | |\\/| | / _ \\ | | |  _|   |\n";
		s += "|| |_| |/ /_  | |  | |/ ___ \\| | | |___  |\n";
		s += "| \\____/____| |_|  |_/_/   \\_\\_| |_____| |\n";
		s += "|                                        |\n";
		s += "|        Completed Courses:              |\n";
		for(int i = 0; i < completeCourses.size(); i++) {
			s += "|        -" + completeCourses.get(i).getCourseName();
			for(int j = 0; j < 31 - completeCourses.get(i).getCourseName().length(); j++) {
				s += " ";
			}
			s += "|\n";
		}
		s += "|                                        |\n";
		s += "| HP Collected: " + doneHP + "                      |\n";
		s += "|                                        |\n";
		s += "|             Certified Noob             |\n";
		s += "|________________________________________|\n\n";
		s += "                THE END!\n";
		
		System.out.print(s);
	}
	
	/**
	 * @return String s with Avatars current doneHP, position, completeCourses and incompleteCourses
	 */
	public String toString() {
		String s = "";
		s =  "HP done: " + doneHP + "\n";
		s += "Location: " + position.getRoomName() + "\n";
		s += "Completed Courses: \n";
		for(int i = 0; i < completeCourses.size(); i++) {
			s += " -" + completeCourses.get(i).getCourseName() + "\n";
		}
		s += "Incompleted Courses: \n";
		for(int i = 0; i < incompleteCourses.size(); i++) {
			s += " -" + incompleteCourses.get(i).getCourseName() + "\n";
		}
		return s;
	}
	
}
