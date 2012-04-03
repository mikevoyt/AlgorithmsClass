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
public class Week2 implements Runner {

	private void Week2() {
	}

	public void run() {

		List<String> lines = null;
		try {
			File file = new File("resources/QuickSort.txt");
			lines = Files.readLines(file, Charsets.UTF_8);
		} catch (IOException e) {
			System.out.println(e);
		}

		List<Integer> values = new ArrayList<Integer>();
		for (String line : lines) {
			values.add(Integer.parseInt(line));
		}

		List<Integer> sorted = Algorithm.quickSortWrapper(values, 0, values.size());
		System.out.println(sorted);

	}
}
