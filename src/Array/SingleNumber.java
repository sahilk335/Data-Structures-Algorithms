package Array;

public class SingleNumber {
    /*
     *@Author : Sahil Khurana
     * Date : 29 July 2019
     *
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     *
     * Note:
     *
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     *
     * Example 1:
     *
     * Input: [2,2,1]
     * Output: 1
     * Example 2:
     *
     * Input: [4,1,2,1,2]
     * Output: 4
     *
     * References :
     * https://leetcode.com/problems/single-number/
     *
     * Solution :
     * res = Xor all the elements
     *
     */

    public int singleNumber(int[] A) {
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            result ^= A[i];
        }
        return result;
    }

}
