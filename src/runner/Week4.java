package runner;

import graph.Graph;
import graph.Vertex;

/**
 *
 */
public class Week4 implements Runner {

	private void Week4() {
	}

	public void run() {
		//Graph graph = Graph.createGraphFromEdges("resources/SCC.txt");
		Graph graph = Graph.createGraphFromEdges("resources/edges.txt");
		System.out.println("edges=" + graph.getEdges().size());

		for (int i=0; i<graph.getVertices().size(); i++) {
			Vertex v = graph.getVertices().get(i);
			if (v.isExplored()) {
				System.out.println(v.getLabel() + " already explored");
				continue;
			} else {
				System.out.println("exploring " + v.getLabel());
				Graph.DFS(graph, v, false);
			}
		}

	}
}
