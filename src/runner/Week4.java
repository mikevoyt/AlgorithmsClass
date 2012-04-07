package runner;

import graph.Graph;
import graph.Vertex;

import java.util.*;

/**
 *
 */
public class Week4 implements Runner {

	private void Week4() {
	}

	public void run() {

		long nodesProcessedSoFar = 0;
		Vertex currentSourceVertex = null;


		Graph graph = Graph.createGraphFromEdges("resources/SCC.txt");
		//Graph graph = Graph.createGraphFromEdges("resources/edges.txt");
		System.out.println("edges=" + graph.getEdges().size());

		for (int i = graph.getVertices().size()-1; i >= 0; i--) {
			Vertex v = graph.getVertices().get(i);
			if (v.isExplored()) {
				//System.out.println(v.getLabel() + " already explored");
				continue;
			} else {
				//System.out.println("exploring " + v.getLabel());
				Graph.DFS(graph, v, v, true);
			}
		}


		//for (Vertex v : graph.getVertices()) {
		//	System.out.println(v.toString());
		//}

		//reset explored & rearrange
		Vertex[] vertices = new Vertex[Graph.NODE_COUNT];
		for (int i = graph.getVertices().size()-1; i >= 0; i--) {
			Vertex v = graph.getVertices().get(i);
			v.setExplored(false);
			v.setLeadingCount(0);
			int finishingTime = (int)v.getFinishingTime();
			v.setLabel(String.format("%d", finishingTime));
			vertices[finishingTime-1] = v;
		}

		graph.setVertices(Arrays.asList(vertices));

		for (int i = graph.getVertices().size()-1; i >= 0; i--) {
			Vertex v = graph.getVertices().get(i);
			if (v.isExplored()) {
				//System.out.println(v.getLabel() + " already explored");
				continue;
			} else {
				//System.out.println("exploring " + v.getLabel());
				Graph.DFS(graph, v, v, false);
			}
		}

		Collections.sort(graph.getVertices());
		Collections.reverse(graph.getVertices());

		for (int i=0; i < 10; i++) {
			System.out.println(graph.getVertices().get(i));
		}
		//for (Vertex v : graph.getVertices()) {
	//		System.out.println(v.toString());
//		}


	}
}
