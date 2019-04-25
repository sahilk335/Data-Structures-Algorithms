package Array;

import java.util.ArrayList;
import java.util.List;

public class MajorityElement2 {
    /*
     *@Author : Sahil Khurana
     * Date : 25 Apr 2019
     * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
     *
     * Note: The algorithm should run in linear time and in O(1) space.
     *
     * Example 1:
     *
     * Input: [3,2,3]
     * Output: [3]
     * Example 2:
     *
     * Input: [1,1,1,3,3,2,2,2]
     * Output: [1,2]
     *
     * References :
     * https://leetcode.com/problems/majority-element-ii/
     *
     * Solution
     *
     * 1. We have to find two majority elements using Booyers Voting algo
     *  why ? because
     *  greater than N/3
     *  +
     *  greater than N/3
     *  +
     *  x
     *
     *  = N
     *
     *  so it can be concluded that
     *
     *  x is less than N/3
     *
     *  so thats why we take 2 suspected val
     *
     *  2. the two majority element you take, check for them again, if there value is greater than N/3 or not
     *
     */
    public List<Integer> majorityElement(int[] nums) {

        if (nums == null || nums.length == 0)
            return new ArrayList<Integer>();

        List<Integer> result = new ArrayList<>();
        int count1 = 0, count2 = 0;
        int majority1 = nums[0], majority2 = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (majority1 == nums[i]) {
                count1++;
            } else if (majority2 == nums[i]) {
                count2++;
            }  else if (majority2 == nums[i]) {
                majority2 = nums[i];
                count2 = 1;
            }else if (count1 == 0) {
                majority1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                majority2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1=0;
        count2=0;

        //Now check if majority1 and majority 2 elements are greater than N/3

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == majority1) {
                count1++;
            } else if (nums[i] == majority2) {
                count2++;
            }
        }

        if (count1 > nums.length / 3)
            result.add(majority1);
        if (count2 > nums.length / 3)
            result.add(majority2);

        return result;
    }

    public static void main(String[] args) {
        MajorityElement2 majorityElement = new MajorityElement2();
        int[] nums = {1, 1, 1, 3, 3, 2, 2, 2};
        System.out.println(majorityElement.majorityElement(nums).toString());
    }
}
