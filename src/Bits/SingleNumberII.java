package Bits;

public class SingleNumberII {
    /*
     *@Author : Sahil Khurana
     * Date : 22 June 2019
     *
     * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
     *
     * Note:
     *
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     *
     * Example 1:
     *
     * Input: [2,2,3,2]
     * Output: 3
     * Example 2:
     *
     * Input: [0,1,0,1,0,1,99]
     * Output: 99
     *
     * Solution :
     * https://www.youtube.com/watch?v=mHfvInveXDQ
     */

    public int singleNumber(int[] nums) {

        int res = 0;
        for(int i = 0; i < 32; i++){
            int sum = 0;
            for(int n: nums)
                if((n >> i & 1) == 1)
                    sum++;
            sum %= 3;
            res |= sum<<i;
        }
        return res;

    }
}
