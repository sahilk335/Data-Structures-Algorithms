package Bits;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GenerateBinaryNumber {
    /*
     * @Author : Sahil Khurana
     * Date : 09 July 2019
     *
     * Given a number n, write a function that generates and prints all binary numbers with decimal values from 1 to n.
     * Examples:
     *
     * Input: n = 2
     * Output: 1, 10
     *
     * Input: n = 5
     * Output: 1, 10, 11, 100, 101
     *
     * Solution :
     * 1. Put 1 in the queue
     * 2. pop from queue , and add 0 & 1 to the popped out string and enqueue it back
     * 3. Repeat till requirement
     *
     */

    public List<String> generatePrintBinary(int n) {
        Queue<String> queue = new LinkedList<>();
        List<String> res = new ArrayList<>();
        queue.add("1");
        while (n-- > 0) {
            String s = queue.remove();
            res.add(s);
            queue.add(s + '0');
            queue.add(s + '1');
        }
        return res;
    }

    public static void main(String[] args) {
        GenerateBinaryNumber generateBinaryNumber = new GenerateBinaryNumber();
        List<String> res = generateBinaryNumber.generatePrintBinary(10);
        for (String s : res) {
            System.out.print(s + " , ");
        }
    }
}
