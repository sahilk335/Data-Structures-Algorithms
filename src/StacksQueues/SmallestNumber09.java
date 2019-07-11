package StacksQueues;

import java.util.LinkedList;
import java.util.Queue;

public class SmallestNumber09 {

    /*
     *@Author : Sahil Khurana
     * Date : 11 July 2019
     *
     * Smallest multiple of a given number made of digits 0 and 9 only
     * We are given an integer N. We need to write a program to find the least positive integer X made up of only
     * digits 9’s and 0’s, such that, X is a multiple of N.
     *
     * Note: It is assumed that the value of X will not exceed 106.
     *
     * Examples:
     *
     * Input : N = 5
     * Output : X = 90
     * Exaplanation: 90 is the smallest number made up
     * of 9's and 0's which is divisible by 5.
     *
     * Input : N = 7
     * Output : X = 9009
     * Exaplanation: 9009 is smallest number made up
     * of 9's and 0's which is divisible by 7.
     *
     * Solution : (Using queues)
     * 1. add 9 in the queue , check its divisibilty with N
     * 2. pop from the queue, append 0 & 9 , enqueue in queue
     * 3. Repeat till get answer;
     *
     */
    int smallestNumber(int n) {
        Queue<String> queue = new LinkedList<>();
        queue.add("9");
        while (true) {
            String s = queue.remove();
            if (Integer.parseInt(s) % n == 0)
                return Integer.parseInt(s);
            queue.add(s + '0');
            queue.add(s + '9');
        }
    }

    public static void main(String[] args) {
        SmallestNumber09 smallestNumber09 = new SmallestNumber09();
        System.out.print(smallestNumber09.smallestNumber(7));

    }
}
