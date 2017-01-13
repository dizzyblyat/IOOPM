
public class RegisterTest {
	
	public static void main(String args[]) {
		// Test customers
		Customer c1 = new Customer(0, 1);
		Customer c2 = new Customer(0, 2);
		Customer c3 = new Customer(0, 1);
		Customer c4 = new Customer(0, 2);
		Register r = new Register();
		
		// Open register
		r.open();
		System.out.println("Register open: " + r.isOpen());
		// Add customers to queue
		r.addToQueue(c1);
		r.addToQueue(c2);
		r.addToQueue(c3);
		r.addToQueue(c4);
		System.out.println("Length of queue: " + r.getQueueLength());
		
		// First customer
		r.step();
		System.out.println("Length of queue: " + r.getQueueLength());
		// Second customer
		r.step();
		r.step();
		System.out.println("Length of queue: " + r.getQueueLength());
		// Third customer
		r.step();
		System.out.println("Length of queue: " + r.getQueueLength());
		// Fourth customer
		r.step();
		r.step();
		System.out.println("Length of queue: " + r.getQueueLength());
		
		// Trying to step when there are no customers in the queue
		r.step();
		
		// Close register
		r.close();
		System.out.println("Register open: " + r.isOpen());
	}
}
