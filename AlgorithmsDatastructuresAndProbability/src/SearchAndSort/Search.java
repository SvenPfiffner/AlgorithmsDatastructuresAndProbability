package SearchAndSort;

/**
 * This class contains some static methods which implement common comparison based search-algorithms
 * @author Sven Pfiffner
 */
public class Search {
	
	/**
	 * Search in an array using binary search
	 * @param data: The array to search in
	 * @param t: the element to search for
	 * @return The index at which t was found, -1 if t is not in the array
	 */
	public static int binarySearch(int[] data, int t) {
		int left = 0;
		int right = data.length-1;
		
		while(left <= right) {
			int center = (left+right) / 2;
			if(data[center] == t) {return center;}
			else if(data[center] > t) {right = center -1;}
			else {left = center +1;}
		}
		return -1;
	}
	
	/**
	 * Search in an array using interpolation
	 * @param data: The array to search in
	 * @param t: the element to search for
	 * @return The index at which t was found, -1 if t is not in the array
	 */
	public static int interpolationSearch(int[] data, int t) {
		int left = 0;
		int right = data.length-1;
		while(left <= right && t >= data[left] && t <= data[right]) {
			
			if(left == right) {
				if(data[left] == t) {return left;}
				return -1;
			}
			
			int pos = left + (((right-left) / (data[right] - data[left])) * (t - data[left]));
			
			if(data[pos] == t) {return pos;}
			if(data[pos] < t) {left = pos+1;}
			else {right = pos-1;}
		}
		return -1;
	}	
	
	/**
	 * Search linearly in an array
	 * @param data: The array to search in
	 * @param t: the element to search for
	 * @return The index at which t was found, -1 if t is not in the array
	 */
	public static int linearSearch(int[] data, int t) {
		for(int i = 0; i<data.length; i++ ) {
			if(data[i] == t) { return i;}
		}
		return -1;
	}
	
}
