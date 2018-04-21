package Array;

public class SubarrayGivenSum {
    /*
     *@Author : Sahil
     * Date : 21 Apr 2018
     *
     * Find subarray with given sum (Array contains only positive number)
     * Given an unsorted array of nonnegative integers, find a continous subarray which adds to a given number.
     *
     * Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
     * Ouptut: Sum found between indexes 2 and 4
     *
     * Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
     * Ouptut: Sum found between indexes 1 and 4
     *
     * Input: arr[] = {1, 4}, sum = 0
     * Output: No subarray found
     *
     * Test Case :
     * 1. Array is empty
     * 2.Sum does not exist
     *
     * Solution : O(N)
     * Initialize a variable curr_sum as first element. curr_sum indicates the sum of current subarray.
     *  Start from the second element and add all elements one by one to the curr_sum.
     *  If curr_sum becomes equal to sum, then print the solution.
     *  If curr_sum exceeds the sum, then remove trailing elements while curr_sum is greater than sum.
     *
     *  References:
     *
     *  https://www.geeksforgeeks.org/find-subarray-with-given-sum/
     *
     */

    public void subarrayGivenSum(int arr[], int sum) {
        int len = arr.length;
        if (len < 1) {
            return;
        }

        int start = 0;
        int currSum = 0;

        for (int i = 0; i < len; i++) {
            //remove elements that are causing increase in sum from left side and increment left index value
            while (currSum > sum && start < i) {
                currSum = currSum - arr[start];
                start++;
            }

            if (currSum == sum) {
                System.out.print("Sum foung between index " + start + " and " + (i - 1));
                return;
            }
            currSum += arr[i];
        }

        //If reach here, sum does not exist

        System.out.print("No subarray found");

    }

    public static void main(String args[]) {
        SubarrayGivenSum subarrayGivenSum = new SubarrayGivenSum();
        int arr[] = {1, 4};
        int sum = 7;
        subarrayGivenSum.subarrayGivenSum(arr, sum);

    }
}
