package Array;

public class MinimumMaximumElementInRotatedSortedArray {
    /*
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
     *
     * Find the minimum element.
     *
     * Solution :
     *
     * 1. Maximum element is the pivot element
     * 2. Minimum element is next to the maximum element.
     *
     * Example 1:
     *
     * Input: [3,4,5,1,2]
     * Output: 1
     * Example 2:
     *
     * Input: [4,5,6,7,0,1,2]
     * Output: 0
     * References :
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
     */

    boolean isAlreadySorted = true;

    public int findMin(int[] nums) {

        return nums[minimumElement(nums, 0, nums.length - 1)];

    }

    public int minimumElement(int arr[], int low, int high) {
        // loop invariant: 1. low < high
        //                 2. mid != high and thus A[mid] != A[high] (no duplicate exists)
        //                 3. minimum is between [low, high]
        // The proof that the loop will exit: after each iteration either the 'high' decreases
        // or the 'low' increases, so the interval [low, high] will always shrink.
        if (low == high) return low;

        int mid = (low + high) / 2;

        if (arr[mid] > arr[high]) {
            // the mininum is in the right part
            return minimumElement(arr, mid + 1, high);
        } else// the mininum is in the left part
            return minimumElement(arr, low, mid);
    }

    public static void main(String args[]) {
        MinimumMaximumElementInRotatedSortedArray minimum = new MinimumMaximumElementInRotatedSortedArray();
        int arr[] = {3, 4, 5, 1, 2};
        System.out.print("Minimum Element :" + minimum.findMin(arr));
    }
}
