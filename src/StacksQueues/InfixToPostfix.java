package StacksQueues;

import java.util.Stack;

public class InfixToPostfix {
    /*
     *
     * @Author : Sahil
     * Date : 20 Apr 2019
     *
     * Infix to Postfix
     *
     *1.if operand, add to output
     *2. if operator add to stack after step 2.1
     *      2.1 if top element has higher or same priority , pop them from stack and add to result
     *3. if scanned is not letter or digit , check for "(" & ")"
     *4. if not breakets then it is operator.
     *5. At the end add all left operators to result
     *
     * References :
     * https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
     *
     */

    public int priority(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            char curr = exp.charAt(i);
            if (Character.isLetterOrDigit(curr)) {
                result += exp.charAt(i);
            } else {//It is opertor or brackets
                if (curr == '(') {
                    stack.push(curr);
                } else if (curr == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        result += stack.pop();
                    }
                    if (!stack.isEmpty() && stack.peek() != '(')
                        return "Invalid Expressions";
                    else
                        stack.pop(); //remove "(" from stack as well
                } else { //operator encounterd now add according to priority already kept
                    while (!stack.isEmpty() && priority(stack.peek()) >= priority(curr)) {
                        result += stack.pop();
                    }
                    stack.push(curr);
                }

            }
        }

        //for left over operands in stack
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        InfixToPostfix infixToPostfix = new InfixToPostfix();
        System.out.println("Postfix Notation :" + infixToPostfix.infixToPostfix("a+b*(c^d-e)^(f+g*h)-i"));
    }
}
