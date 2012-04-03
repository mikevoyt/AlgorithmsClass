import runner.Runner;
import runner.Week1;
import runner.Week2;
import runner.Week3;

public class Main {
	public static void main(String[] args) {

		long start = System.currentTimeMillis();

		Runner runner = new Week1();
		
		runner.run();

		long end = System.currentTimeMillis();

		System.out.println("time:" + (end-start) + " ms");

	}
	

}
