package Array;

public class CountingInversions {
    /*
     *@Author : Sahil
     * Date : 7 Feb 2019
     *
     *  Counting Inversions
     * Inversion Count for an array indicates – how far (or close) the array is from being sorted. If array is already sorted then inversion count is 0. If array is sorted in reverse order that inversion count is the maximum.
     * Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j
     *
     * Example:
     * The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
     *
     * References :
     * https://www.geeksforgeeks.org/counting-inversions/
     * https://medium.com/@ssbothwell/counting-inversions-with-merge-sort-4d9910dc95f0
     * https://www.techiedelight.com/inversion-count-array/
     *
     *
     * Solution 1:
     *
     * Using MergeSort
     * While merging there are two arrays a&b if at anypoint a[i]<b[j] the left over a[] elements (a.length-i-1) will
     * form inversion pair with b[j].
     * keep on adding this inversion and return the inversion count at the end
     *
     * Time Complexity - O(N log N)
     *
     * Solution 2 :
     *
     * Using BIT
     */


    public int merge(int[] arr, int[] copy, int low, int mid, int high) {
        int k = low;
        int i = low;
        int j = mid + 1;
        int inversionCounts = 0;

        //While there are elements in left and right runs
        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                copy[k++] = arr[i++];
            } else {
                copy[k++] = arr[j++];
                inversionCounts += (mid - i + 1);
            }
        }

        //copy remaining elements
        while (i <= mid) {
            copy[k++] = arr[i++];
        }

        //Don't need to copy second half

        //copy back to original array to reflect sorted order
        for (i = low; i <= high; i++) {
            arr[i] = copy[i];
        }

        return inversionCounts;
    }

    public int mergeSort(int[] arr, int[] copy, int low, int high) {
        if (high == low)
            return 0;

        int mid = (low + high) / 2;

        int inversionCounts = 0;

        //Recursively split runs into 2 halves until run size ==1 , then merge them and return back the call chain

        //Split/merge left half
        inversionCounts += mergeSort(arr, copy, low, mid);

        //Split/merge right half
        inversionCounts += mergeSort(arr, copy, low, mid);


        //merge the two half runs
        inversionCounts += merge(arr, copy, low, mid, high);

        return inversionCounts;
    }

    public static void main(String args[]) {
        CountingInversions countingInversions = new CountingInversions();
        int arr[] = new int[] { 1, 20, 6, 4, 5 };
        int copy[]=new int[arr.length];
        System.out.println("Number of inversions are " + countingInversions.mergeSort(arr, copy,0,arr.length-1));

    }
}
