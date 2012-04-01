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
		this.mVertices = vertices;
	}

	public List<Edge> getEdges() {
		return mEdges;
	}

	public void setEdges(List<Edge> edges) {
		this.mEdges = edges;
	}
}
