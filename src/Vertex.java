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

	public void addEdge(Edge edge) {
		mEdges.add(edge);
	}


}
