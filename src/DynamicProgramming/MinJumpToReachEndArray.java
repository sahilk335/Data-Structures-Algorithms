package DynamicProgramming;

import java.util.ArrayList;

public class MinJumpToReachEndArray {
    /*
     *@Author : Sahil
     * Date : 27 March 2018
     *
     * Given an array of non negative integers where each element represents the max
     * number of steps that can be made forward from that element. Write a function to
     * return the minimum number of jumps to reach the end of the array from first element
     *
     * References :
     * http://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
     * https://www.youtube.com/watch?v=cETfFsSTGJI
     *
     */

    public int minimumJumps(int steps[]) {
        int jumps[] = new int[steps.length];
        int from[] = new int[steps.length];

        //Initialize all to infinity - 1 beacuse we have to find minimum number of steps
        for (int i = 0; i < steps.length; i++) {
            jumps[i] = Integer.MAX_VALUE - 1;
        }

        //We are standing at 0th index so
        jumps[0] = 0;

        for (int i = 1; i < jumps.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (steps[j] + j >= i) {
                    jumps[i] = Math.min(jumps[i], 1 + jumps[j]);
                    from[i] = j;
                    break;
                }
            }
        }

        //Print path of the jumps
        ArrayList<Integer> stepslist = new ArrayList<>();
        int currIdx = steps.length - 1;
        while (currIdx > 0) {
            stepslist.add(steps[currIdx]);
            currIdx = from[currIdx];
        }
        stepslist.add(steps[0]);
        System.out.print(stepslist);

        return jumps[steps.length - 1];
    }

    public static void main(String args[]) {
        MinJumpToReachEndArray minjump = new MinJumpToReachEndArray();
        int steps[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.out.print(minjump.minimumJumps(steps));
    }

}
