
public class StoreTest {

	public static void main(String[] args) {
		// Initiate a new Store
		Store s = new Store();
		
		// Add 3 Customers
		s.newCustomer(new Customer(0,5));
		s.newCustomer(new Customer(0,4));
		s.newCustomer(new Customer(0,3));
		
		// Open another Register
		s.openNewRegister();
		
		// Add 3 more Customers
		s.newCustomer(new Customer(0,4));
		s.newCustomer(new Customer(0,3));
		s.newCustomer(new Customer(0,2));
		
		// Step through time and remove Customers that are done
		for(int i = 0 ; i < 12 ; i++) {
			s.step();
			s.getDoneCustomers();
			System.out.printf("%.2f\n", s.getAverageQueueLength());
		}
	}
	
}
