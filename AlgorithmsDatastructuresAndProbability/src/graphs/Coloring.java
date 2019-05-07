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
	 * Colors the graph using greedy coloring (is guaranteed to color the graph with max[deg(x)+1])
	 * @param g the graph to color
	 * @return an array that stores the color of each vertex as an integer value
	 */
	public static int[] greedyColoring(Graph g) {
		//Initialize output array
		int[] out = new int[g.getVertexCount()];
		Arrays.fill(out, -1);
		
		out[0] = 0; //Assign first vertex
		
		//Used to flag available colors (we are guaranteed to have at most |V| colors)
		boolean used[] = new boolean[g.getVertexCount()];
		
		//Go through all nodes
		for(int n = 1; n<g.getVertexCount(); n++) {
			//Mark each neighbors color as not available
			for(int neighbor: g.getNeighbors(n)) {
				if(out[neighbor] != -1) {
					used[out[neighbor]] = true;
				}
			} 
			
			//Assign the first available color
			int color;
			for(color = 0; color<g.getVertexCount(); color++) {
				if(!used[color]) {break;}
			}
			
			out[n] = color;
			
			//Reset flags
			Arrays.fill(used, false);
		}
		return out;	
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
