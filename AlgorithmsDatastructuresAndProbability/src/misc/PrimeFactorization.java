package misc;

import java.util.LinkedList;

/**
 * This class implements a factorization algorithm which finds the prime factorization of a given number
 * @author Sven Pfiffner
 */
public class PrimeFactorization {
	
	/**
	 * Performs a prime factorization on a given integer
	 * @param value to factor
	 * @return LinkedList with all prime factors
	 */
	public static LinkedList<Integer> getPrimeFac(int value) {
		LinkedList<Integer> factors = new LinkedList<Integer>();
		
		//Divide by two while possible
		while(value % 2 == 0) {
			factors.add(2);
			value/=2;
		}
		
		//Loop through all odd numbers <= square root of value and check if they are in prime factorization
		for(int i = 3; i<= Math.sqrt(value); i+=2) {
			while(value % i == 0) {
				factors.add(i);
				value /= i;
			}
		}
		
		if(value > 1) { //If value is not one, rest is prime
			factors.add(value); //So add to factorization
		}
		
		return factors;
	}
	
}
