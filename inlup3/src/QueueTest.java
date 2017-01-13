import static org.junit.Assert.*;

import org.junit.Test;


public class QueueTest {
	Customer c1 = new Customer(0, 1);
	Customer c2 = new Customer(0, 2);
	Customer c3 = new Customer(0, 3);
	Customer c4 = new Customer(0, 4);
	Queue q = new Queue();

	@Test
	public void testQueue() {
		assertEquals(1, c1.getGroceries());
		assertEquals(2, c2.getGroceries());
		assertEquals(3, c3.getGroceries());
		assertEquals(4, c4.getGroceries());
		assertEquals(0, q.getLength());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(q.isEmpty());
	}

	@Test
	public void testEnqueue() {
		q.enqueue(c1);
		q.enqueue(c2);
		q.enqueue(c3);
		q.enqueue(c4);
		
		assertEquals(4, q.getLength());
	}

	@Test
	public void testDequeue() {
		q.enqueue(c1);
		q.enqueue(c2);
		q.enqueue(c3);
		q.enqueue(c4);
		
		q.dequeue();
		
		assertEquals(3, q.getLength());
		
		q.dequeue();
		
		assertEquals(2, q.getLength());
	}

	@Test
	public void testFirst() {
		q.enqueue(c1);
		q.enqueue(c2);
		q.enqueue(c3);
		q.enqueue(c4);
		
		assertEquals(c1, q.first());
		
		q.dequeue();
		
		assertEquals(c2, q.first());
	}

}