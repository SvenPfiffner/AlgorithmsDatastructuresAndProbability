import datastructures.SimpleGraph;
import graphs.Connection;

//Use this class to test functionality of the scripts
public class Main {
	
	static final int testIndex = 1;

	public static void main(String[] args) {
		switch(testIndex) {
		case 0: //Check if graph is fully connected
			System.out.println(testGraph().isConnected());
			break;
		case 1: //Find articulation vertices
			for(int i: Connection.articulationVerts(testGraph())) {
				System.out.print(i + " ");
			}
			System.out.print("\n");
			break;
		}
	}
	
	public static SimpleGraph testGraph() {
		SimpleGraph g = new SimpleGraph(4);
		g.connect(0, 1);
		g.connect(1, 2);
		g.connect(1, 3);
		g.connect(2, 3);
		return g;
	}

}
