import java.util.ArrayList;

/**
 * <h1>Store constructor</h1>
 * Creates and controls <code>Register</code> instances.
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2016-12-30
 */
public class Store {

	private Register registers[];  // Array of registers
	
	/**
	 * Constructor for a <code>Store</code>.
	 */
	public Store() {
		this.registers = new Register[4];
		
		for(int i = 0 ; i < registers.length ; i++) {
			registers[i] = new Register();
		}
	}

	/**
	 * Adds the <code>length</code> of each <code>Queue</code> and divides the number by the amount of <code>Register</code>(s).
	 * @return int The average length of all Registers.
	 */
	public double getAverageQueueLength() {
		double queueLengths = 0;   // Variable for storing each registers queue length
		int openRegisters = 0;     // Variable for storing the amount of registers traversed
		
		for(Register r : this.registers) {
			if(r.isOpen()) {
				queueLengths += r.getQueueLength();
				openRegisters++;
			}
		}
		return queueLengths/openRegisters;
	}
	
	// Put a Customer c in the shortest Queue of a Register
	/**
	 * Sends a <code>Customer</code> to the shortest <code>Queue</code> of an open <code>Register</code>.
	 * 
	 * @param c A <code>Customer</code>.
	 */
	public void newCustomer(Customer c) {
		// Check if no registers are open and open one if none are
		if(!registers[0].isOpen()) {
			openNewRegister();
		}
		Register temp = this.registers[0];
		
		for(Register r : this.registers) {
			if(r.isOpen() && r.getQueueLength() < temp.getQueueLength()) {
				temp = r;
			}
		}
		temp.addToQueue(c);
	}

	/**
	 * Step one time-unit in every open <code>Register</code>.
	 */
	public void step() {
		for(Register r : this.registers) {
			if(r.isOpen()) {
				r.step();
			}
		}
	}
	
	/**
	 * Open a new <code>Register</code> if possible.
	 */
	public void openNewRegister() {
		// if array not full
		for(Register r : this.registers) {
			if(!r.isOpen()) {
				r.open();
				return;
			}
		}
	}
	
	// Return all Customers with empty baskets at the current time unit
	/**
	 * Create an <code>ArrayList</code> of (done) <code>Customer</code>(s) with 0 <code>groceries</code> left.
	 * 
	 * @return ArrayList <code>a</code>.
	 */
	public ArrayList<Customer> getDoneCustomers() {
		ArrayList<Customer> a = new ArrayList<Customer>();
		
		for(Register r : this.registers) {
			if(r.isOpen() && r.hasCustomer() && r.currentCustomerIsDone()) {
				a.add(r.removeCurrentCustomer());
			}
		}
		return a;
	}
	
	/**
	 * Runs all <code>Register</code>(s) through <code>Register.toString();</code>, and adds them to string <code>s</code>.
	 * 
	 * @return string The finished string s.
	 */
	public String toString() {
		String s = "";
		for(Register r : registers) {
			s += r.toString();
		}
		return s;
	}
}
