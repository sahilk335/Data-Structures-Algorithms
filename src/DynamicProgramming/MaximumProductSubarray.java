package DynamicProgramming;

public class MaximumProductSubarray {
    /*
     *@Author : Sahil
     * Date : 07 May 2019
     * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
     *
     * Example 1:
     *
     * Input: [2,3,-2,4]
     * Output: 6
     * Explanation: [2,3] has the largest product 6.
     * Example 2:
     *
     * Input: [-2,0,-1]
     * Output: 0
     * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
     *
     * Solution :
     * 1. It is same as kadanes algorithm
     * 2. Except in product if negative and negative multiplies we get bigger positive number
     * 3. SO 2 main steps, at each step
     *      a) maintain minimum so far and maximum so far
     *      b) when current number is minimum swap it with maximum to calculate maximum product subarray
     *
     * References :
     * https://leetcode.com/problems/maximum-product-subarray/
     * https://leetcode.com/problems/maximum-product-subarray/discuss/48230/Possibly-simplest-solution-with-O(n)-time-complexity
     *
     */
    int maxProduct(int A[]) {
        // store the result that is the max we have found so far
        int r = A[0];
        int n = A.length;
        int imax = r;
        int imin = r;


        // imax/imin stores the max/min product of
        // subarray that ends with the current number A[i]
        for (int i = 1; i < n; i++) {
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if (A[i] < 0) {  //swap imin & imax , if current element is less than 0
                int temp = imin;
                imin = imax;
                imax = temp;
            }
            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            imax = Math.max(A[i], imax * A[i]);
            imin = Math.min(A[i], imin * A[i]);

            // the newly computed max value is a candidate for our global result
            r = Math.max(r, imax);
        }
        return r;
    }


    public static void main(String[] args) {
        MaximumProductSubarray maximumProductSubarray = new MaximumProductSubarray();
        int[] nums = {-2, 3, -4};
        System.out.println(maximumProductSubarray.maxProduct(nums));
    }
}
