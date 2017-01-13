import java.util.Random;

/**
 * <h1>Sfinx constructor</h1>
 * Creates and operates the Creature Sfinx
 * 
 * @author Damir Suvajac
 * @version 1.0
 * @since 2017-01-08
 */
public class Sfinx extends Creature {
	
	/**
	 * Constructor for Sfinx
	 */
	public Sfinx() {
		this.creatureName = "SFINX";
	}
	
	/**
	 * Picks one of 10 different lines (at random) to print when invoked
	 */
	public void talk() {
		Random rand = new Random();
		String[] str = new String[10];
		
		str[0] = "You never change things by fighting the existing reality. To change something, build a new model that makes the existing model obsolete.";
		str[1] = "Education is the most powerful weapon which you can use to change the world.";
		str[2] = "He that reckons without his host must reckon again.";
		str[3] = "When baffled in one direction a man of energy will not despair, but will find another way to his object.";
		str[4] = "Never believe that a few caring people can't change the world. For, indeed, that's all who ever have.";
		str[5] = "Consuming alcohol assists with removing deceit and revealing the truth.";
		str[6] = "It is supposed that if there is a rumour, there must be some truth behind it.";
		str[7] = "Plans are insulted destinies. I don't have plans, I only have goals.";
		str[8] = "When the tree falls, the monkeys scatter.";
		str[9] = "I am ERROR.";
		
		System.out.print(creatureName + ": ");
		System.out.println(str[rand.nextInt(str.length)]);
	}
	
	/**
	 * @return String s containing Creature name
	 */
	public String toString() {
		String s = creatureName;
		return s;
	}
}
