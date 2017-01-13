
/**
 * <h1>Item</h1>
 * A class that Book and Key extend
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2017-01-08
 */
public class Item {
	protected String itemName;
	protected int weight;
	
	/**
	 * 
	 * @return int weight the Item weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * @return String s the Item name
	 */
	public String toString() {
		String s = itemName;
		return s;
	}
	
}
