import java.io.*;
import java.util.*;
public class SortAlgo {
	private static int array[];
	private static int indexArray[];
	
	public static void quickSort(int[] inputarray, int begin, int end) {
		if(inputarray == null || inputarray.length == 0) {
			return;
		}
		else {
			if(begin < end) {
				int partitionIndex = partition(inputarray, begin, end);
				quickSort(inputarray, begin, partitionIndex-1);
				quickSort(inputarray, partitionIndex+1, end);
			}
	}
}
	private static int partition(int[] inputarray, int begin, int end) {
		int pivot = array[end]; // Setting the last number of list as pivot.
		int i = (begin-1);
		
		for(int j=begin; j<end; j++) {
			if(array[i] <= pivot) {
				i++;
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;      // Swapping of tuples.
			}
		}
		int temp = array[i+1];
		array[i+1] = array[end];
		array[end] = temp;
		return i+1;
	}
}
		

