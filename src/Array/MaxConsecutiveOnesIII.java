package Array;

public class MaxConsecutiveOnesIII {

    /**
     * Problem : https://leetcode.com/problems/max-consecutive-ones-iii/
     *
     *
     *
     * Solution :
     * 1. Slide left window until number of zero is reduced to k.
     * 2. Keep calculating Ans with max diff between currPos and wL (left window)
     *
     * @param arr
     * @param k
     * @return
     */


    public int longestOnes(int[] arr, int k) {
        int wL = 0; //left window
        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                k--;   //reduce number of Zero as soon as you encounter
            }
            while (k < 0) {         //Slide left window until number of zero reaches value = k
                if (arr[wL] == 0) {
                    k++;
                }
                wL++;
            }
            ans = Math.max(ans, i - wL + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnesIII test = new MaxConsecutiveOnesIII();
        int arr[] = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        System.out.println(test.longestOnes(arr, k));
    }
}
