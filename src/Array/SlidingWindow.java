package Array;

public class SlidingWindow {
    /*
     *@Author : Sahil
     * Date : 12 Apr 2018
     *
     * Given an array of integers of size ‘n’.
     * Our aim is to calculate the maximum sum of ‘k’
     * consecutive elements in the array.
     *
     * Input  : arr[] = {100, 200, 300, 400}
     *          k = 2
     * Output : 700
     *
     * Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}
     *          k = 4
     * Output : 39
     * We get maximum sum by adding subarray {4, 2, 10, 23}
     * of size 4.
     *
     * Input  : arr[] = {2, 3}
     *          k = 3
     * Output : Invalid
     * There is no subarray of size 3 as size of whole
     * array is 2.
     *
     * References :
     * https://www.geeksforgeeks.org/window-sliding-technique/
     *
     * Solution:Applying sliding window technique :
     *
     * 1 .We compute the sum of first k elements out of n terms using a linear loop and store the sum in variable window_sum.
     * 2 .Then we will graze linearly over the array till it reaches the end and simultaneously keep track of maximum sum.
     * 3 .To get the current sum of block of k elements just subtract the first element from the previous block and add the last element of the current block .
     */

    public Integer maxSlidingSumKLength(int[] a, int k) {
        int answer = 0;
        int currWindowSum = 0;
        //Test case if k is greater than array length then return null
        if (k > a.length)
            return null;

        for (int i = 0; i < k; i++) {
            currWindowSum += a[i];
        }
        answer = currWindowSum;

        for (int i = k; i < a.length; i++) {
            currWindowSum = currWindowSum - a[i - k] + a[i];
            answer = Math.max(answer, currWindowSum);
        }

        return answer;
    }

    public static void main(String args[]) {
        int[] arr = {1, 4, 2, 10, 23, 3, 1, 0, 20};
        int k = 4;
        SlidingWindow slidingWindow = new SlidingWindow();
        System.out.print(slidingWindow.maxSlidingSumKLength(arr, k));
    }
}
