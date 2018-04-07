package DynamicProgramming;

public class MaximumProductRopeCutting {
    /*
     *@Author : Sahil
     * Date : 08 Apr 2018
     *
     * Given a rope of length n meters, cut the rope in different parts of integer lengths in a way that
     * maximizes product of lengths of all parts. You must make at least one cut.
     * Assume that the length of rope is more than 2 meters.
     *
     *
     * Input: n = 2
     * Output: 1 (Maximum obtainable product is 1*1)
     *
     * Input: n = 3
     * Output: 2 (Maximum obtainable product is 1*2)
     *
     * Input: n = 4
     * Output: 4 (Maximum obtainable product is 2*2)
     *
     * Input: n = 5
     * Output: 6 (Maximum obtainable product is 2*3)
     *
     * Input: n = 10
     * Output: 36 (Maximum obtainable product is 3*3*4)
     *
     * References :
     * https://www.geeksforgeeks.org/dynamic-programming-set-36-cut-a-rope-to-maximize-product/
     * https://algorithms.tutorialhorizon.com/dynamic-programming-maximum-product-cutting-problem/
     *
     *Solution 1 : Recursive
     *
     * Check the products of all possible cuts can be made in the rope and return the maximum product.
     * Since for every length there are two options, either a cut to be made or not. Solve the problem for
     * both options and choose maximum.
     * After Making the cut the further options are ,
     * Either this cut will produce the max product OR we need to make further cuts
     */
    public int maxProductRecursive(int n) {
        if (n == 0 || n == 1)
            return 0;

        int answer = 0;

        for (int i = 2; i < n; i++) {
            //After Making the cut the further options are ,
            // Either this cut will produce the max product OR we need to make further cuts
            answer = Math.max(answer, Math.max((i * (n - i)), (i * maxProductRecursive(n - i))));
        }

        return answer;
    }

    public int maxProductDp(int n) {
        int dp[] = new int[n + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MIN_VALUE;
        }

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
            }
        }

        return dp[n];
    }

    public static void main(String args[]) {
        MaximumProductRopeCutting maxprod = new MaximumProductRopeCutting();
        int n = 10;
        // System.out.print(maxprod.maxProductRecursive(n));
        System.out.print(maxprod.maxProductDp(5));

    }
}
