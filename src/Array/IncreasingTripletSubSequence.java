package Array;

public class IncreasingTripletSubSequence {
    /*
     *@Author : Sahil
     * Date : 31 March 2018
     *
     * Find a sorted subsequence of size 3 in linear time
     * Given an array of n integers, find the 3 elements such that a[i] < a[j] < a[k] and i < j < k in 0(n) time.
     * If there are multiple such triplets, then print any one of them.
     * Input:  arr[] = {12, 11, 10, 5, 6, 2, 30}
     * Output: 5, 6, 30
     *
     * Input:  arr[] = {1, 2, 3, 4}
     * Output: 1, 2, 3 OR 1, 2, 4 OR 2, 3, 4
     *
     * Input:  arr[] = {4, 3, 2, 1}
     * Output: No such triplet
     * References :
     * https://www.geeksforgeeks.org/find-a-sorted-subsequence-of-size-3-in-linear-time/
     * https://leetcode.com/problems/increasing-triplet-subsequence/discuss/
     *
     * Solution :
     * Algo has 3 steps
     * 1. keep track of Minimum element uptill now
     * 2. Keep track of second minimum element uptill no
     * 3. As soon as you get the third minimum greater than 1&2 you get the increasing subsequence.
     */

    public boolean sequenceAvailable(int arr[]) {
        boolean answer = false;
        int lowest = Integer.MAX_VALUE;
        int secondLowest = Integer.MAX_VALUE;
        int highest = 0;
        int saveLowest = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < lowest) {
                lowest = arr[i];
            } else if (arr[i] < secondLowest) {
                saveLowest = lowest;     //Actual lowest can be lost, when we get lowest value just before highest value
                secondLowest = arr[i];
            } else {
                highest = arr[i];
                System.out.println(saveLowest + "," + secondLowest + "," + highest);
                return true;
            }
        }
        return answer;
    }

    public static void main(String args[]) {
        IncreasingTripletSubSequence increasing = new IncreasingTripletSubSequence();
        int arr[] = {12, 11, 10, 5, 6, 2, 30};
        System.out.print(increasing.sequenceAvailable(arr));
    }
}
