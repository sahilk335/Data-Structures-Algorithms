package DynamicProgramming;

public class BinomialCoefficient {
    /*
     *@Author : Sahil
     * Date 22 Apr 2018
     *
     * Calculate the value of NCr
     *
     * Formula :
     * nCr = n-1Cr-1 + n-1Cr
     *
     * Solution:
     * Using Dynamic programming
     */

    public long binomialRecursive(int n, int r) {
        if (r == 0 || n == r) {
            return 1;
        }
        return binomialRecursive(n - 1, r - 1) + binomialRecursive(n - 1, r);
    }


    public long binomialDP(int n, int r) {
        int[][] dp = new int[n + 1][r + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, r); j++) {
                if (j == 0 || j == i) {
                    dp[i][j] = 1;  //nCr=nCn-r  -> nCn = 1
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }

        return dp[n][r];
    }

    // Returns value of Binomial Coefficient C(n, k)
    static int binomialCoeff(int n, int k) {
        int res = 1;

        // Since C(n, k) = C(n, n-k)
        if (k > n - k)
            k = n - k;

        // Calculate value of [n * (n-1) *---* (n-k+1)] / [k * (k-1) *----* 1]
        for (int i = 0; i < k; ++i) {
            res *= (n - i);
            res /= (i + 1);
        }

        return res;
    }

    public static void main(String args[]) {
        BinomialCoefficient binomial = new BinomialCoefficient();
        System.out.print(binomialCoeff(5, 2));

    }
}
