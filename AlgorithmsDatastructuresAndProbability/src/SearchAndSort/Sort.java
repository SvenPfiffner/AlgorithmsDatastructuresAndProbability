package SearchAndSort;

import java.lang.reflect.Array;
import java.util.Random;

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
	 * Sort a given array using insertion sort
	 * @param data array to sort
	 */
	public static void insertionSort(int[] data) {
		for(int i = 0; i<data.length; i++) {
			int newPos = i;
			while(newPos>0 && data[newPos-1] > data[newPos]) {
				swap(data, newPos, newPos -1);
				newPos--;
			}
		}
	}
	
	/**
	 * Sort a given array using selection sort
	 * @param data array to sort
	 */
	public static void selectionSort(int[] data) {
		for(int i = 0; i < data.length; i++){
			int min = i;
			for(int j = i; j<data.length; j++) {
				if(data[j] < data[min]) {
					min = j;
				}
			}
			swap(data, min, i);
		}
	}
	
	/**
	 * Sort a given array using heapsSort
	 * @param data to sort
	 */
	public static void heapSort(int[] data) {
		
		//Build heap
		for(int i = data.length/2 -1; i>=0; i--) {
			restoreMaxHeapCondition(data, i, data.length);
		}
		
		//Extract root and rearrange n times
		for(int i = data.length -1; i>=0; i--) {
			swap(data, 0, i);
			restoreMaxHeapCondition(data, 0, i);
		}
		
	}
	
	/**
	 * Sort a given array using mergeSort
	 * @param data to sort
	 */
	public static void mergeSort(int[] data) {
		mergeSortRec(data, 0, data.length-1);
	}
	
	/**
	 * Sort a given array using quickSort
	 * @param data to sort
	 */
	public static void quickSort(int[] data) {
		quickSortRec(data, 0, data.length-1);
	}
	
	/**
	 * Restores max heap condition in a given array within a given border
	 * @param data the array
	 * @param lo low site of border
	 * @param hi high site of border
	 */
	private static void restoreMaxHeapCondition(int[] data, int lo, int hi) {
		int max = lo;
		int leftChild = 2* lo +1;
		int rightChild = 2*lo +2;
		
		if(leftChild < hi && data[leftChild] > data[max]) {max = leftChild;}
		if(rightChild < hi && data[rightChild] > data[max]) {max = rightChild;}
		
		if(max != lo) {
			swap(data,lo,max);
			restoreMaxHeapCondition(data, max, hi);
		}
	}
	
	/**
	 * Merge an array within a given border
	 * @param data array to merge in
	 * @param lo left side of border
	 * @param mid center of border
	 * @param hi right side of border
	 */
	private static void merge(int[] data, int lo, int mid, int hi) {
		int lo2 = mid + 1;
		
		//We can leave the merge if all elements of one subarray are smaller than all of other 
		if(data[mid] <= data[lo2]) {return;}
		
		//Go through subarrays until one of them is traversed
		while(lo <= mid && lo2 <= hi) {
			//Merge according to size
			if(data[lo] <= data[lo2]) {lo++;}
			else {
				int temp = data[lo2];
				int index = lo2;
				
				//Shift right
				while(index != lo) {
					data[index] = data[index -1];
					index--;
				}
				data[lo] = temp;
				
				lo++;
				mid++;
				lo2++;
			}
		}
	}
	
	/**
	 * Recursive mergesort for a given array within a border 
	 * @param data the array to sort
	 * @param lo left border
	 * @param hi right border
	 */
	private static void mergeSortRec(int[] data, int lo, int hi) {
		if(lo >= hi) {return;} //In this case we are done (base case)
		
		//Avoids overflow basically the same as (lo+hi) / 2
		int mid = lo + (hi-lo) / 2;
		
		mergeSortRec(data, lo, mid);
		mergeSortRec(data, mid+1, hi);
		
		merge(data, lo, mid, hi);
	}
	
	/**
	 * Recursive quicksort for a given array within a border
	 * @param data the array to sort
	 * @param lo left border
	 * @param hi right border
	 */
	private static void quickSortRec(int[] data, int lo, int hi) {
		if(lo >= hi) {return;} //In this case we are done (base case)
		
		Random rand = new Random();
		int pivot = lo + rand.nextInt(hi-lo + 1);
		swap(data, pivot, hi);
		
		int partition = randomQuicksortPartition(data, lo, hi, data[hi]);
		quickSortRec(data, lo, partition-1);
		quickSortRec(data, partition + 1, hi);
	}
	
	/**
	 * partitions a given array within a given border
	 * @param data the array to partition
	 * @param lo left border
	 * @param hi right border
	 * @return integer that determines the index of partition "cut"
	 */
	private static int randomQuicksortPartition(int[] data, int lo, int hi, int pivot) {
		int left = lo -1;
		int right = hi;
		
		while(true) {
			while(data[++left] < pivot);
			while(right > 0 && data[--right] > pivot);
			
			if(left >= right) {break;}
			else {swap(data, left, right);}
		}
		swap(data, left, hi);
		return left;
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
