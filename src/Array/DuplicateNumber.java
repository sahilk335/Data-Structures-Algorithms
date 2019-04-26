package Array;

public class DuplicateNumber {
    /*
     *@Author : Sahil
     * Date : 26 Apr 2019
     *
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
     *
     * Example 1:
     *
     * Input: [1,3,4,2,2]
     * Output: 2
     * Example 2:
     *
     * Input: [3,1,3,4,2]
     * Output: 3
     * Note:
     *
     * You must not modify the array (assume the array is read only).
     * You must use only constant, O(1) extra space.
     * Your runtime complexity should be less than O(n2).
     * There is only one duplicate number in the array, but it could be repeated more than once.
     *
     *
     *References :
     * https://leetcode.com/problems/find-the-duplicate-number/solution/
     *
     * Solution :
     * 1. Same as cycle in a linked list
     * 2. take 2 pointers, slow & fast
     *
     * slow = nums[i];  //one step
     * fast = nums[mums[i] //two step
     *
     * if slow==fast -> then cycle exist
     *
     * to check the starting point of the cycle
     * 1. keep fast point, where you left earlier
     * 2. start slow pointer again from 0th index
     * 3. Now when slow == fast, we get the starting point [Note now slow and fast both will move 1 index]
     *
     */

    public int findDuplicate(int[] nums) {

        if (nums.length <= 1)
            return -1;

        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;

    }

    public static void main(String[] args) {
        DuplicateNumber duplicateNumber = new DuplicateNumber();
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println(duplicateNumber.findDuplicate(nums));

    }
}
