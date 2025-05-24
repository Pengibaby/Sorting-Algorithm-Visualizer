
public class SortingAlgorithms {
	
	/**
	 * Bubble sort
	 * @param array
	 * 			Input Visual_window object that contains the array to be sorted.
	 * @param size
	 * 			Size of the array to be sorted.
	 */
	public void bubbleSort(Visual_window array, int size) {
		Runnable sortTask = () -> {
		    try {
		    	for(int i = 0; i < size - 1; i++) {
					for(int j = 0; j < size - i - 1; j++) {
						if(array.getValue(j) > array.getValue(j+1)) {
							array.swap(j, j+1);
						}
					}
				}
		    } catch (Exception e) {
		        System.out.println("Interrupted.  Terminating sort.");
		    }
		};
		new Thread(sortTask, "Bubble Sort").start();
	}
	
	/**
	 * Iterative Merge Sort
	 * @param array
	 * 			Input Visual_window object that contains the array to be sorted.
	 * @param size
	 * 			Size of the array to be sorted.
	 */
	public void merge_sort(Visual_window array, int size) {
		Runnable sortTask = () -> {
		    try {
		    	int currentSubArraySize;  
		        int left_start; 

		        for (currentSubArraySize = 1; currentSubArraySize <= size-1; currentSubArraySize = 2*currentSubArraySize) { 
		            // Pick starting point of different 
		            // subarrays of current size 
		            for (left_start = 0; left_start < size-1; left_start += 2*currentSubArraySize) { 
		                // Find ending point of left  
		                // subarray. mid+1 is starting  
		                // point of right 
		                int mid = Math.min(left_start + currentSubArraySize - 1, size-1); 
		                int right_end = Math.min(left_start + 2*currentSubArraySize - 1, size-1); 
		                // Merge Subarrays arr[left_start...mid] 
		                // & arr[mid+1...right_end] 
		                merge(array, left_start, mid, right_end); 
		            } 
		        }
		    } catch (Exception e) {
		        System.out.println("Interrupted.  Terminating sort.");
		    }
		};
		new Thread(sortTask, "Merge Sort").start();
	}
	
	/**
	 * A helper method for merge sort that sorts the sub arrays and merges them together.
	 * @param array
	 * 			Input Visual_window object that contains the array to be sorted.
	 * @param left
	 * 			Index of the left most index of the left sub array
	 * @param middle
	 * 			Last index of the left sub array.
	 * @param right
	 * 			Last index of the right sub array.
	 */
	public void merge(Visual_window array, int left, int middle, int right) {
		int leftSize = middle - left + 1;
		int rightSize = right - middle;
		
		int[] leftArray = new int[leftSize];
		int[] rightArray = new int[rightSize];
		
		for(int i = 0; i < leftSize; i++) {
			leftArray[i] = array.getValue(left + i);
		}
		for(int i = 0; i < rightSize; i++) {
			rightArray[i] = array.getValue(middle + 1 + i);
		}
		
		int i = 0, j = 0, k = left;
		while(i < leftSize && j < rightSize) {
			if(leftArray[i] <= rightArray[j]) {
				array.swapSingleLeft(k, leftArray[i]);
				i++;
			} else {
				array.swapSingleRight(k, rightArray[j]);
				j++;
			}
			k++;
		}
		
		while(i < leftSize) {
			array.swapSingleLeft(k, leftArray[i]);
			i++;
			k++;
		}
		while(j < rightSize) {
			array.swapSingleRight(k, rightArray[j]);
			j++;
			k++;
		}
	}
	
	/**
	 * Insertion Sort.
	 * @param array
	 * 			Input Visual_window object that contains the array to be sorted.
	 * @param size
	 * 			Size of the array to be sorted.
	 */
	public void insertion_sort(Visual_window array, int size) {
		Runnable sortTask = () -> {
		    try {
				for(int i = 1; i < size; i++) {
					int key = array.getValue(i);
					int j = i - 1;
					
					while(j >= 0 && array.getValue(j) > key) {
						array.swap(j + 1, j);
						j = j - 1;
					}
					array.swapSingleLeft(j + 1, key);
				}
		    } catch (Exception e) {
		        System.out.println("Interrupted.  Terminating sort.");
		    }
		};
		new Thread(sortTask, "Insertion Sort").start();
	}
	
	/**
	 * Bucket Sort.
	 * @param array
	 * 			Input Visual_window object that contains the array to be sorted.
	 * @param size
	 * 			Size of the array to be sorted.
	 */
	public void bucket_sort(Visual_window array, int size) {
		Runnable sortTask = () -> {
		    try {
				int[] bucket = new int[array.max_value() + 1];
				for(int i = 0; i < size; i++) {
					bucket[array.getValue(i)]++;
					array.swap(i, i);
				}
				
				int pos = 0;
				for(int i = 0; i < bucket.length; i++) {
					for(int j = 0; j < bucket[i]; j++) {
						array.swapSingleLeft(pos, i);
						pos++;
					}
				}
		    } catch (Exception e) {
		        System.out.println("Interrupted.  Terminating sort.");
		    }
		};
		new Thread(sortTask, "Bucket Sort").start();
	}
	
	/**
	 * Selectioon Sort
	 * @param array
	 * 			Input Visual_window object that contains the array to be sorted.
	 * @param size
	 * 			Size of the array to be sorted.
	 */
	public void selection_sort(Visual_window array, int size) {
		Runnable sortTask = () -> {
		    try {
				for(int i = 0; i < size - 1; i++) {
					int min_index = i;
					
					for(int j = i + 1; j < size; j++) {
						if(array.getValue(j) < array.getValue(min_index)) {
							min_index = j;
						}
					}
					
					array.swap(i, min_index);
				}
		    } catch (Exception e) {
		        System.out.println("Interrupted.  Terminating sort.");
		    }
		};
		new Thread(sortTask, "Selection Sort").start();
	}
}
