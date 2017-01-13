
public class Simulator{

    public static void main(String[] args) throws InterruptedException{
        int steps = 100;
        int customerIntensity = 75;
        int maxGroceries = 7;
        int registerThreshold = 3;
        
        Simulation s = new Simulation(customerIntensity, maxGroceries, registerThreshold); // TODO: Add parameters!
        
        for(int i = 0; i < steps; i++){
            System.out.print("\033[2J\033[;H");
            s.step();
            System.out.println(s);
            Thread.sleep(500);
        }
        System.out.println("");
        System.out.println("Simulation finished!");
    }
}