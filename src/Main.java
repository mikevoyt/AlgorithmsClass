import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<String> lines = null;
		try {
			File file = new File("IntegerArrayShort.txt");
			//File file = new File("QuickSort.txt");
			lines = Files.readLines(file, Charsets.UTF_8);
		} catch (IOException e) {
			System.out.println(e);
		}

		List<Integer> values = new ArrayList<Integer>();
		for (String line : lines) {
			values.add(Integer.parseInt(line));
		}

		long start = System.currentTimeMillis();

		List<Integer> sorted = Algorithm.mergeSort(values);
		System.out.println(sorted);

		long end = System.currentTimeMillis();


		System.out.println("time:" + (end-start) + "ms");

	}
}
