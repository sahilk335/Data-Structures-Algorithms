package Array;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaxiumAllSubArraySizeK {
    /*
     *@Author : Sahil
     * Date : 12 Apr 2018
     *
     * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

     * For example,
     * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
     *
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     * Examples:
     *
     * Input :
     * arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}
     * k = 3
     * Output :
     * 3 3 4 5 5 5 6
     *
     * Input :
     * arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}
     * k = 4
     * Output :
     * 10 10 10 15 15 90 90
     *
     *Solution : (Using Deque)
     *
     * Reference :
     * https://leetcode.com/problems/sliding-window-maximum/description/
     *
     *
     * Solution 1 : O(N) Using arrays
     *
     * For Example: A = [2,1,3,4,6,3,8,9,10,12,56], w=4
     *
     * partition the array in blocks of size w=4. The last block may have less then w.
     * 2, 1, 3, 4 | 6, 3, 8, 9 | 10, 12, 56|
     *
     * Traverse the list from start to end and calculate max_so_far. Reset max after each block boundary (of w elements).
     * left_max[] = 2, 2, 3, 4 | 6, 6, 8, 9 | 10, 12, 56
     *
     * Similarly calculate max in future by traversing from end to start.
     * right_max[] = 4, 4, 4, 4 | 9, 9, 9, 9 | 56, 56, 56
     *
     * now, sliding max at each position i in current window, sliding-max(i) = max{right_max(i), left_max(i+w-1)}
     * sliding_max = 4, 6, 6, 8, 9, 10, 12, 56
     *
     *
     * Solution 2 : O(N) Using Deque
     *
     * We scan the array from 0 to n-1, keep “promising” elements in the deque. The algorithm is amortized O(n)
     * as each element is put and polled once.
     *
     * At each i, we keep “promising” elements, which are potentially max number in window [i-(k-1),i]
     * or any subsequent window. This means If an element in the deque and it is out of i-(k-1), we discard them.
     * We just need to poll from the head, as we are using a deque and elements are ordered as the sequence in the array
     * Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the
     * tail.
     * This is because if a[x] <a[i] and x<i,
     * then a[x] has no chance to be the “max” in [i-(k-1),i], or any other subsequent window: a[i]
     * would always be a better candidate.
     * As a result elements in the deque are ordered in both sequence in array and their value.
     * At each step the head of the deque is the max element in [i-(k-1),i]
     */


    public void maxSubarrayKLengthUsingArray(int[] arr, int k) {

        int[] left = new int[arr.length];
        int[] right = new int[arr.length];

        left[0] = arr[0];
        right[arr.length - 1] = arr[arr.length - 1];

        for (int i = 1; i < arr.length; i++) {
            left[i] = (i % k == 0) ? arr[i] : Math.max(left[i - 1], arr[i]);
            int j = arr.length - i - 1;
            right[j] = ((j + 1) % k == 0) ? arr[j] : Math.max(right[j + 1], arr[j]);
        }

        int slidingMax[] = new int[arr.length - k + 1];
        for (int i = 0; i + k <= arr.length; i++) {
            slidingMax[i] = Math.max(right[i], left[i + k - 1]);
        }

        //Print the slidingMax
        for (int i = 0; i < slidingMax.length; i++) {
            System.out.print(slidingMax[i] + " ");
        }
    }

    //Solution 2
    public void maxSubarrayKLengthUsingDeque(int[] arr, int k) {
        if (arr == null)
            return;

        int[] res = new int[arr.length - k + 1];
        int resIdx = 0;

        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            //If any element is present that is older than current sliding window till i index, then keep on pop all
            //such items from deque
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }

            //if coming elements at i index are smaller than the head, then keep on popping from the end of the queue
            //till you find the index that stores greater value then you(i index value)
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                deque.pollLast();
            }

            //push the i index in the deque everytime , after checking above two steps
            deque.offer(i);

            if (i >= k - 1) {
                res[resIdx++] = arr[deque.peek()];
            }
        }

        //print the result array
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    public static void main(String args[]) {
        SlidingWindowMaxiumAllSubArraySizeK sliding = new SlidingWindowMaxiumAllSubArraySizeK();
        int arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        int k = 3;
        sliding.maxSubarrayKLengthUsingDeque(arr, k);
    }
}
