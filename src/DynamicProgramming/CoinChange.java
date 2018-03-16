package DynamicProgramming;

import java.util.Arrays;

public class CoinChange {
    /*
     *@Author : Sahil
     * Date : 16 March 2018
     *
     * Given a value V, if we want to make change for V cents, and we have infinite supply of each of
     * C = { C1, C2, .. , Cm} valued coins, what is the minimum number of coins to make the change?
     *
     *References : https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
     *
     * Solution:
     * 1.Make array of length Sum+1
     * 2.Initialize the first element 0 by 0, as minimum number of coin required to make sum 0 is 0
     * 3. Initialize other elements by infinity
     * 4.Sort the coins array
     * 5.Now if minimum coin value is 3, then start from array index 3 , because 0,1,2 sum will not be contributed by
     * coing 3.
     * 6.Apply this formula :
     *              if (i >= coins[j])
                    T[i] = Math.min(T[i], 1 + T[i - coins[j]]);
     *
     */
    public int minimumCoin(int coins[], int sum) {
        int T[] = new int[sum + 1];
        for (int i = 0; i < T.length; i++) {
            T[i] = Integer.MAX_VALUE;
        }
        //NOTE : the coins array must be sorted
        Arrays.sort(coins);

        T[0] = 0;
        for (int j = 0; j < coins.length; j++) {
            for (int i = 1; i < T.length; i++) {
                //Minimum coins required is using this coin or not using this coin.
                //When using this coin add 1+value of not using this coin [i-coin[j]]
                if (i >= coins[j])
                    T[i] = Math.min(T[i], 1 + T[i - coins[j]]);
            }
        }

        return T[sum];
    }

    public static void main(String args[]) {
        int coins[] = {9, 6, 5, 1};
        int sum = 11;
        CoinChange cc = new CoinChange();
        System.out.print(cc.minimumCoin(coins, sum));
    }
}
