package graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Vertex implements Comparable<Vertex> {
	private String mLabel;
	private List<Edge> mEdges = new ArrayList<Edge>();
	private boolean mExplored = false;
	private long mDistance = Integer.MAX_VALUE;
	private Vertex mLeader;
	private long mFinishingTime;
	private Long mLeadingCount = new Long(0); //if this vertex is the leader, how many vertex's (including itself) it's leading


	public Long getLeadingCount() {
		return mLeadingCount;
	}

	public void setLeadingCount(long count) {
		mLeadingCount = count;
	}

	public void incrementLeadingCount() {
		mLeadingCount++;
	}

	public long getFinishingTime() {
		return mFinishingTime;
	}

	public void setFinishingTime(long finishingTime) {
		this.mFinishingTime = finishingTime;
	}

	public Vertex getLeader() {
		return mLeader;
	}

	public void setLeader(Vertex leader) {
		this.mLeader = leader;
	}

	public long getDistance() {
		return mDistance;
	}

	public void setDistance(long distance) {
		this.mDistance = distance;
	}

	public boolean isExplored() {
		return mExplored;
	}

	public void setExplored(boolean explored) {
		this.mExplored = explored;
	}

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

	@Override
	public String toString() {
		return mLabel + ": leader=" + getLeader().getLabel() + ", finishingTime=" + getFinishingTime() + ", leadingCount=" + getLeadingCount();
	}

	public int compareTo(Vertex v) {
		return getLeadingCount().compareTo(v.getLeadingCount());
	}
}
