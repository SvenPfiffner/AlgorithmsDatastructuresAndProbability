package graphs;

import java.util.LinkedList;
import java.util.ListIterator;

import datastructures.Edge;
import datastructures.Graph;

/**
 * This class implements algorithms for finding inclusion and cardinality maximum matchings
 * @author Sven Pfiffner
 */
public class Matching {

	/**
	 * Finds an inclusion maximal matching in a given graph
	 * @param g the graph to scan
	 * @return a linkedlist of edges that are contained in the matching
	 */
	public static LinkedList<Edge> incMaxMatch(Graph g) {
		
		LinkedList<Edge> graphEdges = g.getEdgeList();
		LinkedList<Edge> out = new LinkedList<Edge>();
		
		while(true) {
			if(graphEdges.size() == 0) {break;}
			
			Edge e = graphEdges.get(0);
			out.add(e);
			//Remove all incident edges from pool
			removeIncident(graphEdges, e);
		}
		
		//Return matching
		return out;
		
	}
	
	/**
	 * Find the size of a maximum matching in a bipartit graph 
	 * @param g the graph to scan
	 * @return the size of the maximum matching as an integer
	 */
	public static int maxBipartitMatch(Graph g) {
		//TODO: Implement
		return 0;
	}
	
	/**
	 * Removes all edges incident to a given edge from a list
	 * @param edgeList the list to traverse
	 * @param e the edge to check incident edges for
	 */
	private static void removeIncident(LinkedList<Edge> edgeList, Edge e) {
		ListIterator<Edge> iter = edgeList.listIterator();
		
		while(iter.hasNext()) {
			Edge e1 = iter.next();
			if(e1.one() == e.one() || e1.one() == e.other()) {iter.remove();}
			else if(e1.other() == e.one() || e1.other() == e.other()) {iter.remove();}	
		}
	}
	
	
	
}
