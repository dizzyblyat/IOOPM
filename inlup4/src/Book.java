
/**
 * <h1>Book constructor</h1>
 * Creates an Item Book
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2017-01-08
 */
public class Book extends Item { 
	private String bookAuthor;
	private String bookDate;
	
	/**
	 * Constructor for Book
	 * 
	 * @param bookName For storing the name of the Book
	 * @param bookAuthor For storing the Books author name
	 * @param bookDate For storing the year of the Books publishing
	 * @param weight For storing the weight of the Book
	 */
	public Book(String bookName, String bookAuthor, String bookDate, int weight) {
		this.itemName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookDate = bookDate;
		this.weight = weight;
	}
	
	/**
	 * Inspects a Book
	 * 
	 * @return String s containing the Books name, author, publishing year and weight
	 */
	public String inspect() {
		String s = "Book:\n" + itemName + "\n" + " -Author: " + bookAuthor + "\n" + " -Publish year: " + bookDate + "\n" + " -Weight: " + weight + "\n";
		return s;
	}
	
}
