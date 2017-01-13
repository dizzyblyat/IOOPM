import java.util.Random;

/**
 * <h1>Student constructor</h1>
 * Creates and operates a Creature Student
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2017-01-08
 */
public class Student extends Creature {
	private Course completeCourse;
	private Course incompleteCourse;
	private Item bookToGive;
	private String answerToCourse;
	private boolean tradeable;

	/**
	 * Constructor for Student
	 * 
	 * @param i int to be added to the String of the Student name
	 * @param completeCourse For storing the Students completed Course
	 * @param incompleteCourse For storing the Students incompleted Course
	 * @param bookToGive For storing a Book to the Student
	 * @param answerToCourse For storing a String to the Student
	 */
	public Student(int i, Course completeCourse, Course incompleteCourse, Item bookToGive, String answerToCourse) {
		this.creatureName = "Student" + i;
		this.completeCourse = completeCourse;
		this.incompleteCourse = incompleteCourse;
		this.bookToGive = bookToGive;
		this.answerToCourse = answerToCourse;
		this.tradeable = true;
	}
	
	/**
	 * Picks one of 4 different lines (at random) to print when invoked
	 */
	public void talk() {
		Random rand = new Random();
		String[] str = new String[4];
		
		str[0] = "Hey! Can you help me with \"" + this.incompleteCourse.getCourseName() + "\"?\n"
				+ "I can help you with \"" + this.completeCourse.getCourseName() + "\" in return!";
		str[1] = "Unless you want to help me with \"" + this.incompleteCourse.getCourseName() + "\" get lost.\n"
				+ "I can help you with \"" + this.completeCourse.getCourseName() + "\" if you do.";
		str[2] = "Ughh I hate \"" + this.incompleteCourse.getCourseName() + "\" SO MUCH! \n"
				+ "Help me out and i'll help \"" + this.completeCourse.getCourseName() + "\" in return!";
		str[3] = "I need: \"" + this.incompleteCourse.getCourseName() + "\".\n"
				+ "I can aid with: \"" + this.completeCourse.getCourseName() + "\". Trade?";
		
		System.out.print(this.creatureName + ": ");
		System.out.println(str[rand.nextInt(str.length)]);
	}
	
	/**
	 * @return String The name of the Students incomplete Course Book
	 */
	public String wantedBookName() {
		return this.incompleteCourse.getCourseBookName();
	}
	
	/**
	 * @return Item bookToGive
	 */
	public Item getBookToGive() {
		return this.bookToGive;
	}
	
	/**
	 * @return int the weight of bookToGive
	 */
	public int getBookToGiveWeight() {
		return this.bookToGive.weight;
	}
	
	/**
	 * @return String answerToCourse 
	 */
	public String getAnswerToCourse() {
		String answer = this.answerToCourse;
		this.answerToCourse = null;
		return answer;
	}

	/**
	 * @return boolean True if Student is allowed to trade, else False
	 */
	public boolean isTradeable() {
		return this.tradeable;
	}

	/**
	 * @param tradeable boolean True if Student is allowed to trade, else False
	 */
	public void setTradeable(boolean tradeable) {
		this.tradeable = tradeable;
	}
	
	/**
	 * @return String courseName of completeCourse
	 */
	public String getCompleteCourseName() {
		return this.completeCourse.getCourseName();
	}

}
