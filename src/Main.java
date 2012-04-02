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
			Graph graph = createGraph();
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
	
	private static Graph createGraph() {

		List<String> lines = null;
		try {
			//File file = new File("smallgraph.txt");
			File file = new File("kargerAdj.txt");
			lines = Files.readLines(file, Charsets.UTF_8);
		} catch (IOException e) {
			System.out.println(e);
		}

		List<Vertex> vertices = new ArrayList<Vertex>();
		List<Edge> edges = new ArrayList<Edge>();

		//first create all verticies
		for (String line : lines) {
			Vertex vertex = new Vertex();
			line = line.replaceFirst("\\s+", "");
			String[] vals = line.split("\\s+");
			String label = vals[0];
			vertex.setLabel(label);
			vertices.add(vertex);
		}

		//now create all edges, and add edges to vertices
		for (String line : lines) {
			line = line.replaceFirst("\\s+", "");
			String[] vals = line.split("\\s+");
			String root = vals[0];
			Vertex rootVertex = vertices.get(Integer.parseInt(root)-1);
			for (String neighbor: vals) {
				if (neighbor.equals(root)) continue;
				Vertex neighborVertex = vertices.get(Integer.parseInt(neighbor)-1);
				if (neighborVertex.isConnectedTo(rootVertex)) continue;  //already connected

				//create new edge and set vertices
				Edge edge = new Edge();
				edge.setHead(rootVertex);
				edge.setTail(neighborVertex);

				//add to list of edges
				edges.add(edge);

				//link vertices back to new edge
				rootVertex.addEdge(edge);
				neighborVertex.addEdge(edge);
			}
		}

		Graph graph = new Graph(vertices, edges);
		return graph;
	}
}
