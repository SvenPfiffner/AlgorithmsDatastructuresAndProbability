import java.util.Arrays;
import java.util.Random;

import datastructures.SimpleGraph;

//Use this class to test functionality of the scripts
public class Main {

	public static void main(String[] args) {
		int size = 10;
		int[] data = randArray(size);
		SearchAndSort.Sort.bubbleSort(data);
		System.out.println("A: " + Arrays.toString(data));
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
		SimpleGraph g = new SimpleGraph(4);
		g.connect(0, 1);
		g.connect(1, 2);
		g.connect(1, 3);
		g.connect(2, 3);
		return g;
	}

}
