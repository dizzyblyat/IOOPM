
/**
 * <h1>Register constructor</h1>
 * Creates and controls <code>Queue</code> instances.
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2016-12-30
 */
public class Register {

	private boolean open;  // True if Register is open
	private Queue queue;
	
	/**
	 * Constructor for a <code>Register</code>.
	 */
	public Register() {
		this.open = false;
		this.queue = new Queue();
	}
	
	/**
	 * Opens a <code>Register</code>.
	 */
	public void open() {
		this.open = true;
	}
	
	/**
	 * Closes a <code>Register</code>.
	 */
	public void close() {
		this.open = false;
	}
	
	/**
	 * Checks if a <code>Register</code> is open.
	 * 
	 * @return bool <code>True</code> if the <code>Register</code> is open, otherwise <code>False</code>.
	 */
	public boolean isOpen() {
		return open;
	}
	
	/**
	 * Removes a grocery from <code>groceries</code> from a <code>Customer</code> basket.
	 * 
	 * @exception EmptyQueueException if the <code>Queue</code> is empty.
	 */
	public void step() {
		try {
			if(hasCustomer()) {
				queue.first().serve();
			}
		} catch (EmptyQueueException e) {
			System.out.println("Nothing to serve");
		}
	}
	
	/**
	 * Check if <code>Register</code> has a <code>Customer</code>.
	 * 
	 * @return bool <code>True</code> if <code>Queue</code> has a <code>Customer</code>, otherwise <code>False</code>.
	 */
	public boolean hasCustomer() {
		return !queue.isEmpty();
	}
	
	/**
	 * Check if front-most <code>Customer</code> is done.
	 * 
	 * @return bool <code>True</code> if <code>Customer</code> is done, otherwise <code>False</code>.
	 */
	public boolean currentCustomerIsDone() {
		return queue.first().isDone();
	}
	
	/**
	 * Add a <code>Customer</code> at the end of a <code>Queue</code>.
	 * 
	 * If <code>Register</code> is not open, return a print saying "Register not open!".
	 * 
	 * @param c A <code>Customer</code>.
	 */
	public void addToQueue(Customer c) {
		if(isOpen()) {
			queue.enqueue(c);
		} else {
			System.out.println("Register not open!");
		}
	}
	
	/**
	 * Remove the first <code>Customer</code> in a <code>Queue</code>.
	 * 
	 * @return Customer The removed <code>Customer</code>.
	 */
	public Customer removeCurrentCustomer() {
		return queue.dequeue();
	}
	
	/**
	 * @return int The <code>length</code> of a <code>Queue</code>.
	 */
	public int getQueueLength() {
		return queue.getLength();
	}
	
	/**
	 * If a <code>Register</code> is closed, <code>"X [ ]"</code> is added to string <code>s</code>.
	 * 
	 * If a <code>Register</code> is open and has no <code>Customers</code>, <code>"  [ ]"</code> is added to string <code>s</code>.
	 * 
	 * If a <code>Register</code> is open and has <code>Customers</code>, <code>"  [n]x"</code> is added to string <code>s</code>
	 * (where "n" represents the amount of <code>groceries</code> a <code>Customer</code> has, and "x" represents the amount of
	 * <code>Customer</code>(s) in the <code>Queue</code>, which are represented by the character "@").
	 * 
	 * @return string The finished string s.
	 */
	public String toString() {
		String s = "";
		
		if(this.isOpen()) {
			if(!this.queue.isEmpty()) {
				s = "  [" + this.queue.first().getGroceries() + "]";
				
				for(int i = 0; i < this.queue.getLength(); i++) {
					s += "@";
				}
			} else {
				s = "  [ ]"; // Register open but no Customer(s)
			}
		} else {
			s += "X [ ]"; // Register closed
		}
		s += "\n";
		
		return s;
	}
}
