import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Graph {
	private List<Vertex> mVertices;
	private List<Edge> mEdges;

	public Graph(List<Vertex> vertices, List<Edge> edges) {
		setVertices(vertices);
		setEdges(edges);
	}

	public List<Vertex> getVertices() {
		return mVertices;
	}

	public void setVertices(List<Vertex> vertices) {
		this.mVertices = new ArrayList<Vertex>(vertices);
	}

	public List<Edge> getEdges() {
		return mEdges;
	}

	public void setEdges(List<Edge> edges) {
		this.mEdges = new ArrayList<Edge>(edges);
	}

	public void contract(Vertex src, Vertex dest) {
		moveEdges(src, dest);
		mVertices.remove(src);
		System.out.println("removed vertex: " + src.getLabel());
	}

	/**
	 * moves all edges connected to this Vertex to dest
	 * @param dest
	 */
	public void moveEdges(Vertex src, Vertex dest) {
		for (Edge srcEdge : src.getEdges()) {
			if (srcEdge.getHead().equals(src)) {
				System.out.println("set edge head between " + srcEdge.getHead().getLabel() + " and " + srcEdge.getTail().getLabel() + " to " + dest.getLabel());
				srcEdge.setHead(dest);
			} else if (srcEdge.getTail().equals(src)) {
				System.out.println("set edge tail between " + srcEdge.getTail().getLabel() + " and " + srcEdge.getHead().getLabel() + " to " + dest.getLabel());
				srcEdge.setTail(dest);
			} else {
				System.out.println("Error: can't find this vertex in edge list, not adjacent!!!");
			}

			if (srcEdge.getHead().getLabel().equals(srcEdge.getTail().getLabel())) {
				mEdges.remove(srcEdge);
				dest.removeEdge(srcEdge);
				System.out.println("removed self-loop at: " + srcEdge.getHead().getLabel());
			} else {
				dest.addEdge(srcEdge);
			}
		}

	}

	public static Graph createGraph() {

		List<String> lines = null;
		try {
			File file = new File("resources/kargerAdj.txt");
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
