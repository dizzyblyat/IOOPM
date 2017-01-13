import static org.junit.Assert.*;

import org.junit.Test;


public class RegisterTest {
	Customer c1 = new Customer(0, 1);
	Customer c2 = new Customer(0, 2);
	Register r = new Register();

	@Test
	public void testOpen() {
		assertFalse(r.isOpen());
		
		r.open();
		
		assertTrue(r.isOpen());
	}

	@Test
	public void testClose() {
		r.close();
		
		assertFalse(r.isOpen());
		
		r.open();
		r.close();
		
		assertFalse(r.isOpen());
	}

	@Test
	public void testAddToQueue() {
		r.open();
		
		assertEquals(0, r.getQueueLength());
		
		r.addToQueue(c1);
		
		assertEquals(1, r.getQueueLength());
		
		r.addToQueue(c2);
		
		assertEquals(2, r.getQueueLength());
	}

	@Test
	public void testRemoveCurrentCustomer() {
		r.open();
		r.addToQueue(c1);
		r.addToQueue(c2);
		r.removeCurrentCustomer();
		
		assertEquals(1, r.getQueueLength());		
	}
	
	@Test
	public void testHasCustomer() {
		r.open();
		
		assertFalse(r.hasCustomer());
		
		r.addToQueue(c1);
		
		assertTrue(r.hasCustomer());
	}
	
	@Test
	public void testStep() {
		r.open();
		r.addToQueue(c1);
		r.addToQueue(c2);
		
		assertEquals(1, c1.getGroceries());
		
		r.step();
		
		assertEquals(0, c1.getGroceries());
		
	}
	
	@Test
	public void testCurrentCustomerIsDone() {
		r.open();
		r.addToQueue(c1);
		r.step();
		
		assertTrue(r.currentCustomerIsDone());
	}

}