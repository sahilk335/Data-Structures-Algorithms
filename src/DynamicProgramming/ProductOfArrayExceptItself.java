package DynamicProgramming;

import java.util.Arrays;

public class ProductOfArrayExceptItself {
    /*
     *@Author : Sahil
     * Date : 07 May 2019
     *
     * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the
     * product of all the elements of nums except nums[i].
     *
     * Example:
     *
     * Input:  [1,2,3,4]
     * Output: [24,12,8,6]
     * Note: Please solve it without division and in O(n).
     *
     * Solution :
     *
     * 1.  for any number if we know preMultiplication of left and its left.. and then we multiply those number
     *      then that will be the answer for it example
     *
     *      xxxx 4 yyyyy
     *
     *      So for 4  xxxxx multiply by yyyyy will be the answer
     * 2. For this we will traverse first left and then from right and then multiply those value
     *
     * 3. This will give us result.
     */

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] leftMul = new int[n];
        int[] res = new int[n];


        leftMul[0] = 1; //because for 1st element , left boundary is 1
        for (int i = 1; i < n; i++) {
            leftMul[i] = leftMul[i - 1] * nums[i - 1];
        }
        int[] rightmul = new int[n];
        rightmul[n - 1] = 1;
        res[n - 1] = leftMul[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightmul[i] = rightmul[i + 1] * nums[i + 1];
            res[i] = leftMul[i] * rightmul[i];
        }

        return res;
    }


    public static void main(String[] args) {
        ProductOfArrayExceptItself multiplier = new ProductOfArrayExceptItself();
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(multiplier.productExceptSelf(nums)));

    }
}
