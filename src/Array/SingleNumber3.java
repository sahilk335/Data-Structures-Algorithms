package Array;

public class SingleNumber3 {
    /*
     *@Author : Sahil Khurana
     * Date : 29 July 2019
     *
     * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear
     * exactly twice. Find the two elements that appear only once.
     *
     * Example:
     *
     * Input:  [1,2,1,3,2,5]
     * Output: [3,5]
     * Note:
     *
     * The order of the result is not important. So in the above example, [5, 3] is also correct.
     * Your algorithm should run in linear runtime complexity. Could you implement it using only
     * constant space complexity?
     *
     *
     * References :
     * https://leetcode.com/problems/single-number-iii/
     *
     * Solution :
     * 1. XOR all the elements , this will give us a couple of 2 answers
     * 2. couple has at least one distinct bit..
     * 3. we find rigtmost set bit of the couple.
     * 4. we divide  other elements in 2 groups based on setbit.
     * 5. in the end 2 sets will have the answer
     *
     * Note : This question is same as find missing and repeating number
     *
     *
     */
    public int[] singleNumber(int[] nums) {
        int couple = 0;
        for (int num : nums) {
            couple ^= num;
        }
        int rightmostSetBitForm = couple & (-couple);

        // Make 2 groups x & y
        int x = 0;
        int y = 0;

        for (int num : nums) {
            if ((num & rightmostSetBitForm) == 0) {
                x = x ^ num;
            } else {
                y = y ^ num;
            }
        }

        int[] res = {x, y};
        return res;
    }
}
