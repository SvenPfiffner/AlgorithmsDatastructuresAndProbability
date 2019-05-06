package graphs;

import java.util.Arrays;
import java.util.LinkedList;

import datastructures.Graph;

/**
 * This class contains some coloring algorithms for graphs such
 * as to check if a given graph is bipartit
 * @author Sven Pfiffner
 */
public class Coloring {
	
	/**
	 * Checks whether a given graph is bipartit
	 * @param g the graph to check for bipartition
	 * @return true if g is bipartit, false if it isn't
	 */
	public static boolean isBipartit(Graph g) {
		return isBipartitRec(g, 0);
	}
	
	/**
	 * Checks recursively whether a given graph is bipartit
	 * @param g the graph to check for bipartition
	 * @param start the node at which we want to start checking
	 * @return true if g is bipartit, false if it isn't
	 */
	private static boolean isBipartitRec(Graph g, int start) {
		
		int[] color = new int[g.getVertexCount()]; //Stores node colors
		Arrays.fill(color, -1);
		LinkedList<Integer> queue = new LinkedList<Integer>(); //Used for bfs traversal
		
		//Start bfs from node 0
		queue.add(0);
		color[0] = 0;
		
		//Perform bfs on subgraph that contains node 0
		while(queue.size() > 0) {
			int source = queue.removeFirst();
			for(int node: g.getNeighbors(source)) {
				if(color[node] == -1) { //For each unvisited neighbor, give alternated color
					if(color[source] == 0) {
						color[node] = 1;
					}
					else {
						color[node] = 0; 
					}
					queue.add(node);
				}
				else if(color[node] == color[source]) { //If we find a visited node with same color as source
					return false; //No bipartition
				}
			}
		}
		
		for(int i = 0; i< color.length; i++) {
			if(color[i] == -1) {
				return isBipartitRec(g,i);
			}
		}
		return true;
	}

}
