package graphs;

import java.util.Collections;
import java.util.LinkedList;

import datastructures.Edge;
import datastructures.Graph;

/**
 * This class implements the kargers mincut algorithm
 * @author Sven Pfiffner
 */
public class Kargers {
	
	/**
	 * Computes a mincut graph
	 * @param graph is the graph to scan
	 * @return The size of the mincut
	 */
	public static int getMinCutGraph(Graph graph) {
		
		//Get edgelist of of the given graph
		@SuppressWarnings("unchecked")
		LinkedList<Edge> edgeList = graph.getEdgeList();
		
		int out = edgeList.size();
		int vertCount = graph.getVertexCount();
		
		//Perform kargers algorithm with bootstrapping
		//Basically as described in the lecture and script
		
		for(int i = 0; i<vertCount*vertCount; i++) {
			out = Math.min(out, karger(edgeList, vertCount));
		}
		
		return out;
		
	}
	
	public static int karger(LinkedList<Edge> edgeList, int vertAmount) {
		Collections.shuffle(edgeList); //Randomly rearange the edgelist
		UFind unionStructure = new UFind(vertAmount); //Create a unionfind structure
		
		for(Edge edge: edgeList) { //Go trough each edge
			if(vertAmount <3) { //If we have only two vertices left
				break; //Break free from loop
			}
			
			int unionOfX = unionStructure.find(edge.one()); //Find union of vertex x
			int unionOfY = unionStructure.find(edge.other()); //Find union of vertex y
			
			if(unionOfX != unionOfY) { //If they are not in the same union (not contracted)
				unionStructure.union(edge.one(), edge.other()); //Contract
				vertAmount--; //We have less vertices now
			}
		}
		
		int out = 0; //This stores the edge amount of the cut (remaining edges after contraction)
		for(Edge edge: edgeList) { //Go trough each edge in list
			if(unionStructure.find(edge.one()) != unionStructure.find(edge.other())) { //If it was not contracted
				out++; //It counts towards the min cut
			}
		}
		
		return out; //Return output
	}
}

/**
 * This class implements a union find datastructure
 * @author Sven Pfiffner
 */
class UFind {
	
	public LinkedList<Integer> source;
	public LinkedList<Integer> rank;
	
	public UFind(int size) {
		
		//Set fields
		this.source = new LinkedList<Integer>(); 
		this.rank = new LinkedList<Integer>();
		
		for(int i=0; i<size; i++) {
			this.source.add(i); //Each node can be seen as a single union
			this.rank.add(0); //In which it is ranked 0
		}
	}
	
	/**
	 * Find the index value of the union a given vertex is in
	 * @param v the vertex to find
	 * @return int value of the union
	 */
	public int find(int v) {
		int s = source.get(v);
		if(v == s) {
			return v;
		} else {
			return find(s);
		}
	}
	
	/**
	 * Unites sets two given vertices are in
	 * @param x index of first vertex
	 * @param y index of second vertex
	 */
	public void union(int x, int y) {
		//Find the source of the given vertices unions
		int xSource = find(x);
		int ySource = find(y);
		
		if(xSource == ySource) return; //This means both are already in the same union
		
		int xRank = rank.get(xSource);
		int yRank = rank.get(ySource);
		
		//Make sure the vertex which has lower ranked is always added to the one with higher rank
		if(xRank > yRank) {
			source.set(ySource, xSource);
		} else if(yRank > xRank) {
			source.set(xSource, ySource);
		} else {
			source.set(ySource, xSource);
			//If this happens the rank of xSource is increased by 1
			rank.set(xSource, rank.get(xSource)+1);
		}
	}
	
}
