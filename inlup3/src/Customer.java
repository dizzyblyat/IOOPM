
/**
 * <h1>Customer constructor</h1>
 * Creates a <code>Customer</code> item.
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2016-12-30
 */
public class Customer {

	private int bornTime;   // Time when Customer spawned
	private int groceries;  // Amount of groceries in Customer basket

	/**
	 * Constructor for Customer.
	 * 
	 * @param bornTime For storing the (random) born time of a <code>Customer</code>.
	 * @param groceries For storing the amount of (random) groceries a <code>Customer</code> has.
	 */
	public Customer(int bornTime, int groceries) {
		this.bornTime = bornTime;
		this.groceries = groceries;
	}
	
	/**
	 * Subtracts a grocery from <code>groceries</code> from a <code>Customer</code>.
	 */
	public void serve() {
		groceries--;
	}
	
	/**
	 * Check if the <code>Customer</code> is done by comparing <code>groceries</code> with 0.
	 * 
	 * @return bool This returns <code>True</code> if <code>groceries</code> are 0, otherwise <code>False</code>.
	 */
	public boolean isDone() {
		return groceries == 0;
	}
	
	/**
	 * @return int bornTime of <code>Customer</code>.
	 */
	public int getBornTime() {
		return bornTime;
	}
	
	/**
	 * @return int Current <code>groceries</code> amount.
	 */
	public int getGroceries() {
		return groceries;
	}
}