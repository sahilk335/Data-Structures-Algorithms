package Array;

import java.util.Arrays;

public class Candy {
    /*
     *@Author : Sahil
     * Date : 07 May 2019
     *
     * There are N children standing in a line. Each child is assigned a rating value.
     *
     * You are giving candies to these children subjected to the following requirements:
     *
     * Each child must have at least one candy.
     * Children with a higher rating get more candies than their neighbors.
     * What is the minimum candies you must give?
     *
     * Example 1:
     *
     * Input: [1,0,2]
     * Output: 5
     * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
     * Example 2:
     *
     * Input: [1,2,2]
     * Output: 4
     * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
     *              The third child gets 1 candy because it satisfies the above two conditions.
     *
     *
     *  Solution
     *
     *  1. initialise left array with 1 and right array with 1
     *  2. now in left array if previous rank is smaller than increment previous candy +1
     *  3. now in right array from end. if preious cand is smaller than increment previosu candy +1
     *  4. maximum of left and right candy is the answer
     *
     */
    public int candy(int[] ratings) {
        int sum = 0;
        int[] left2right = new int[ratings.length];
        int[] right2left = new int[ratings.length];

        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i + 1] < ratings[i]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }

        for (int i = 0; i < ratings.length; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }

        return sum;
    }

    public static void main(String[] args) {
        Candy candy = new Candy();
        int[] nums = {1, 0, 2};
        System.out.println(candy.candy(nums));

    }
}
