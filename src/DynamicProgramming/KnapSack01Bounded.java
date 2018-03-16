package DynamicProgramming;

import java.util.HashMap;

public class KnapSack01Bounded {
    /*
     *@Author : Sahil
     * Date : 15 March 2018
     * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value
     * in the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and
     * weights associated with n items respectively. Also given an integer W which represents knapsack capacity,
     * find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal
     * to W. You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
     * Solution 1 [Top Down]
     *  Topdown DP - https://youtu.be/149WSzQ4E1g
     *
     *  Solution 2 [Bottom Up]
     *  Bottom DP -  https://youtu.be/8LusJS5-AGo
     *
     *  References :
     *  https://www.geeksforgeeks.org/knapsack-problem/
     *
     */

    public int bottomDownKnapsack(int[] wt, int[] val, int W) {

        int T[][] = new int[val.length][W + 1];

        T[0][0] = 0;
        for (int j = 1; j <= W; j++) {
            T[0][j] = val[0];
        }
        for (int i = 1; i < val.length; i++) {
            for (int j = 0; j <= W; j++) {
                if (j == 0) {
                    T[i][j] = 0;
                    continue;
                }
                if (j < wt[i]) {
                    T[i][j] = T[i - 1][j];
                } else {
                    //Either pick the item -> its value + value (of the weight - its weight)
                    //or not pick the item (Its value from the upper row.
                    //maximum of the two value.
                    T[i][j] = Math.max(T[i - 1][j], val[i] + T[i - 1][j - wt[i]]);
                }
            }
        }
        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j <= W; j++) {
                System.out.print(T[i][j] + "\t");
            }
            System.out.print("\n");
        }
        return T[val.length - 1][W];
    }

    class Index {
        int remainingWeight;
        int remainingItem;

        //Since key for the map is Object of the Index which is based on the remaining weight & remaining Item
        // we have to override the
        //equals and hashcode method of this class . Refer OCJP book
        @Override
        public boolean equals(Object o) {
            if (o instanceof Index) {
                return ((Index) o).remainingWeight == this.remainingWeight && ((Index) o).remainingItem == this.remainingItem;
            } else {
                return false;
            }

        }

        @Override
        public int hashCode() {
            return 17 * remainingWeight + remainingItem;
        }
    }


    public int topDownKnapSack(int weight[], int val[], int W) {
        HashMap<Index, Integer> map = new HashMap<>();
        return topDownKnapSackUtil(W, 0, weight.length, val, weight, map);
    }

    public int topDownKnapSackUtil(int remainingWeight, int currItemIdx, int totalItem, int val[], int weight[],
                                   HashMap<Index, Integer> map) {

        if (remainingWeight <= 0 || currItemIdx >= totalItem) {
            return 0;
        }

        Index key = new Index();
        key.remainingItem = totalItem - currItemIdx - 1;
        key.remainingWeight = remainingWeight;

        if (map.containsKey(key)) {
            int maxValue = map.get(key);
            return maxValue;
        }

        int maxValue;

        if (remainingWeight < weight[currItemIdx]) {
            //then do not include the item, i.e no reduction in weight just skip this currIdx to +1
            maxValue = topDownKnapSackUtil(remainingWeight, currItemIdx + 1, totalItem, val, weight, map);
        } else {
            //Case 1 : include the currItem .i.e increment currIdx +1 , include its value, and decrease its weight from
            //remaining weight
            //Or Case 2: do not include it ,i.e. no redcution in weight, no increment in value,just skip this index
            //return maximum of case 1 and case 2;

            int include = val[currItemIdx] + topDownKnapSackUtil(remainingWeight - weight[currItemIdx],
                    currItemIdx + 1, totalItem, val, weight, map);
            int doNotInclude = topDownKnapSackUtil(remainingWeight, currItemIdx + 1, totalItem, val, weight, map);
            return Math.max(include, doNotInclude);
        }
        //With current Item and current weight left, save the maximum value we can get in the map
        map.put(key, maxValue);
        return maxValue;
    }

    public static void main(String args[]) {
        int val[] = {1, 4, 5, 7};
        int wt[] = {1, 3, 4, 5};
        //Bottom up matrix
        /*
         * 0	1	1	1	1	1	1	1
         * 0	1	1	4	5	5	5	5
         * 0	1	1	4	5	6	6	9
         * 0	1	1	4	5	7	8	9<-- this is the answer
         */
        KnapSack01Bounded knapSack01Bounded = new KnapSack01Bounded();
        System.out.print(knapSack01Bounded.bottomDownKnapsack(wt, val, 7));

    }
}