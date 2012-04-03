package runner;

import graph.Graph;

/**
 *
 */
public class Week3 implements Runner {

	private void Week3() {
	}

	public void run() {
		long min = Integer.MAX_VALUE;
		long max = Integer.MIN_VALUE;
		long iterations = 40*40;

		while (iterations -- > 0) {
			System.out.println("******* iteration: " + iterations);
			Graph graph = Graph.createGraph();
			while (graph.getVertices().size() > 2) {
				graph = Graph.randomContraction(graph);
			}
			min = graph.getEdges().size() < min ? graph.getEdges().size() : min;
			max = graph.getEdges().size() > max ? graph.getEdges().size() : max;
		}

		System.out.println("min edges: " + min + ", max edges: " + max);

	}
}
