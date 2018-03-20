package DynamicProgramming;

public class MaximumSumContiguousKadanes {
    /*
     *@Author : Sahil
     * Date: 20 March 2018
     *find the sum of contiguous subarray within a one-dimensional array of numbers which has the largest sum.
     * Example :   {-2, -3, 4, -1, -2, 1, 5, -3}
     * Sol : 7
     * which we get from 4 + -1 + -2 + 1 + 5
     *
     * References :https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
     * Solution 1: [Using extra Space O(N))
     * 1.Let give array be A[]
     * 2. Make an array T[] . copy first element . T[0]=A[0]
     * 3. Fill array T[] from 1 to a.length with this formula
     * T[i]=max(a[i],T[i-1]+a[i]
     * 4. Find maximum element among T[i] , this is the maximum sum contiguous subarray
     *
     * Solution 2: Space complexity O(1)
     * Reference SOl 2 : https://en.wikipedia.org/wiki/Maximum_subarray_problem
     * It is same as Solution except T[i-1] here is replaces with a variable -> max_ending_here
     * and globalMaxsoFar is replaced with max_so_farX
     *
     *
     * Test case
     * 1. array is null
     * 2. array has only 1 element
     */

    public int maxSumSubarraySol2(int arr[]) {

        if (arr.length == 1)               //Test case: only 1 element in array
            return arr[0];

        Integer max_ending_here = arr[0];
        Integer max_so_far = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max_ending_here = Math.max(arr[i], max_ending_here + arr[i]);
            max_so_far = Math.max(max_so_far, max_ending_here);
        }
        return max_so_far;
    }

    public int maxSumSubarraySol1(int arr[]) {
        int T[] = new int[arr.length];
        T[0] = arr[0];
        int globalMaxofT = Integer.MIN_VALUE;

        for (int i = 1; i < arr.length; i++) {

            T[i] = Math.max(arr[i], T[i - 1] + arr[i]);

            if (globalMaxofT < T[i])
                globalMaxofT = T[i];

        }
        return globalMaxofT;
    }

    public static void main(String args[]) {
        int arr[] = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
        MaximumSumContiguousKadanes ms = new MaximumSumContiguousKadanes();
        System.out.print(ms.maxSumSubarraySol2(arr));
    }
}
