package Array;

public class MinimumSizeSubarraySum {
    /*
     *@Author : Sahil
     * Date : 18 May 2018
     *
     * Given an array of n positive integers and a positive integer s, find the minimal length of a
     * contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
     *
     * Example:
     *
     * Input: [2,3,1,2,4,3], s = 7
     * Output: 2
     * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
     *
     * References :
     * Asked in GoldmanSachs Telephonic interview
     *
     * Solution :
     * Until now, we have kept the starting index of subarray fixed, and found the last position. Instead, we could
     * move the starting index of the current subarray as soon as we know that no better could be done with this
     * index as the starting index. We could keep 2 pointer,one for the start and another for the end of the current
     * subarray, and make optimal moves so as to keep the sum greater than ss as well as maintain
     * the lowest size possible.
     *
     * Algorithm
     *
     * Initialize left pointer to 0 and sum to 0
     * Iterate over the nums:
     * Add nums[i] to sum
     * While sum is greater than or equal to ss:
     * Update ans=min(ans,i+1−left), where i+1−left is the size of current subarray
     * It means that the first index can safely be incremented, since, the minimum subarray starting with this index
     * with  sum≥s has been achieved
     * Subtract nums[left] from sum and increment left
     *
     * Time Complexity : O(N)
     * Space Complexity : O(1)
     */

    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int answer = Integer.MAX_VALUE;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                answer = Math.min(answer, i + 1 - left);
                sum -= nums[left];
                left++;
            }
        }
    }

    public static void main(String[] args) {
        MinimumSizeSubarraySum minimumSizeSubarraySum = new MinimumSizeSubarraySum();
    }
}
