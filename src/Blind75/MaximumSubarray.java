package Blind75;

/**
 * https://leetcode.com/problems/maximum-subarray/
 * <p>
 * <p>
 * Given an integer array nums, find the
 * subarray
 * with the largest sum, and return its sum.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 * <p>
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 */

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int ans = maxSoFar;
        for (int i = 1; i < nums.length; i++) {
            maxSoFar = Math.max(nums[i], maxSoFar + nums[i]);
            ans = Math.max(ans, maxSoFar);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, -1, 7, 8};
        MaximumSubarray maximumSubarray = new MaximumSubarray();
        System.out.println(maximumSubarray.maxSubArray(arr));
    }
}
