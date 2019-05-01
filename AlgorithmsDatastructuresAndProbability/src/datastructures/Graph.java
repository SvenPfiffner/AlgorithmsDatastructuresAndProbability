package datastructures;

import java.util.LinkedList;

public interface Graph {

	public int getVertexCount(); //Get the size of the graph by vertices
	public LinkedList<Integer> getNeighbours(int x); //Get all the neighbours of a given vertex
	public int getEdgeCount(); //Get the size of the graph by edges
	public void connect(int x, int y); //Connect two nodes with an edge
	public boolean isConnected(); //Check if graph is fully connected
	
}
