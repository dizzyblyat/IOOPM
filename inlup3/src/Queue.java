
/**
 * <h1>Queue constructor</h1>
 * A FIFO <code>Queue</code> that handles <code>Customer</code>(s).
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2016-12-30
 */
public class Queue {

	/**
	 * <h1>Node constructor</h1>
	 * Creates a <code>Node</code> that stores a <code>Customer</code> and a pointer to the previous <code>Node</code>.
	 */
	private class Node {
		private Customer element;  // This Customer in Queue
		private Node next;         // Points to Node behind current Node
		
		/**
		 * Constructor for Node.
		 * 
		 * @param element <code>Customer</code> to be stored in the <code>Node</code>.
		 */
		public Node(Customer element) {
			this.element = element;
			this.next = null;
		}
	}
	
	private Node first;            // Node containing the first element
	private Node last;             // Node containing the last element
	private int length;            // Storing the length of the Queue
	
	/**
	 * Constructor for a <code>Queue</code>.
	 */
	public Queue() {
		this.length = 0;
		this.first = null;
		this.last = null;
	}
	
	/**
	 * Check if <code>Queue</code> is empty.
	 * 
	 * @return bool <code>True</code> if <code>Queue</code> is empty, otherwise <code>False</code>.
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	/**
	 * Adds a <code>Customer</code> at the end of the <code>Queue</code>.
	 * 
	 * @param c A <code>Customer</code>.
	 */
	public void enqueue(Customer c) {
		Node oldlast = last;
		last = new Node(c);
		
		if(isEmpty()) {
			first = last;
		} else {
			oldlast.next = last;
		}
		length++;
	}
	
	/**
	 * Removes the <code>Customer</code> at the start of the <code>Queue</code>.
	 * 
	 * @return Customer The <code>Customer</code> that was removed.
	 */
	public Customer dequeue() {
		if(isEmpty()) {
			throw new EmptyQueueException();
		}
		
		Customer element = first.element;
		first = first.next;
		length--;
		
		if(isEmpty()) {
			last = null;
		}
		return element;
	}
	
	/**
	 * Finds first <code>Customer</code> in <code>Queue</code>.
	 * 
	 * @return Customer The <code>Customer</code> at the start of the <code>Queue</code>.
	 */
	public Customer first() {
		if(isEmpty()) {
			throw new EmptyQueueException();
		} else {
			return first.element;
		}
	}
	
	
	/**
	 * @return int Current <code>length</code> of the <code>Queue</code>.
	 */
	public int getLength() {
		return length;
	}
}