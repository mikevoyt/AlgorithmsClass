package runner;

import algorithm.Algorithm;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Week1 implements Runner {

	private void Week1() {
	}

	public void run() {
		List<String> lines = null;
		try {
			File file = new File("resources/IntegerArray.txt");
			lines = Files.readLines(file, Charsets.UTF_8);
		} catch (IOException e) {
			System.out.println(e);
		}

		List<Integer> values = new ArrayList<Integer>();
		for (String line : lines) {
			values.add(Integer.parseInt(line));
		}

		long start = System.currentTimeMillis();

		long inversions = Algorithm.countInversions(values);

		long end = System.currentTimeMillis();

		System.out.println("inversions:" + inversions);


		List<Integer> sorted = Algorithm.mergeSort(values);
		System.out.println(sorted);



	}
}
