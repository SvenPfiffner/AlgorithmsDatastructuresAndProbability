package datastructures;

import java.util.LinkedList;

public interface Graph {
	
	public int getVertexCount(); //Get the size of the graph by vertices
	public LinkedList<Edge> getEdgeList();
	public LinkedList<Integer> getNeighbors(int x); //Get all the neighbours of a given vertex
	public int getEdgeCount(); //Get the size of the graph by edges
	public void connect(int x, int y); //Connect two nodes with an edge
	public boolean disconnect(int x, int y); //Disconnect two nodes with an edge
	
}
