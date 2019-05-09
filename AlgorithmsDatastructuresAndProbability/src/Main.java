import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

import SearchAndSort.Sort;
import datastructures.SimpleGraph;
import graphs.Coloring;
import graphs.Matching;
import probability.MillerRabin;

//Use this class to test functionality of the scripts
public class Main {

	public static void main(String[] args) {
		System.out.println(misc.DpFib.fib(50));
	}
	
	public static int[] randArray(int size) {
		int[] out = new int[size];
		Random rand = new Random();
		for(int i = 0; i<size; i++) {
			out[i] = rand.nextInt((int)Math.pow(size, 2));
		}
		return out;
	}
	
	public static SimpleGraph testGraph() {
		SimpleGraph g = new SimpleGraph(8);
		g.connect(0, 1);
		g.connect(0, 2);
		g.connect(0, 4);
		g.connect(0, 5);
		g.connect(1, 5);
		g.connect(1, 2);
		g.connect(1, 3);
		g.connect(2, 4);
		g.connect(2, 3);
		g.connect(3, 5);
		g.connect(3, 4);
		g.connect(4, 5);
		g.connect(6, 7);
		return g;
	}

}
