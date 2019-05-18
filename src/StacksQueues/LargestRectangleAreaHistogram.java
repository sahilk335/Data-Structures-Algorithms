package StacksQueues;

import java.util.Stack;

public class LargestRectangleAreaHistogram {
    /*
     *@Author : Sahil
     * Date : 17 Feb 2018
     *
     * References : https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
     *
     * Solution 1 : Dynamic Programming
     *For any bar i the maximum rectangle is of width r - l - 1 where r - is the last coordinate of the bar to the
     * right with height h[r] >= h[i] and l - is the last coordinate of the bar to the left which height h[l] >= h[i]
     *
     * So if for any i coordinate we know his utmost higher (or of the same height) neighbors to the right and to the
     * left, we can easily find the largest rectangle:
     *
     * int maxArea = 0;
     * for (int i = 0; i < height.length; i++)
     *     maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
     *
     *
     * Solution 2 : Using Stacks
     *
     *  * Maintain a stack
     *
     * If stack is empty or value at index of stack is less than or equal to value at current
     * index, push this into stack.
     * Otherwise keep removing values from stack till value at index at top of stack is
     * less than value at current index.
     * While removing value from stack calculate area
     * if stack is empty
     * it means that till this point value just removed has to be smallest element
     * so area = input[top] * i
     * if stack is not empty then this value at index top is less than or equal to
     * everything from stack top + 1 till i. So area will
     * area = input[top] * (i - stack.peek() - 1);
     * Finally maxArea is area if area is greater than maxArea.
     */

    //Solution 1
    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
        lessFromRight[height.length - 1] = height.length;
        lessFromLeft[0] = -1;
//Less From left array stores at position i stores the index of the bar whose height is just less than its current
// hieght from left direction
        for (int i = 1; i < height.length; i++) {
            int p = i - 1;

            while (p >= 0 && height[p] >= height[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }
//similarly fron right side
        for (int i = height.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < height.length && height[p] >= height[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        for (int i = 0; i < height.length; i++)
            System.out.print(lessFromLeft[i]);
        System.out.print("\n\n");
        for (int i = 0; i < height.length; i++)
            System.out.print(lessFromRight[i]);

        return maxArea;
    }

    //Solution 2
    public int largestRectangleAreaStack(int[] h) {
        int n = h.length, i = 0, max = 0;

        Stack<Integer> s = new Stack<>();

        while (i < n) {
            // as long as the current bar is shorter than the last one in the stack
            // we keep popping out the stack and calculate the area based on
            // the popped bar
            while (!s.isEmpty() && h[i] < h[s.peek()]) {
                // tricky part is how to handle the index of the left bound
                max = Math.max(max, h[s.pop()] * (i - (s.isEmpty() ? 0 : s.peek() + 1)));
            }
            // put current bar's index to the stack
            s.push(i++);
        }

        // finally pop out any bar left in the stack and calculate the area based on it
        while (!s.isEmpty()) {
            max = Math.max(max, h[s.pop()] * (n - (s.isEmpty() ? 0 : s.peek() + 1)));
        }

        return max;
    }

    public static void main(String args[]) {
        int height[] = {2, 1, 5, 6, 2, 3};
        LargestRectangleAreaHistogram largestRectangleAreaHistogram = new LargestRectangleAreaHistogram();
        System.out.println("\n" + largestRectangleAreaHistogram.largestRectangleArea(height));
        System.out.println("\n" + largestRectangleAreaHistogram.largestRectangleAreaStack(height));
    }

}
