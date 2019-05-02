package datastructures;

/**
 * Simple Edge datastructure so we can store the graph as edgelist
 * @author Sven Pfiffner
 */
public class Edge {
	private int x;
	private int y;
	
	public Edge(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Edge clone() {
		return new Edge(x,y);
	}
	
	/**
	 * @return One vertex of the graph
	 */
	public int one() {
		return x;
	}
	
	/**
	 * @return Other vertex of the graph
	 */
	public int other() {
		return y;
	}
}
