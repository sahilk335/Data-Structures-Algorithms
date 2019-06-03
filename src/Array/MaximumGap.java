package Array;

import java.util.Arrays;

public class MaximumGap {
    /*
     *@Author : Sahil Khurana
     * Date : 03 June 2019
     * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
     *
     * Return 0 if the array contains less than 2 elements.
     *
     * Example 1:
     *
     * Input: [3,6,9,1]
     * Output: 3
     * Explanation: The sorted form of the array is [1,3,6,9], either
     *              (3,6) or (6,9) has the maximum difference 3.
     * Example 2:
     *
     * Input: [10]
     * Output: 0
     * Explanation: The array contains less than 2 elements, therefore return 0.
     *
     *
     * Solution :
     * 1 .  there are N elements in the array, the min value is min and the max value is max.
     *      Then the maximum gap will be no smaller than ceiling[(max - min ) / (N - 1)
     * 2.    gap = ceiling[(max - min ) / (N - 1)]
     * 3.   Based on this, we only need to compare max number in this bucket and min number in next bucket to get the
     *      relatively large gap and find out which two bucket have the largest gap.
     * 4 .  basically we argue that the largest gap can not be smaller than (max-min)/(N-1), so if we make the buckets
     *      smaller than this number, any gaps within the same bucket is not the amount we are looking for, so we are
     *      safe to look only for the inter-bucket gaps.
     *      so making the buckets smaller doesn't affect the correctness.
     * 5. Now let's say that we distribute the numbers into N buckets. The hashing function used is :
     *
     * H(n) = int(n−min(A)gap), where 'gap' is defined as above
     *
     * The above function, assigns each number to one of the N buckets.
     *
     * Refrences :
     * http://www.stokastik.in/leetcode-maximum-gap/
     * https://leetcode.com/problems/maximum-gap/
     *
     */

    public int maximumGap(int[] num) {
        if (num == null || num.length < 2)
            return 0;
        // get the max and min value of the array
        int min = num[0];
        int max = num[0];
        for (int i : num) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        // the minimum possibale gap, ceiling of the integer division
        int gap = (int) Math.ceil((double) (max - min) / (num.length - 1));
        int[] bucketsMIN = new int[num.length - 1]; // store the min value in that bucket
        int[] bucketsMAX = new int[num.length - 1]; // store the max value in that bucket
        Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
        Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
        // put numbers into buckets
        for (int i : num) {
            if (i == min || i == max)
                continue;
            int idx = (i - min) / gap; // index of the right position in the buckets
            bucketsMIN[idx] = Math.min(i, bucketsMIN[idx]);
            bucketsMAX[idx] = Math.max(i, bucketsMAX[idx]);
        }
        // scan the buckets for the max gap
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        for (int i = 0; i < num.length - 1; i++) {
            if (bucketsMIN[i] == Integer.MAX_VALUE && bucketsMAX[i] == Integer.MIN_VALUE)
                // empty bucket
                continue;
            // min value minus the previous value is the current gap
            maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
            // update previous bucket value
            previous = bucketsMAX[i];
        }
        maxGap = Math.max(maxGap, max - previous); // updata the final max value gap
        return maxGap;

    }

    public static void main(String[] args) {
        MaximumGap maximumGaps = new MaximumGap();
        int[] nums = {3, 6, 9, 1};
        System.out.println(maximumGaps.maximumGap(nums));
    }


}
