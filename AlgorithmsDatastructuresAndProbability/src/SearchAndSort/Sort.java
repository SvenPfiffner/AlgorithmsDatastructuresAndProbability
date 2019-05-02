package SearchAndSort;

/**
 * This class contains some static methods which implement common comparison based sort-algorithms
 * @author Sven Pfiffner
 */
public class Sort {

	/**
	 * Sort a given array using bubbleSort
	 * @param data to sort
	 */
	public static void bubbleSort(int[] data) {
		for(int i = 0; i<data.length; i++) {
			for(int j = 0; j<data.length-1-i; j++) {
				if(data[j]> data[j+1]) {swap(data, j, j+1);}
			}
		}
	}
	
	/**
	 * Sort a given array using heapsSort
	 * @param data to sort
	 */
	public static void heapSort(int[] data) {
		
	}
	
	/**
	 * Sort a given array using mergeSort
	 * @param data to sort
	 */
	public static void mergeSort(int[] data) {
		
	}
	
	/**
	 * Sort a given array using quickSort
	 * @param data to sort
	 */
	public static void quickSort(int[] data) {
		
	}
	
	/**
	 * Swaps two keys in a given array
	 * @param data array to swap in
	 * @param a first key
	 * @param b second key
	 */
	private static void swap(int[] data, int a, int b) {
		int temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}
}
