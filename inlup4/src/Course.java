
/**
 * <h1>Course constructor</h1>
 * Creates and operates a Course
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2017-01-08
 */
public class Course {
	private String courseName;     // The Courses name
	private String courseBookName; // The name of the Book associated with the Course
	private int worthHP;           // The amount of HP the Course is worth
	
	/**
	 * Constructor for Course
	 * 
	 * @param courseName For storing the name of the Course
	 * @param courseBook For storing the name of the Book that is associated with the Course
	 * @param worthHP For storing the value of what the Course is worth in terms of HP
	 */
	public Course(String courseName, String courseBook, int worthHP) {
		this.courseName = courseName;
		this.courseBookName = courseBook;
		this.worthHP = worthHP;
	}

	/**
	 * @return int The worthHP
	 */
	public int getWorthHP() {
		return worthHP;
	}
	
	/**
	 * @return String courseName the course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName The courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return String courseBookName the name of a course book
	 */
	public String getCourseBookName() {
		return courseBookName;
	}

	/**
	 * @param courseBookName The courseBookName to set
	 */
	public void setCourseBook(String courseBookName) {
		this.courseBookName = courseBookName;
	}
	
}
