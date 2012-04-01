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
				System.out.println("set edge tail from " + srcEdge.getTail().getLabel() + " and " + srcEdge.getHead().getLabel() + " to " + dest.getLabel());
				srcEdge.setTail(dest);
			} else {
				System.out.println("Error: can't find this vertex in edge list, not adjacent!!!");
			}

			if (srcEdge.getHead().equals(srcEdge.getTail())) {
				mEdges.remove(srcEdge);
				System.out.println("removed self-loop at: " + srcEdge.getHead().getLabel());
			}
		}

	}
}
