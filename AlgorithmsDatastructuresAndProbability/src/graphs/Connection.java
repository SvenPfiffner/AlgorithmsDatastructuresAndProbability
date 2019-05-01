package graphs;

import java.util.LinkedList;

import datastructures.Graph;

public class Connection {
	
	/**
	 * Find all articulation vertices in a given graph
	 * @param g Graph to scan
	 * @return LinkedList that contains indices of all found articulation vertices (may be empty)
	 */
	public static LinkedList<Integer> articulationVerts(Graph g) {
		
		int graphSize = g.getVertexCount();
		
		LinkedList<Integer> artVerts = new LinkedList<Integer>();
		
		//Use some arrays to store important stuff
		int[] dfs = new int[graphSize]; //dfs-value of each node
		int[] degInTree = new int[graphSize]; //degree of each node in dfs-tree
		int[] low = new int[graphSize]; //low-value of each node
		boolean[] isArtVert = new boolean [graphSize]; //Is node a articulation node?
		
		//Visit the root node, the rest will follow recursively
		articulationDfsVisit(g, 0, 0, dfs, degInTree, low, isArtVert);
				
		//Root is always marked as articulation node
		//So we have to check wheter it really is
		isArtVert[0] = degInTree[0] > 1;
		
		for(int i = 0; i<isArtVert.length; i++) {
			if(isArtVert[i]) {artVerts.add(i);}
		}
		
		return artVerts;
	}
	
	/**
	 * Internal recursive function that is used to find articulation nodes
	 */
	private static int articulationDfsVisit(Graph g, int source, int num, int[] dfs, int[] degInTree, int[] low, boolean[] isArtVert) {
		num++; //We increase the current traversal number
		dfs[source] = num; //This number corresponds to current nodes dfs-value
		low[source] = dfs[source]; // And for now, it is also current nodes low-value
		
		//For each neighbor of the current node
		for(int neighbor: g.getNeighbours(source)) {
			//If said neighbor has not been visited by dfs yet
			if(dfs[neighbor] == 0) {
				//It will be a neighbor in the dfs tree, so we increase degrees
				degInTree[source] += 1;
				degInTree[neighbor] += 1;
				
				//We perform dfs from this neighbor and receive its low-value
				int lowOfNeighbor = articulationDfsVisit(g, neighbor, num, dfs, degInTree, low, isArtVert);
				//If found low-value is bigger than our low value
				if(lowOfNeighbor >= dfs[source]) {
					//Our node is an articulation vertex
					isArtVert[source] = true;
				}
				
				//Low of this vertex is either its current low or the low of a neighbor, whichever is smaller
				low[source] = Math.min(low[source], lowOfNeighbor);
			} else {
				low[source] = Math.min(low[source], dfs[neighbor]);
			}
		}
		
		//Return the currents node low-value
		return low[source];
	}
	
}
