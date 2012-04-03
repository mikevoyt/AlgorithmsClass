package graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Vertex {
	private String mLabel;
	private List<Edge> mEdges = new ArrayList<Edge>();

	public void setLabel(String label) {
		mLabel = label;
	}

	public String getLabel() {
		return mLabel;
	}
	
	public List<Edge> getEdges() {
		return mEdges;
	}

	public void addEdge(Edge edge) {
		mEdges.add(edge);
	}

	public void removeEdge(Edge edge) {
		mEdges.remove(edge);
	}

	public boolean isConnectedTo(Vertex dest) {
		for (Edge edge : mEdges) {
			if (edge.getHead().equals(dest) || edge.getTail().equals(dest)) return true;
		}
		return false;
	}


}
