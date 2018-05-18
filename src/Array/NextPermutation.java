package Array;

import java.util.Arrays;


public class NextPermutation {
    /*
     *@Author : Sahil
     * Date : 18 May 2018
     * Implement next permutation, which rearranges numbers into the lexicographically
     * next greater permutation of numbers.
     *
     * If such arrangement is not possible, it must rearrange it as the lowest possible order
     * (ie, sorted in ascending order).
     *
     * The replacement must be in-place and use only constant extra memory.
     *
     * Here are some examples. Inputs are in the left-hand column and
     * its corresponding outputs are in the right-hand column.
     *
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     *
     * References :
     * https://leetcode.com/problems/next-permutation/description/
     *
     * Solution :
     * 1. If number is alredy in descending order, then there is no next greater permutation
     * 2. Else if not -> there exist its next greater permutation
     * 3. Find first number from the right side , that is not following descending order
     * 4. Search number that is greater than the number found in step 3 and swap it
     * 5. Now sort the integers after the number found in step 3, not including itself
     *
     *
     *
     *
     *
     */

    public void nextPermutation(int[] A) {
        if (A == null || A.length <= 1)
            return;

        int i = A.length - 2;

        while (i >= 0 && A[i] >= A[i + 1])
            i--; // Find 1st id i that breaks descending order

        if (i >= 0) {
            // If not entirely descending
            int j = A.length - 1;

            // Start from the end
            while (A[j] <= A[i])
                j--;           // Find rightmost first larger id j
            swap(A, i, j);                     // Switch i and j
        }

        reverse(A, i + 1, A.length - 1);       // Reverse the descending sequence

    }

    public void swap(int[] arr, int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    public void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }


    public static void main(String args[]) {
        NextPermutation nextPermutation = new NextPermutation();
        int nums[] = {1, 2, 3};
        nextPermutation.nextPermutation(nums);
        Arrays.stream(nums).forEach(num -> System.out.print(num + " "));


    }
}
