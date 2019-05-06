package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import datastructures.Edge;
import datastructures.Graph;
import datastructures.SimpleGraph;

/**
 * This class implements some common methods to compute paths in graphs
 * @author Sven Pfiffner
 */
public class Paths {
	
	/**
	 * Checks whether a given graph contains an euler tour
	 * @param g the graph to scan
	 * @return true if euler tour exists, false if not
	 */
	public static boolean hasEulerTour(Graph g) {
		//Go through each node and check if degree is even
		for(int i = 0; i<g.getVertexCount(); i++) {
			if(g.getNeighbors(i).size() % 2 != 0) {
				return false;
			}
		}
		
		//Check if graph is fully connected
		return g.isConnected();
	}
	
	/**
	 * Scans a given simplegraph for euler tour
	 * @param g the graph to scan
	 * @return first found euler tour as linkedlist of nodes, null if no eulertour in the graph
	 */
	public static LinkedList<Integer> getEulerTour(SimpleGraph g) {
		if(!hasEulerTour(g)) {return null;}
		
		GraphEdges graphEdges = new GraphEdges(g);
		
		//Walk tour from start
		LinkedList<Integer> tour = walkTour(graphEdges, 0);
		
		/*
		 * 'Turtle' traverses tour, if it finds a node that is no dead end it tells the program to walk a tour from there
		 * and adds this sub tour to the main tour
		 */
		int turtlePos = 0;
		ListIterator<Integer> iter = tour.listIterator();
		while(iter.hasNext()) {
			turtlePos = iter.next();
			if(!graphEdges.isDeadEnd(turtlePos)) {
				for(int i: walkTour(graphEdges, turtlePos)) {
					iter.add(i);
				}
			}
		}

		tour.add(0, 0);
		return tour;
	}
	
	private static LinkedList<Integer> walkTour(GraphEdges g, int start) {
		
		if(g.isDeadEnd(start)) {return new LinkedList<Integer>();}
		
		LinkedList<Integer> tour = new LinkedList<Integer>();
		int currentPos = start;
		
		do {
			currentPos = g.traverseEdge(currentPos);
			tour.add(currentPos);
		} while(currentPos != start);
		
		return tour;
	}
}

/**
 * This datastructure allows access to edges from nodes in near constant time. The idea is that
 * an array stores for each vertex of the array which edges are incident. This way we can get unused edges incident to a given vertex in constant time
 * @author Sven Pfiffner
 */
class GraphEdges {
	ArrayList<Edge>[] vertices;
	
	/**
	 * Constructor for the datastructure
	 * @param g the graph that serves as a blueprint
	 */
	@SuppressWarnings("unchecked")
	public GraphEdges(SimpleGraph g) {
		this.vertices = new ArrayList[g.getVertexCount()];
		//For each vertex in the graph
		for(int i = 0; i<g.getVertexCount(); i++) {
			if(vertices[i] == null) {
				vertices[i] = new ArrayList<Edge>();
			}
			//Add incident edges to lists only if neighbor is bigger than i.
			for(int neighbor: g.getNeighbors(i)) {
				if(i < neighbor) {
					Edge e = new Edge(i,neighbor);
					vertices[i].add(e);
					if(vertices[neighbor] == null) {
						vertices[neighbor] = new ArrayList<Edge>();
					}
					vertices[neighbor].add(e);
				}
			}
		}
	}
	
	/**
	 * Checks whether a given node is a dead end (has no unused edges incident)
	 * @param n the node to check
	 * @return true if it is, false if not
	 */
	public boolean isDeadEnd(int n) {
		return (vertices[n].size() == 0);
	}
	
	/**
	 * Traverse first unused edge found incident to a given vertex
	 * @param from the start vertex
	 * @return new position as vertex index, -1 if no edge was found
	 */
	public int traverseEdge(int from) {
		//Get first non used edge if there are still edges
		if(vertices[from].size() > 0) {
			Edge traversedEdge = vertices[from].remove(0);
			int newPos;
			if(traversedEdge.one() != from) {newPos = traversedEdge.one();}
			else {newPos = traversedEdge.other();}
			//By the nature of our structure, we have to delete the other direction too
			vertices[newPos].remove(traversedEdge);
			return newPos;
		}
		return -1;
	}
}
