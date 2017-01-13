import static org.junit.Assert.*;

import org.junit.Test;


public class StoreTest {
	// Initiate a new Store
	Store s = new Store();

	@Test
	public void testStep() {
		// Add 3 Customers
		s.newCustomer(new Customer(0,3));
		s.newCustomer(new Customer(0,4));
		s.newCustomer(new Customer(0,3));
		// Open another Register
		s.openNewRegister();
		// Add 3 more Customers
		s.newCustomer(new Customer(0,4));
		s.newCustomer(new Customer(0,3));
		s.newCustomer(new Customer(0,2));
		int tempSize = 0;
		
		for(int i = 0; i < 10; i++) {
			s.step();
			tempSize += s.getDoneCustomers().size();
		}
		assertEquals(6, tempSize);
	}

	@Test
	public void testGetDoneCustomers() {
		// Add 3 Customers
		s.newCustomer(new Customer(0,2));
		// Open another Register
		s.openNewRegister();
		// Add 3 more Customers
		s.newCustomer(new Customer(0,1));
		
		for(int i = 0; i < 2; i++) {
			s.step();
			assertEquals(1, s.getDoneCustomers().size());
		}
	}

}