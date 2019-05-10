package datastructures;

/**
 * This datastructure implements a connection in a graph by storing both the connections target location and its "cost"
 * @author Sven Pfiffner
 */
public class GraphConnection {
	final int target;
	int cost;
	
	public GraphConnection(int target, int cost) {
		//Set fields
		this.target = target;
		this.cost = cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}
	
	public int getTarget() {
		return target; 
	}
	
	
}