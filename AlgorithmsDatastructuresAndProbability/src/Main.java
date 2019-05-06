import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

import datastructures.SimpleGraph;
import probability.MillerRabin;

//Use this class to test functionality of the scripts
public class Main {

	public static void main(String[] args) {
		//This 50 digit number is known to be prime
		BigInteger test = new BigInteger("52125697062031981959667707995340809347392556520339");
		int certainty = 5;
		System.out.println("Own Miller Rabin implementation: " + MillerRabin.isPrime(test, certainty));
		System.out.println("Java-Intern implementation: " + test.isProbablePrime(certainty));
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
