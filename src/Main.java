import runner.*;

public class Main {
	public static void main(String[] args) {

		long start = System.currentTimeMillis();

		Runner runner = new Week4();
		
		runner.run();

		long end = System.currentTimeMillis();

		System.out.println("time:" + (end-start) + " ms");

	}
	

}
