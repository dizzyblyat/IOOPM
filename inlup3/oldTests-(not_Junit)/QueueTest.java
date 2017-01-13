
public class QueueTest {
	
	public static void main(String args[]) {
		// Test customers
		Customer c1 = new Customer(0, 1);
		Customer c2 = new Customer(0, 2);
		Customer c3 = new Customer(0, 3);
		Customer c4 = new Customer(0, 4);
		Queue q = new Queue();
		
		// First enqueue
		q.enqueue(c1);
		// Second enqueue
		q.enqueue(c2);
		// Third enqueue
		q.enqueue(c3);
		// Fouth enqueue
		q.enqueue(c4);
		System.out.println("Length of queue: " + q.getLength() + " (after 4 enqueues)");
		
		// First dequeue
		q.dequeue();
		System.out.println("Length of queue: " + q.getLength());
		if(q.first() == c2) {
			System.out.println("Correct customer after first dequeue!");
		} else {
			System.out.println("Error after first dequeue.");
		}
		// Second dequeue
		q.dequeue();
		System.out.println("Length of queue: " + q.getLength());
		// Third dequeue
		q.dequeue();
		System.out.println("Length of queue: " + q.getLength());
		if(q.first() == c4) {
			System.out.println("Correct customer after third dequeue!");
		} else {
			System.out.println("Error after third dequeue.");
		}
		// Fourth dequeue
		q.dequeue();
		System.out.println("Length of queue: " + q.getLength());
		
        if(q.isEmpty()) {
            System.out.println("\nThe queue is empty after 4 enqueue/dequeue!");
        } else {
            System.out.println("\nThe queue is not empty, but it should be...");
        }
		
	}
}
