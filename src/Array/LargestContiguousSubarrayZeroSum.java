package Array;

import java.util.HashMap;

public class LargestContiguousSubarrayZeroSum {
    /*
     *@Author : Sahil
     *Date : 12 Apr 2018
     *
     * Given an array of integers, find length of the largest subarray with sum equals to 0.
     *
     * Examples:
     *
     * Input: arr[] = {15, -2, 2, -8, 1, 7, 10, 23};
     * Output: 5
     * The largest subarray with 0 sum is -2, 2, -8, 1, 7
     *
     * Input: arr[] = {1, 2, 3}
     * Output: 0
     * There is no subarray with 0 sum
     *
     * Input: arr[] = {1, 0, 3}
     * Output: 1
     *
     * Solution : (Using Hashmap) O(N)
     *
     * Step 1 : Change the array such that a[i]=a[i]+a[i-1]
     * Step 2 : Two cases arises Now :
     *          Case 1 : Contiguous sum subarray can be started from 0 index it self for eg 1,2,-3 -> ans -> 3
     *                      in that case search for 0 in that modified array and return its index+1
     *                      in this case modified array is : 1,3,0 so 0's index which is 2+1=3 is the answer
     *          Case 2 : Store every element in the hashmap where key is number itself and value is its index
     *                      i.e (key,Value)-->(num,index).. If at any point num already exist then answer is index
     *
     * References :
     * https://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/
     * https://stackoverflow.com/questions/5534063/zero-sum-subarray
     */

    public int largestContiguousSubarrayZeroSum(int nums[]) {

        int max = 0;
        HashMap<Integer, Integer> hashMap = new HashMap();

        //From index 1 , apply Step 1 described above in comments
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        //Step 2
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            //Case 1
            if (num == 0) {
                max = Math.max(max, i + 1);
            } else if (hashMap.containsKey(num)) {
                //Case 2
                max = Math.max(max, i - hashMap.get(num));
            } else {
                hashMap.put(num, i);
            }

        }
        return max;
    }

    public static void main(String args[]) {
        LargestContiguousSubarrayZeroSum large = new LargestContiguousSubarrayZeroSum();
        int arr[] = {15, -2, 2, -8, 1, 7, 10, 23};
        System.out.print("Largest Size of subarray of 0 sum : " + large.largestContiguousSubarrayZeroSum(arr));
    }
}
