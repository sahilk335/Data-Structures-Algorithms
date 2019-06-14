package StacksQueues;

import java.util.Stack;

/*
 * @Author : Sahil Khurana
 * Date : 14 June 2019
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 *
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 *
 * References :
 * https://leetcode.com/problems/longest-valid-parentheses/
 * https://www.youtube.com/watch?v=r0-zx5ejdq0
 *
 * Solution :
 * 1. Take Stack , and push index
 * 2. If curr character is closing bracker ')' , and stack peek is '(' opening bracket, then pop it , and calculate answer uptill now.
 * 3. else push the index of current char to the stack
 */
public class LongestValidParanthesis {

    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        if (s.length() == 1)
            return 0;
        stack.push(-1);
        int maxAns = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')' && stack.peek() != -1 && s.charAt(stack.peek()) == '(') {
                stack.pop();
                maxAns = Math.max(maxAns, i - stack.peek());
            } else {
                stack.push(i);
            }
        }
        return maxAns;
    }

    public static void main(String[] args) {
        LongestValidParanthesis longestValidParanthesis = new LongestValidParanthesis();
        System.out.println(longestValidParanthesis.longestValidParentheses(")()())"));
    }
}
