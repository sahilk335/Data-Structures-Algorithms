package Array;

/*
 * Source : https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 *
 *Say you have an array for which the ith element is the price of a given stock on day i.

 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * Example 1:
 * Input: [7, 1, 5, 3, 6, 4]
 * Output: 5

 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 *
 * Solution references : Followed peek value approach
 *
 * https://leetcode.com/articles/best-time-buy-and-sell-stock/
 */

public class BuySellStocks {
    int profitBuySellStockI(int arr[]) {
        int profit = 0;
        int minsofar = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            minsofar = Math.min(minsofar, arr[i]);
            profit = Math.max(profit, arr[i] - minsofar);
        }
        return profit;
    }

    public static void main(String args[]) {
        int arr[] = {7, 1, 5, 3, 6, 4};
        BuySellStocks bss = new BuySellStocks();
        System.out.print("Maximum profit for Buy Sell stock I " + bss.profitBuySellStockI(arr));

    }
}
