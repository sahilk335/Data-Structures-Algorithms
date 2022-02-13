public class MaximumConsecutiveOne3 {
    /**
     * https://leetcode.com/problems/max-consecutive-ones-iii/
     *
     * Solution :
     * 1. Take 2 pointers i & j
     * 2. i moves with for loop
     * 3. as soon as nums[i] == 0, decerement k
     * 4. when k is negative , then start incrementing j
     * 5. if(j==0) then increment k as we are leaving 0.
     * 6. only when K>=0 , we keep on updating ans (i-j+1)
     *
     *
     * Author : Sahil Khurana
     * Date : Feb 12 2022
     */

    public int longestOnes(int[] nums, int k) {

        int j = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                k--;
            }
            if (k >= 0) {
                ans = Math.max(ans, i - j + 1);
            }else{
                if (nums[j] == 0) {
                    k++;
                }
                j++;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        MaximumConsecutiveOne3 maximumConsecutiveOne3 = new MaximumConsecutiveOne3();
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        System.out.println(maximumConsecutiveOne3.longestOnes(nums, 2));
    }
}
