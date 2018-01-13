package Array;

public class UnsortedContiguousSubarray {
    /*
     * Source : https://leetcode.com/problems/shortest-unsorted-continuous-subarray/description/
     * https://www.geeksforgeeks.org/minimum-length-unsorted-subarray-sorting-which-makes-the-complete-array-sorted/
     * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in
     * ascending order, then the whole array will be sorted in ascending order, too.
     * Input: [2, 6, 4, 8, 10, 9, 15]
     * Output: 5
     * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole
     * array sorted in ascending order.
     *
     * Solution Article : https://leetcode.com/problems/shortest-unsorted-continuous-subarray/solution/
     */

    public int unsortedArraySize(int arr[]) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        boolean culpritFound = false;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1])
                culpritFound = true;
            if (culpritFound) {
                min = Math.min(min, arr[i]);
            }
        }

        culpritFound = false;

        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1])
                culpritFound = true;
            if (culpritFound) {
                max = Math.max(max, arr[i]);
            }
        }

        int low, high;

        for (low = 0; low < arr.length; low++) {
            if (arr[low] > min)
                break;
        }

        for (high = arr.length - 1; high >= 0; high--) {
            if (arr[high] < max)
                break;
        }

        //System.out.print("High : "+high+" Low :"+low+" Min : "+min+" Max : "+max);

        return high - low < 0 ? 0 : high - low + 1;

    }


    public static void main(String args[]) {
        int arr[] = {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
        UnsortedContiguousSubarray ucs = new UnsortedContiguousSubarray();
        int solution;
        solution = ucs.unsortedArraySize(arr);
        System.out.print("Unsorted Array Size : " + solution);
    }
}
