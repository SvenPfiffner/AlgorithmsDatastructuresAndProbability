package misc;

import java.math.BigInteger;

/**
 * This class implements a method to compute fibonacci numbers using dynamic programming
 * @author Sven Pfiffner
 */
public class DpFib {
	
	public static BigInteger fib(int num) {
		BigInteger[] cache = new BigInteger[num];
		//Base case
		cache[0] = BigInteger.ONE;
		cache[1] = BigInteger.ONE;
		for(int i = 2; i<num; i++) {
			cache[i] = cache[i-1].add(cache[i-2]);
		}
		return cache[num-1];
	}

}
