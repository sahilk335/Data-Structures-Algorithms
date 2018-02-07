package Array;

import static Sort.QuickSort.swap;

public class KthLargestElementInArray {
    /*
     *@Author : Sahil
     * Date : 7 Feb 2018
     *
     * References : https://leetcode.com/problems/kth-largest-element-in-an-array/description/
     * 
     * Solution : using Max Heap [submitted to leetcode]
     */

    public int buildHeapAndFind(int arr[], int k) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        return kthLargestElement(arr, k);
    }

    public void heapify(int arr[], int size, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < size && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < size && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, size, largest);
        }
    }

    public int kthLargestElement(int arr[], int k) {
        int kthElement = 0;
        int n = arr.length;
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (count == k)
                break;
            kthElement = arr[0];
            swap(arr, i, 0);
            heapify(arr, i, 0);
            count++;
        }
        return kthElement;
    }


    public static void main(String args[]) {

        int arr[] = {8, 7, 6, 4, 3, 1, 2, 9, 5};
        int k = 2;
        KthLargestElementInArray element = new KthLargestElementInArray();
        System.out.print(element.buildHeapAndFind(arr, k));
    }
}
