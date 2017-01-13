import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * <h1>World constructor</h1>
 * Creates and operates the World
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2017-01-08
 */
public class World {
	private ArrayList<String[]> questionList;
	private ArrayList<Book> bookList;
	private ArrayList<Room> roomList;
	private ArrayList<Course> courseList;
	private int keyAmount;
	
	/**
	 * Constructor for World
	 */
	public World() {
		this.keyAmount = 0;
		try {
			initWorld();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void initWorld() throws FileNotFoundException {
		this.questionList = initQuestions();
		this.bookList = initBooks();
		this.roomList = initRooms();
		this.courseList = initCourses();
		
		placeBooks();
		placeKeys();
		placeStudents();
		placeTeachers();
		placeSfinx();
	}
	
	private ArrayList<String[]> initQuestions() throws FileNotFoundException {
		Scanner q = new Scanner(new File("ext/questions"));
		ArrayList<String[]> questionList = new ArrayList<String[]>();
		while(q.hasNextLine()) {
			String[] str = q.nextLine().split(";");
			questionList.add(str);
		}
		q.close();
		return questionList;
	}

	private ArrayList<Book> initBooks() throws FileNotFoundException {
		Scanner b = new Scanner(new File("ext/books"));
		ArrayList<Book> bookList = new ArrayList<Book>();
		while(b.hasNextLine()) {
			String[] str = b.nextLine().split(";");
			bookList.add(new Book(str[0], str[1], str[2], Integer.parseInt(str[3])));
		}
		b.close();
		return bookList;
	}
	
	private ArrayList<Course> initCourses() throws FileNotFoundException {
		Scanner c = new Scanner(new File("ext/courses"));
		ArrayList<Course> courseList = new ArrayList<Course>();
		while(c.hasNextLine()) {
			String[] str = c.nextLine().split(";");
			courseList.add(new Course(str[0], str[1], Integer.parseInt(str[2])));
		}
		
		c.close();
		return courseList;
	}
	
	private ArrayList<Room> initRooms() throws FileNotFoundException {
		Scanner r = new Scanner(new File("ext/world"));
		ArrayList<Room> roomList = new ArrayList<Room>();
		
		while(r.hasNextLine()) {
			String[] str = r.nextLine().split(";");
			roomList.add(new Room(str[0]));
		}
		r.close();
		
		r = new Scanner(new File("ext/world"));
		for(int i = 0; i < roomList.size(); i++) {
			String[] str = r.nextLine().split(";");
			roomList.get(i).setNorthDir(roomConnector(1, str, roomList));
			roomList.get(i).setEastDir(roomConnector(2, str, roomList));
			roomList.get(i).setSouthDir(roomConnector(3, str, roomList));
			roomList.get(i).setWestDir(roomConnector(4, str, roomList));
			
			roomList.get(i).setNorthUnlock(toBool(str[5]));
			roomList.get(i).setEastUnlock(toBool(str[6]));
			roomList.get(i).setSouthUnlock(toBool(str[7]));
			roomList.get(i).setWestUnlock(toBool(str[8]));
			
			if(str[5].equals(" False")) {
				keyAmount++;
			}
			if(str[6].equals(" False")) {
				keyAmount++;
			}
			if(str[7].equals(" False")) {
				keyAmount++;
			}
			if(str[8].equals(" False")) {
				keyAmount++;
			}
		}
		r.close();
		return roomList;
	}
	
	private Room roomConnector(int i, String[] s, ArrayList<Room> roomList) {
		if(s[i].equals(" X")) {
			return null;
		} else {
			for(Room r : roomList) {
				if(r.getRoomName().equals(s[i].substring(1))) {
					return r;
				}
			}
		}
		return null;
	}
	
	private boolean toBool(String s) {
		boolean x;
		x = Boolean.parseBoolean(s.substring(1).toLowerCase());
		return x;
	}

	private void placeSfinx() {
		Random rand = new Random();
		int rngRoom = (rand.nextInt(roomList.size()));
		
		while(roomList.get(rngRoom).getRoomName().contains("Hallway") || maxOneDirection(rngRoom) == false) {
			rngRoom = (rand.nextInt(roomList.size()));
		}
		
		roomList.get(rngRoom).addCreature(new Sfinx());
	}
	
	private boolean maxOneDirection(int roomIdentifier) {
		int counter = 0;
		if(roomList.get(roomIdentifier).getNorthDir() != null) {
			counter++;
		}
		if(roomList.get(roomIdentifier).getEastDir() != null) {
			counter++;
		}
		if(roomList.get(roomIdentifier).getSouthDir() != null) {
			counter++;
		}
		if(roomList.get(roomIdentifier).getWestDir() != null) {
			counter++;
		}
		if(counter == 1) {
			return true;
		} else {
			return false;
		}
	}

	private void placeBooks() {
		Random rand = new Random();
		
		if(bookList.size() > 0) {
			ArrayList<Book> temp = new ArrayList<Book>(bookList);
			
			for(int i = 0; i < bookList.size() / 2; i++) {
				int rngBook = (rand.nextInt(temp.size()));
				int rngRoom = (rand.nextInt(roomList.size()));
				
				roomList.get(rngRoom).addItem(temp.get(rngBook));
				
				temp.remove(rngBook);
			}
		}
	}
	
	private void placeKeys() {
		Random rand = new Random();
		
		for(int i = 0; i < (keyAmount / 2) * 1.5; i++) {
			int rngRoom = (rand.nextInt(roomList.size()));
			
			roomList.get(rngRoom).addItem(new Key());
		}
	}
	
	private void placeStudents() {
		Random rand = new Random();
		
		for(int i = 0; i < roomList.size() / 2; i++) {
			int rngRoom = (rand.nextInt(roomList.size()));
			int rngCompleteCourse = (rand.nextInt(courseList.size()));
			int rngIncompleteCourse = (rand.nextInt(courseList.size()));
			String questionAnswer = "";
			Item bookToGive = new Item();
			
			while(rngCompleteCourse == rngIncompleteCourse) {
				rngIncompleteCourse = (rand.nextInt(courseList.size()));
			}
			
			for(int j = 0; j < questionList.size(); j++) {
				if(questionList.get(j)[0].equals(courseList.get(rngCompleteCourse).getCourseName())) {
					questionAnswer = questionList.get(j)[4];
				}
			}
			
			for(int k = 0; k < bookList.size(); k++) {
				if(bookList.get(k).toString().equals(courseList.get(rngCompleteCourse).getCourseBookName())) {
					bookToGive = bookList.get(k);
				}
			}
			
			roomList.get(rngRoom).addCreature(new Student(i+1, courseList.get(rngCompleteCourse), courseList.get(rngIncompleteCourse), bookToGive, questionAnswer));
		}
	}
	
	private void placeTeachers() {
		Random rand = new Random();
		ArrayList<Course> tempCourseList = new ArrayList<Course>(courseList);
		ArrayList<Room> tempRoomList = new ArrayList<Room>(roomList);
		String[] question = null;
		
		for(int i = 0; i < courseList.size(); i++) {
			int rngCourse = (rand.nextInt(tempCourseList.size()));
			int rngRoom = (rand.nextInt(tempRoomList.size()));
		
			while(tempRoomList.get(rngRoom).getRoomName().contains("Hallway")) {
				rngRoom = (rand.nextInt(tempRoomList.size()));
			}

			for(int j = 0; j < questionList.size(); j++) {
				if(tempCourseList.get(rngCourse).getCourseName().equals(questionList.get(j)[0])) {
					for(int k = 0; k < 5; k++) {
						question = questionList.get(j);
					}
				}
			}
			
			tempRoomList.get(rngRoom).addCreature(new Teacher(i+1, tempCourseList.get(rngCourse), question));
			tempCourseList.remove(rngCourse);
			tempRoomList.remove(rngRoom);
		}
	}
	
	/**
	 * Places the avatar in a Room which has a name with the phrase "Hallway" in it
	 * 
	 * @return Room r if such a Room is found, else null
	 */
	public Room placeAvatar() {
		for(Room r : roomList) {
			if(r.getRoomName().contains("Hallway")) {
				return r;
			}
		}
		return null;
	}
	
	/**
	 * Adds two (random) Courses to Avatars completeCourses
	 * 
	 * @return ArrayList courses the list to be returned
	 */
	public ArrayList<Course> getAvatarsCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		Random rand = new Random();
		
		int rngCourse1 = (rand.nextInt(courseList.size()));
		while(courseList.get(rngCourse1).getWorthHP() != 30) {
			rngCourse1 = (rand.nextInt(courseList.size()));
		}
		int rngCourse2 = (rand.nextInt(courseList.size()));
		while(rngCourse1 == rngCourse2 || courseList.get(rngCourse2).getWorthHP() != 30) {
			rngCourse2 = (rand.nextInt(courseList.size()));
		}
		
		courses.add(courseList.get(rngCourse1));
		courses.add(courseList.get(rngCourse2));
		
		return courses;
	}
	
	/**
	 * @param itemName The Item name to be searched
	 * @return int The Item weight value
	 */
	public int getItemWeight(String itemName) {
		for(Item i : bookList) {
			if(i.itemName.equals(itemName)) {
				return i.weight;
			}
		}
		return 0;
	}
	
}
