import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<String> lines = null;
		try {
			File file = new File("kargerAdj.txt");
			lines = Files.readLines(file, Charsets.UTF_8);
		} catch (IOException e) {
			System.out.println(e);
		}


		List<Vertex> mVertices = new ArrayList<Vertex>();
		List<Edge> mEdges = new ArrayList<Edge>();

		long start = System.currentTimeMillis();

		//first create all verticies
		for (String line : lines) {
			Vertex vertex = new Vertex();
			line = line.replaceFirst("\\s+", "");
			String[] vals = line.split("\\s+");
			String label = vals[0];
			vertex.setLabel(label);
			mVertices.add(vertex);
		}

		//now create all edges, and add edges to vertices
		for (String line : lines) {
			line = line.replaceFirst("\\s+", "");
			String[] vals = line.split("\\s+");
			String root = vals[0];
			Vertex rootVertex = mVertices.get(Integer.parseInt(root)-1);
			for (String neighbor: vals) {
				if (neighbor.equals(root)) continue;
				Vertex neighborVertex = mVertices.get(Integer.parseInt(neighbor)-1);
				if (neighborVertex.isConnectedTo(rootVertex)) continue;  //already connected

				//create new edge and set vertices
				Edge edge = new Edge();
				edge.setHead(rootVertex);
				edge.setTail(neighborVertex);

				//add to list of edges
				mEdges.add(edge);

				//link vertices back to new edge
				rootVertex.addEdge(edge);
				neighborVertex.addEdge(edge);
			}
		}


		long end = System.currentTimeMillis();


		System.out.println("time:" + (end-start) + " ms");

	}
}
