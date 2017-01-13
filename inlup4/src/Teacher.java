import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * <h1>Teacher constructor</h1>
 * Creates and operates a Creature Teacher
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2017-01-08
 */
public class Teacher extends Creature {
	private Course course;
	private String[] question;
	private Scanner inputReaderTemp;
	
	/**
	 * Constructor for Teacher
	 * 
	 * @param i int to be added to the String of the Teacher name
	 * @param course For storing the Course that the Teacher is holding
	 * @param question For storing the String[] with the Teachers question and answers to said question
	 */
	public Teacher(int i, Course course, String[] question) {
		this.creatureName = "Teacher" + i;
		this.course = course;
		this.question = question;
	}
	
	/**
	 * Picks one of 4 different lines (at random) to print when invoked, or
	 * Teacher asks Avatar if they want to enroll the Course the Teacher is holding
	 * 
	 * @param completeCourses The Students complete courses
	 * @param incompleteCourses The Students incomplete courses
	 */
	public void talk(ArrayList<Course> completeCourses, ArrayList<Course> incompleteCourses) {
		boolean courseFound = false;
		for(Course c : completeCourses) {
			if(c.getCourseName().equals(course.getCourseName())) {
				courseFound = true;
				break;
			}
		}
		
		if(!courseFound) {
			for(Course c : incompleteCourses) {
				if(c.getCourseName().equals(course.getCourseName())) {
					courseFound = true;
				}
			}
		}
		
		if(courseFound) {
			Random rand = new Random();
			String[] str = new String[4];
			
			str[0] = "Fly, you fools!";
			str[1] = "I am busy...";
			str[2] = "I don't have time.";
			str[3] = "Don't talk to me.";
			
			System.out.print(creatureName + ": ");
			System.out.println(str[rand.nextInt(str.length)]);
		} else {
			System.out.println(creatureName + ": Do you wish to enroll in \"" + course.getCourseName() + "\"?");
		}
	}
	
	/**
	 * The Teacher asks their question and supplies 3 answers (2 if Avatar has correct Book), only one is the right one
	 * 
	 * @param hasBook boolean True if Avatar has the book for the Course, else False
	 * @return boolean isCorrect True if Avatar answers the question right, else False
	 */
	public boolean askQuestion(boolean hasBook) {
		Random rand = new Random();
		boolean isCorrect = false;
		boolean notYetAnswered = true;
		inputReaderTemp = new Scanner(System.in);
		String input;
		String answerA = null;
		String answerB = null;
		String answerC = null;
		ArrayList<Integer> intList = new ArrayList<Integer>();
		int[] rngInt;
		rngInt = new int[3];
		
		intList.add(2);
		intList.add(3);
		intList.add(4);
				
		if(hasBook == false) {
			for(int i = 0; i < 3; i++) {
				int temp = (rand.nextInt(intList.size()));
				rngInt[i] = intList.get(temp);
				intList.remove(temp);
			}
			
			
		} else if(hasBook == true){
			for(int i = 0; i < 2; i++) {
				int temp = (rand.nextInt(intList.size()));
				rngInt[i] = intList.get(temp);
				intList.remove(temp);
			}
			if(rngInt[0] != 4 && rngInt[1] != 4) {
				rngInt[1] = 4;
			}
		}
		
		answerA = question[rngInt[0]];
		answerB = question[rngInt[1]];
		answerC = question[rngInt[2]];
		
		System.out.println(creatureName + ": Hey you! It's time to examine you.");
		while(notYetAnswered) {
			System.out.println(question[1]);
			if(hasBook) {
				System.out.println("A: " + answerA);
				System.out.println("B: " + answerB);
			} else {
				System.out.println("A: " + answerA);
				System.out.println("B: " + answerB);
				System.out.println("C: " + answerC);
			}
			System.out.print(": ");
			input = inputReaderTemp.nextLine();
			String parsedInput[] = input.split(" ");
			switch(parsedInput[0].toLowerCase()) {
			
			case "a":
				if(answerA.equals(question[4])) {
					isCorrect = true;
					System.out.println("Congratulations! \"" + question[4] + "\" was the correct answer!");
				} else {
					isCorrect = false;
					System.out.println("NO! That's the wrong answer!");
				}
				notYetAnswered = false;
				break;
				
			case "b":
				if(answerB.equals(question[4])) {
					isCorrect = true;
					System.out.println("Congratulations! \"" + question[4] + "\" was the correct answer!");
				} else {
					isCorrect = false;
					System.out.println("NO! That's the wrong answer!");
				}
				notYetAnswered = false;
				break;
				
			case "c":
				if(hasBook) {
					System.out.println("Please pick between \"A\" and \"B\".");
					break;
				} else if(answerC.equals(question[4])) {
					isCorrect = true;
					System.out.println("Congratulations! \"" + question[4] + "\" was the correct answer!");
				} else {
					isCorrect = false;
					System.out.println("NO! That's the wrong answer!");
				}
				notYetAnswered = false;
				break;
				
			default:
				if(hasBook) {
					System.out.println("Please pick between \"A\" and \"B\".");
				} else {
					System.out.println("Please pick between \"A\", \"B\" and \"C\".");
				}
			}
		}
		return isCorrect;
	}
	
	/**
	 * Returns the Teachers Course
	 * 
	 * @param courseName The name of the course
	 * @return Course The Course to be returned
	 */
	public Course returnCourse(String courseName) {
		if(course.getCourseName().toLowerCase().equals(courseName)) {
			return course;
		}
		return null;
	}
	
	/**
	 * @return String the name of the course
	 */
	public String returnCourseName() {
		return this.course.getCourseName();
	}
	
	/**
	 * @return String the name of the course Book
	 */
	public String returnBookName() {
		return this.course.getCourseBookName();
	}
}
