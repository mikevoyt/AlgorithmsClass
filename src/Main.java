import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		
		long min = Integer.MAX_VALUE;
		long max = Integer.MIN_VALUE;
		//long iterations = mVertices.size()*mVertices.size();

		long iterations = 40*40;

		while (iterations-- > 0) {
			System.out.println("******* iteration: " + iterations);
			Graph graph = Graph.createGraph();
			while (graph.getVertices().size() > 2) {
				graph = Algorithm.randomContraction(graph);
			}
			min = graph.getEdges().size() < min ? graph.getEdges().size() : min;
			max = graph.getEdges().size() > max ? graph.getEdges().size() : max;
		}


		long end = System.currentTimeMillis();

		System.out.println("min edges: " + min + ", max edges: " + max);

		System.out.println("time:" + (end-start) + " ms");

	}
	

}
