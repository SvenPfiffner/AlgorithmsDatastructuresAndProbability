package datastructures;

import java.util.LinkedList;

public class WeightedDirectedGraph implements Graph{

	LinkedList<GraphConnection>[] adjList; //Stores the connections in the graph
	LinkedList<Edge> edgeList; //Stores the edges of the graph
	
	/**
	 * Constructor
	 * @param n size of the graph (amount of vertices)
	 */
	@SuppressWarnings("unchecked")
	public WeightedDirectedGraph(int n) {
		adjList = new LinkedList[n];
		edgeList = new LinkedList<Edge>();
		for(int i = 0; i<n; i++) {
			adjList[i] = new LinkedList<GraphConnection>();
		}
	}
	
	/**
	 * Get the size of this graph by vertices
	 * @return int that stores the amount of vertices in the graph
	 */
	@Override
	public int getVertexCount() {
		return adjList.length;
	}

	/**
	 * Get all the edges of this graph as a list
	 * @return LinkedList of edges
	 */
	@Override
	public LinkedList<Edge> getEdgeList() {
		LinkedList<Edge> out = new LinkedList<Edge>();
		
		for(Edge e: edgeList) {
			out.add(e.clone());
		}
		
		return out;
	}

	/**
	 * Find all neighbours of x
	 * @param x the vertex we want the neighbours from
	 * @return a LinkedList of neighbour vertices
	 */
	@Override
	public LinkedList<Integer> getNeighbors(int x) {
		LinkedList<Integer> out = new LinkedList<Integer>();
		
		for(GraphConnection c: adjList[x]) {
			out.add(c.target);
		}
		
		return out;
	}
	
	/**
	 * Find all connections that go away from x
	 * @param x the vertex we want the neighbours from
	 * @return a LinkedList of neighbour vertices
	 */
	public LinkedList<GraphConnection> getConnections(int x) {
		return adjList[x];
	}

	/**
	 * Get the size of this graph by edges
	 * @return int that stores the amount of edges in the graph
	 */
	@Override
	public int getEdgeCount() {
		// TODO Auto-generated method stub
		return edgeList.size();
	}

	/**
	 * Connect x and y with a directed edge (from x to y) with no cost
	 * @param x one vertex
	 * @param y other vertex
	 */
	@Override
	public void connect(int x, int y) {
		connect(x,y,0);
	}
	
	/**
	 * Connect x and y with a directed edge (from x to y)
	 * @param x one vertex
	 * @param y other vertex
	 * @param cost how much it should cost to traverse this edge
	 */
	public void connect(int x, int y, int cost) {
		adjList[x].add(new GraphConnection(y,cost));
		edgeList.add(new Edge(x,y));
	}

	/**
	 * Disconnect two given nodes by deleting the edge
	 * @param x one vertex
	 * @param y other vertex
	 * @return true if edge was deleted, false if no edge was present
	 */
	@Override
	public boolean disconnect(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}
