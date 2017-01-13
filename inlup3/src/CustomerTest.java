import static org.junit.Assert.*;

import org.junit.Test;


public class CustomerTest {
	Customer c = new Customer(0, 2);
	
	@Test
	public void testCustomer() {
		assertEquals(0, c.getBornTime());
		assertEquals(2, c.getGroceries());
	}

	@Test
	public void testServe() {
		c.serve();
		
		assertEquals(0, c.getBornTime());
		assertEquals(1, c.getGroceries());
	}

	@Test
	public void testIsDone() {
		c.serve();
		c.serve();
		
		assertTrue(c.isDone());
	}

}