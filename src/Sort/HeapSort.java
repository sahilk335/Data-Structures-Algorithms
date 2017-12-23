package Sort;

import static Sort.QuickSort.swap;
import static Sort.QuickSort.printArray;

/*
2 Step Algorithm :

1. Build Max heap using buildHeap function that recursivley calls heapify from non-leaf nodes to root
2. swap first and last element and then call heapify on the array from the root

References : https://www.programiz.com/dsa/heap-sort
 */

public class HeapSort {

    void buildHeap(int arr[], int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    void heapify(int arr[], int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest])
            largest = left;
        if (right < n && arr[right] > arr[largest])
            largest = right;
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    void heapSort(int arr[], int n) {
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    public static void main(String args[]) {
        int arr[]={7,1,3,4,5,8,9,0};
        HeapSort heapSort=new HeapSort();
        heapSort.buildHeap(arr,arr.length);
        heapSort.heapSort(arr,arr.length);
        printArray(arr);
    }

}
