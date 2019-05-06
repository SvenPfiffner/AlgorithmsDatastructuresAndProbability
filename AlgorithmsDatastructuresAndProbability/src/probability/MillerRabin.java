package probability;

import java.math.BigInteger;
import java.util.Random;

/**
 * This class implements the Miller-Rabin primality test
 * @author Sven Pfiffner
 */
public class MillerRabin {
	
	/**
	 * Perform the Miller-Rabin Test on a given BigInteger to determine whether it is prime
	 * @param n The BigInteger to test
	 * @param cert The number of repetitions for the test (the more the bigger certainty for truth)
	 * @return True if n is prime, false if it is not
	 */
	public static boolean isPrime(BigInteger n, int cert) {
		
		//If given value is 2, it is prime
		if(n.equals(new BigInteger("2"))) {
			return true;
		}
		
		//If given value is divisible by 2 it is not prime
		if(n.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
			return false;
		}
			
		//We repeat the algorithm multiple times to minimize the error
		for(int i = 0; i<cert; i++) {
			BigInteger a = randomBigInt(n.subtract(new BigInteger("2"))); //Find a random base
			if(isWitness(a,n)) { //Check for witnesses
				return false; //If found, n is not prime
			}
		}
		return true; //No witness found, n is probably prime
	}

	/**
	 * Generats a BigInteger of random value
	 * @param max the maximum value the BigInteger may have
	 * @return Randomly generated BigInteger
	 */
	private static BigInteger randomBigInt(BigInteger max) {
		Random rand = new Random();
		BigInteger out;
		
		/*
		 * The following loop generates a BigInteger strictly smaller than max. While it may seem inefficient,
		 * it is expected to need not more than two loop-through runs
		 * SEE: quickprogrammingtips.com/java/creating-a-random-biginteger-in-java.html
		 */
		
		do {
			out = new BigInteger(max.bitLength(), rand).add(BigInteger.ONE);
		} while (out.compareTo(max) > 0);
		return out;
	}
	
	/**
	 * Checks whether we can find a witness by evaluating a and n (by the Miller-Rabin method)
	 * @param a
	 * @param n
	 * @return true if a witness was found, false if not
	 */
	private static boolean isWitness(BigInteger a, BigInteger n) {
		BigInteger n1 = n.subtract(BigInteger.ONE);
		int t = 0;
		while(n1.mod(new BigInteger("2").pow(t+1)).equals(BigInteger.ZERO)) {t++;}
		BigInteger u = n1.divide(new BigInteger("2").pow(t));
		BigInteger x0;
		BigInteger x1 = a.modPow(u, n);
		
		for(int i=1; i<t+1; i++) {
			x0 = x1;
			x1 = x0.pow(2).mod(n);
			if(x1.compareTo(BigInteger.ONE)==0 && x0.compareTo(BigInteger.ONE)!=0 && x0.compareTo(n1)!=0) {
				return true;
			}
		}
		return (x1.compareTo(BigInteger.ONE)!=0);		
	}
}
