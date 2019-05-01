package datastructures;

import java.util.LinkedList;

/**
 * Datastructure for a basic undirected and unweighted graph
 * @author Sven Pfiffner
 */
public class SimpleGraph implements Graph{
	
	private LinkedList<Integer>[] adjList; //The graph is stored as an adjacency list
	private int edgeCount = 0; //Keeps track of the amount of edges in the graph
	
	/**
	 * Constructor
	 * @param n size of the graph (amount of vertices)
	 */
	@SuppressWarnings("unchecked")
	public SimpleGraph(int n) {
		adjList = new LinkedList[n];
		
		for(int i = 0; i<n; i++) {
			adjList[i] = new LinkedList<Integer>();
		}
	}
	
	/**
	 * Find all neighbours of x
	 * @param x the vertex we want the neighbours from
	 * @return a LinkedList of neighbour vertices
	 */
	public LinkedList<Integer> getNeighbours(int x) {
		return adjList[x];
	}
	
	/**
	 * Connect two given nodes with an edge
	 * @param x one vertex
	 * @param y other vertex
	 */
	public void connect(int x, int y) {
		edgeCount++;
		adjList[x].add(y);
		adjList[y].add(x);
	}
	
	/**
	 * Disconnect two given nodes by deleting the edge
	 * @param x one vertex
	 * @param y other vertex
	 * @return true if edge was deleted, false if no edge was present
	 */
	public boolean disconnect(int x, int y) {
		boolean hadEdge = (adjList[x].removeFirstOccurrence(y) && adjList[y].removeFirstOccurrence(x));
		edgeCount = (hadEdge)? edgeCount-1: edgeCount;
		return hadEdge;
	}
	
	/**
	 * Use breadth first search to check whether the graph is fully connected
	 * @return true if the graph is connected
	 */
	public boolean isConnected() {
		
		boolean[] markings = new boolean[this.getVertexCount()];
		
		//Start bfs
		bfs(0,markings);
		
		//Check for unmarked vertices
		for(boolean b: markings) {
			if(!b) {return false;}
		}
		
		return true;
	}
	
	/**
	 * Get the size of this graph by vertices
	 * @return int that stores the amount of vertices in the graph
	 */
	public int getVertexCount() {
		return adjList.length;
	}
	
	/**
	 * Get the size of this graph by edges
	 * @return int that stores the amount of edges in the graph
	 */
	public int getEdgeCount() {
		return edgeCount;
	}
	
	/**
	 * Recursive bfs that marks vertices it visits in a given array of booleans
	 */
	private void bfs(int source, boolean[] markings) {
		
		//Mark the source
		markings[source] = true;
		
		//Perform bfs on all unmarked neighbours
		for(int neighbour: adjList[source]) {
			if(!markings[neighbour]) {bfs(neighbour, markings);}
		}
		
	}
}
