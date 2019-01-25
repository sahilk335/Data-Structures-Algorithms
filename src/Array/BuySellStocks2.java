package Array;

/*
 * Source : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BuySellStocks2 {

    public int maxProfit(int arr[]) {
        if (arr.length <= 0) {
            return 0;
        }
        int profit = 0;
        int valley = arr[0];
        int peak = arr[0];
        for (int i = 0; i < arr.length; i++) {
            while (i < arr.length - 1 && arr[i] >= arr[i + 1]) {
                i++;
            }
            valley = arr[i];

            while (i < arr.length - 1 && arr[i] <= arr[i + 1]) {
                i++;
            }
            peak = arr[i];
            profit += peak - valley;
        }
        return profit;
    }

    public static void main(String args[]) {
        int arr[] = {7, 1, 5, 3, 6, 4};
        BuySellStocks2 bss = new BuySellStocks2();
        System.out.print("Maximum profit : " + bss.maxProfit(arr));
    }
}
