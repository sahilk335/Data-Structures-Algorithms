package Strings;

import java.util.Arrays;

public class LargestNumber {
    /*
     *@Author : Sahil
     * Date : 02 April 2019
     *
     * Given a list of non negative integers, arrange them such that they form the largest number.
     *
     * For example:
     *
     * Given [3, 30, 34, 5, 9], the largest formed number is 9534330.
     *
     * Note: The result may be very large, so you need to return a string instead of an integer.
     *
     * Solution :
     *
     *
     * References :
     * https://leetcode.com/problems/largest-number/solution/
     */

    public String largestNumber(int[] num) {
        //Covert number to string
        String[] arrToStr = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            arrToStr[i] = String.valueOf(num[i]);
        }

        Arrays.sort(arrToStr, (a, b) -> {
            return (b + a).compareTo(a + b);
        });

        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if (arrToStr[0].equals("0")) {
            return "0";
        }
        String largestNumber = new String();
        for (int i = 0; i < arrToStr.length; i++) {
            largestNumber += arrToStr[i];
        }

        return largestNumber;

    }


    public static void main(String[] args) {
        LargestNumber largestNumber = new LargestNumber();
        int[] num = {3, 30, 34, 5, 9};
        System.out.println(largestNumber.largestNumber(num));

    }
}
