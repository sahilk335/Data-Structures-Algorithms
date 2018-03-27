package DynamicProgramming;

public class CoinChangeTotalWays {
    /*
     *@Author : Sahil
     * Date : 27 March 2018
     *
     * Given a value N, if we want to make change for N cents, and we have infinite supply of each of
     * S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change? The order of coins doesn’t matter.
     *
     * For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4.
     * For N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}.
     * So the output should be 5.
     *
     * References :
     * https://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
     * https://www.youtube.com/watch?v=_fgjrs570YE
     */

    public int coinChangeways(int coins[], int sumTargetWays) {
        //dp array store the number of ways the ith index sum be made using the coins
        int dp[] = new int[sumTargetWays + 1];
        dp[0] = 0;  //Number of ways to make sum 0 with coins x,y,z... is 0

        for (int j = 0; j < coins.length; j++) {
            for (int i = 1; i < dp.length; i++) {
                if (i == coins[j]) {
                    dp[i] = 1 + dp[i];
                    continue;
                }
                if (i - coins[j] > 0) {
                    dp[i] = dp[i] + dp[i - coins[j]];
                }
            }
        }
        return dp[sumTargetWays];
    }

    public static void main(String args[]) {
        CoinChangeTotalWays cc = new CoinChangeTotalWays();
        int coins[] = {1, 2, 3};
        int sumTargetWays = 4;
        System.out.print(cc.coinChangeways(coins, sumTargetWays));

    }
}
