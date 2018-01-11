package Array;

public class MaximumDifference2Numbers {
    /*
     *Source : https://www.geeksforgeeks.org/maximum-difference-between-two-elements/
     * 
     *Maximum difference between two elements such that larger element appears after the smaller number
     * Given an array arr[] of integers, find out the difference between any two elements such that larger element appears
     * after the smaller number in arr[].
     * Examples: If array is [2, 3, 10, 6, 4, 8, 1] then returned value should be 8 (Diff between 10 and 2).
     * If array is [ 7, 9, 5, 6, 3, 2 ] then returned value should be 2 (Diff between 7 and 9)
     *A more clear explanation on why sum of subarray works.:

     * Suppose we have original array:
     * [a0, a1, a2, a3, a4, a5, a6]
     *
     * what we are given here(or we calculate ourselves) is:
     * [b0, b1, b2, b3, b4, b5, b6]
     *
     * where,
     * b[i] = 0, when i == 0
     * b[i] = a[i] - a[i - 1], when i != 0
     *
     * suppose if a2 and a6 are the points that give us the max difference (a2 < a6)
     * then in our given array, we need to find the sum of sub array from b3 to b6.
     *
     * b3 = a3 - a2
     * b4 = a4 - a3
     * b5 = a5 - a4
     * b6 = a6 - a5
     *
     * adding all these, all the middle terms will cancel out except two
     * i.e.
     *
     * b3 + b4 + b5 + b6 = a6 - a2
     *
     * a6 - a2 is the required solution.
     *
     * so we need to find the largest sub array sum to get the most profit
     * Solution : Use Kadane's Algorithm
     *
     *
     */
    int maximumDifference(int arr[]) {
        int maxCurr = 0;
        int maxsoFar = 0;
        for (int i = 1; i < arr.length; i++) {
            maxCurr = Math.max(0, maxCurr += arr[i] - arr[i - 1]);
            maxsoFar = Math.max(maxCurr, maxsoFar);
        }
        return maxsoFar;
    }

    public static void main(String args[]) {
        int arr[] = {7, 9, 5, 6, 11, 17};
        MaximumDifference2Numbers mxd = new MaximumDifference2Numbers();
        System.out.print("Maximum difference : " + mxd.maximumDifference(arr));

    }
}
