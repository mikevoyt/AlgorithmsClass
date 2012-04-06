package runner;

import graph.Graph;

/**
 *
 */
public class Week4 implements Runner {

	private void Week4() {
	}

	public void run() {
		Graph graph = Graph.createGraphFromEdges();
		System.out.println("edges=" + graph.getEdges().size());
	}
}
