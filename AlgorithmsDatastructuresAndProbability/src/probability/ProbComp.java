package probability;

import java.math.BigInteger;

/**
 * This class provides some static methods that can perform probabilistic computations crucial for a lot of
 * non-deterministic algorithms 
 * @author Sven Pfiffner
 */
public class ProbComp {
	
	/**
	 * Compute the binomial coefficient of two given integers
	 * @param n one of the two integers
	 * @param k the other integer
	 * @return long value of the binomial coefficient
	 */
	public static long binomialCoefficient(int n, int k) {
		BigInteger out = factorial(n).divide(factorial(k).multiply(factorial(n-k)));
		return out.longValue();
	}
	
	/**
	 * Compute the factorial of a given integer
	 * @param n is the number to take the factorial of
	 * @return A BigInteger with the result
	 */
	public static BigInteger factorial(int n) {
		BigInteger out = BigInteger.ONE; //We start with 1
		
		for (int i = 2; i<= n; i++) { //Compute factorial of n
			out = out.multiply(BigInteger.valueOf(i));
		}
		return out;
	}
	
}
