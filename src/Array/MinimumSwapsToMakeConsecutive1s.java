package Array;

public class MinimumSwapsToMakeConsecutive1s {
    /*
     *@Author : Sahil
     *Date : 07 Apr 2019
     *
     * Minimum Swaps required to group all 1’s together
     * Given an array of 0’s and 1’s, we need to write a program to find the minimum number of swaps required to group all 1’s present in the array together.
     *
     * Examples:
     *
     * Input : arr[] = {1, 0, 1, 0, 1}
     * Output : 1
     * Explanation: Only 1 swap is required to
     * group all 1's together. Swapping index 1
     * and 4 will give arr[] = {1, 1, 1, 0, 0}
     *
     * Input : arr[] = {1, 0, 1, 0, 1, 1}
     * Output : 1
     *
     * References :
     * https://www.geeksforgeeks.org/minimum-swaps-required-group-1s-together/
     *
     * Solution :
     * 1. First Count the number of 1's in the array . let the count be X
     * 2. Now find subarray of length x of array with maximum numbers of 1's
     * 3. Answer = X - (No. of 0's in the subarray)
     */

    public int minSwaps(int[] arr) {

        int numberofOnes = 0;
        for (int num : arr) {
            if (num == 1)
                numberofOnes++;
        }
        int currWindowOnes = 0;
        int windowSize = numberofOnes;

        //Making first Window that would be slided afterwards
        for (int i = 0; i < numberofOnes; i++) {
            if (arr[i] == 1) {
                currWindowOnes++;
            }
        }
        int maxOnes = currWindowOnes;
        for (int i = 1; i + windowSize <= arr.length; i++) {
            if (arr[i - 1] == 1)        // previous number benefit or loss
                currWindowOnes--;

            if (arr[i + windowSize - 1] == 1) //trailing number benefit or loss
                currWindowOnes++;

            maxOnes = Math.max(maxOnes, currWindowOnes);
        }

        return numberofOnes - maxOnes;

    }

    public static void main(String[] args) {
        MinimumSwapsToMakeConsecutive1s minimumSwapsToMakeConsecutive1s = new MinimumSwapsToMakeConsecutive1s();
        int arr[] = {0, 0, 1, 0, 1, 1, 0, 0, 1};
        System.out.println("Minimum No. of Swaps : " + minimumSwapsToMakeConsecutive1s.minSwaps(arr));

    }
}
