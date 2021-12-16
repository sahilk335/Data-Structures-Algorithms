package StacksQueues;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinimumRemoveValidParanthesis {

    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s);
        int currIdx = 0;
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() && s.charAt(i) == ')') {
                stringBuilder.deleteCharAt(i - currIdx);
                currIdx++;
                continue;
            }
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')' && s.charAt(stack.peek()) == '(') {
                stack.pop();
            }
        }

        while (stack.size() > 0) {
            stringBuilder.deleteCharAt(stack.pop() - currIdx);
        }

        return stringBuilder.toString();

    }

    public static void main(String[] args) {
        MinimumRemoveValidParanthesis minimumRemoveValidParanthesis = new MinimumRemoveValidParanthesis();
        System.out.println(minimumRemoveValidParanthesis.minRemoveToMakeValid("))(("));
    }
}
