package Array;

public class BinarySearch {
    /*
     *@Author : Sahil
     * Date : 03 May 2018
     *
     * Binary Search - Find key in sorted array
     *
     */

    public int binarySearchRecursive(int[] arr, int low, int high, int key) {
        if (high >= low) {
            int mid = low + (high - low) / 2;       //to handle integer overflow tinyurl.com/ybpc9qlr

            if (arr[mid] == key)
                return mid;

            if (key > arr[mid])
                return binarySearchRecursive(arr, mid + 1, high, key);
            else
                return binarySearchRecursive(arr, low, mid - 1, key);
        }
        return -1;
    }

    public int binarySearchIterative(int[] arr, int low, int high, int key) {
        while (high >= low) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key)
                return mid;
            if (key > arr[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    public static void main(String args[]) {
        int arr[] = {1, 2, 5, 7, 9, 20, 25};
        BinarySearch binarySearch = new BinarySearch();
        int low = 0;
        int high = arr.length - 1;
        System.out.print(binarySearch.binarySearchIterative(arr, low, high, 20));
    }
}
