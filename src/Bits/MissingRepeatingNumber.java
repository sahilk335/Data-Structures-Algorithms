package Bits;

public class MissingRepeatingNumber {
    /*
     *@Author : Sahil Khurana
     * Date : 09 July 2019
     *
     * Find the repeating and the missing | Added 3 new methods
     * Given an unsorted array of size n. Array elements are in the range from 1 to n. One number from set {1, 2, …n}
     * is missing and one number occurs twice in the array. Find these two numbers.
     * Examples:
     *
     *
     * Input: arr[] = {3, 1, 3}
     * Output: Missing = 2, Repeating = 3
     * Explanation: In the array,
     * 2 is missing and 3 occurs twice
     *
     * Input: arr[] = {4, 3, 6, 2, 1, 1}
     * Output: Missing = 5, Repeating = 1
     *
     * References :
     * https://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/
     *
     * Solution :
     * 1. xor all the numbers in the array with the the numbers from 1 to n
     * 2. res = x^y .. result obtained would be missing^repeating number
     * 3. Now get the rightmost set bit of res ( you can get any set bit you want )
     *      rightmost set bit is (res & -res)
     * 4. Now take two sets x and y.. if right most bit is 1 then put in x else put in y
     * 5. after completion x and y will be missing and repeating
     * 6. One another step is required to check which is repeating and which is missing
     *
     * Time Complexity : O(N)
     * Space Complexity : O(1)
     *
     */
    void getTwoElements(int nums[]) {

        int res = nums[0];
        int n = nums.length;
        int x = 0;
        int y = 0;
        for (int i = 1; i < n; i++) {
            res = res ^ nums[i];
        }
        for (int i = 1; i <= n; i++) {
            res = res ^ i;
        }

        //Now res =x^y
        //get the right most set bit

        int rightMostSetBit = res & -res;
        for (int i = 0; i < n; i++) {
            if ((nums[i] & rightMostSetBit) == 1) {
                x = x ^ nums[i];
            } else {
                y = y ^ nums[i];
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & rightMostSetBit) == 1) {
                x = x ^ i;
            } else {
                y = y ^ i;
            }
        }

        System.out.print("Missing and repeating numbers are :" + x + "," + y);
    }

    public static void main(String[] args) {
        MissingRepeatingNumber missingRepeatingNumber = new MissingRepeatingNumber();
        int[] nums = {1, 2, 3, 1};
        missingRepeatingNumber.getTwoElements(nums);
    }
}
