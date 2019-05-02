import datastructures.SimpleGraph;
import graphs.Connection;
import probability.PiTargetShoot;

//Use this class to test functionality of the scripts
public class Main {

	public static void main(String[] args) {
		int a = 10000000;
		System.out.println(PiTargetShoot.computeParallel(a));
		System.out.println(PiTargetShoot.compute(a));
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
