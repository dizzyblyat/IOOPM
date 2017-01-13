import java.util.ArrayList;
import java.util.Random;

/**
 * <h1>Simulation constructor</h1>
 * Creates and controls a <code>Store</code> instance.
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2016-12-30
 */
public class Simulation {

	private Store store;                   // Store to be Simulated
	private int time;                      // Amount of time units since Simulation started
	private int intensity;                 // Probability (in percent) of new Customer each time unit
	private int minGroceries;              // Min amount of groceries in a Customers basket
	private int maxGroceries;              // Max amount of groceries in a Customers basket
	private int thresholdForNewRegister;   // At what Queue length to open a new Register
	private Random random = new Random();  // New Random int
	
	private int servedCustomers = 0;
	private int maxWaitTime = 0;
	private double avgWaitTime = 0;
	private double totalWaitTime = 0;
	
	/**
	 * Constructor for Simulation.
	 * 
	 * @param intensity For storing the frequency (in %) of when a new <code>Customer</code> may seek an open <code>Register</code>.
	 * @param maxGroceries For storing the maximum amount of <code>groceries</code> a <code>Customer</code> can have.
	 * @param thresholdForNewRegister For storing the threshold number for when to open a new <code>Register</code>.
	 */
	public Simulation(int intensity, int maxGroceries, int thresholdForNewRegister) {
		store = new Store();
		this.time = 0;
		this.intensity = intensity;
		this.minGroceries = 1;
		this.maxGroceries = maxGroceries;
		this.thresholdForNewRegister = thresholdForNewRegister;
	}
	
	private int groceryRng() {
		// Randomize a number between minGroceries and maxGroceries
		int groceries = random.nextInt((maxGroceries - minGroceries) + 1) + minGroceries;
		return groceries;
	}
	
	private void customerStats() {
		ArrayList<Customer> a = store.getDoneCustomers();
		this.servedCustomers += a.size();
		
		for(int i = 0; i < a.size(); i++) {
			int temp = time - a.get(i).getBornTime();
			
			if(temp > maxWaitTime) {
				maxWaitTime = temp;
			}
			totalWaitTime += temp;
			
		}
		if(servedCustomers > 0) {
			avgWaitTime = totalWaitTime/servedCustomers;
		}
	}
	
	/**
	 * Adds 1 to <code>time</code>.
	 * 
	 * Adds a new <code>Customer</code> with parameters: <code>time</code>, random amount of <code>groceries</code>.
	 * 
	 * Opens a new <code>Register</code> if the average queue length is bigger than <code>threshold</code>.
	 * 
	 * Updates <code>customerStats();</code>.
	 * 
	 * Calls <code>step();</code> in the <code>Store</code>.
	 */
    public void step() {
    	// Step time one time unit
        ++time;
        
        // Add new customer at time-frame with a random amount of groceries
        if(random.nextInt(100) < intensity) {
            store.newCustomer(new Customer(time, groceryRng()));
        }
        
        // Open a new register if requirements are met
        if(store.getAverageQueueLength() > thresholdForNewRegister) {
            store.openNewRegister();
        }
        
        // Update customer statistics each step
        customerStats();
    	
    	// Step store one time unit
    	store.step();
    }

	/**
	 * Adds <code>"\n"</code>, <code>Store.toString();</code> and the
	 * <code>servedCustomers</code>, <code>maxWaitTime</code>, <code>avgWaitTime</code> to string <code>s</code>.
	 * 
	 * @return string The finished string s.
	 */
    public String toString() {
    	String s = "\n" + store.toString() +
    	"Customers served: " + servedCustomers + "\n" +
    	"Max Wait Time: " + maxWaitTime + "\n" +
    	"Avg Wait Time: " + (double)Math.round(avgWaitTime * 100d) / 100d + "\n";
        return s;
    }
}