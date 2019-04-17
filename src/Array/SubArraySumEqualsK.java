package Array;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualsK {
    /*
     *@Author : Sahil
     * Date : 17 April 2019
     *
     * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
     *
     * Example 1:
     * Input:nums = [1,1,1], k = 2
     * Output: 2
     *
     * References :
     * https://leetcode.com/problems/subarray-sum-equals-k/
     *
     * SOlution :
     *
     * Note it is contiguos sum
     *
     * 1. keep on adding sum of each element with previous element in map.
     * 2. while adding if you get any element in map which sum-k,
     * then add it to count.
     *
     */

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (hashMap.containsKey(sum - k)) {
                count += hashMap.get(sum - k);
            }
            hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        SubArraySumEqualsK subArraySumEqualsK = new SubArraySumEqualsK();
        int arr[] = {9, 4, 20, 3, 10, 5};
        int k = 33;
        System.out.println("Number of Subarray with Sum  K :" + subArraySumEqualsK.subarraySum(arr, k));
    }
}
