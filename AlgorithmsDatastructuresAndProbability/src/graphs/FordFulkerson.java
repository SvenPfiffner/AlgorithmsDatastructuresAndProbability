package graphs;

import java.util.LinkedList;
import datastructures.WeightedDirectedGraph;

/**
 * This class implements the Ford Fulkerson max-flow algorithm
 * @author Sven Pfiffner
 */
public class FordFulkerson {
	
	private static WeightedDirectedGraph g;
	
	/**
	 * Computes the max flow on a given graph
	 * @param g the graph to scan
	 * @param source the source of the flow
	 * @param dest the sink of the flow
	 * @return maximum flow as an int value
	 */
	public static int maxFlow(WeightedDirectedGraph g, int source, int sink) {
		
		FordFulkerson.g = g;
		
		/*Create a residual graph and fill the residual graph with given
		*capacities in the original graph
		*/
		int[][] residualGraph = new int[g.getVertexCount()][g.getVertexCount()];
		for(int u = 0; u<g.getVertexCount(); u++) {
			for(int v = 0; v<g.getVertexCount(); v++) {
				residualGraph[u][v] = g.getConnectionCost(u, v);
			}
		}
		
		//This array is filled by bfs and stores the paths
		int[] precessor = new int[g.getVertexCount()];
		
		int maxFlow = 0; //No flow initially
		
		while(hasPath(residualGraph, source, sink, precessor)) {
			//Find the maximal flow through the found bfs path
			int pathFlow = Integer.MAX_VALUE;
			for(int v=sink; v!=source; v=precessor[v]) {
				int u = precessor[v];
				pathFlow = Math.min(pathFlow, residualGraph[u][v]);
			}
			
			// Update residual capacities
			for(int v = source; v != sink; v=precessor[v]) {
				int u = precessor[v];
				residualGraph[u][v] -= pathFlow;
				residualGraph[v][u] += pathFlow;
			}
			
			maxFlow += pathFlow;
		}
		
		//Return flow
		return maxFlow;
	}
	
	/**
	 * Checks if there is a path from source to sink in residual graph
	 * @param residualGraph The residual graph
	 * @param source The flows source
	 * @param sink The flows sink
	 * @param precessor Array that stores the precessor of each node
	 * @return true if there is a path
	 */
	private static boolean hasPath(int[][] residualGraph, int source, int sink, int[] precessor) {
		
		//Standard bfs
		boolean visited[] = new boolean[g.getVertexCount()];
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		visited[source] = true;
		precessor[source] = -1;
		
		while(queue.size()!=0) {
			int u = queue.poll();
			for(int v=0; v<g.getVertexCount(); v++) {
				if(!visited[v] && residualGraph[u][v] > 0) {
					queue.add(v);
					precessor[v] = u;
					visited[v] = true;
				}
			}
		}
		
		//Have we reached sink?
		return visited[sink];
	}
	
}
