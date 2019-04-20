package StacksQueues;

import java.util.Stack;

public class PostfixToInfix {
    /*
     *@Author : Sahil
     * Date : 20 Apr 2019
     *
     *Postfix to Infix
     * Algorithm
     * 1.While there are input symbol left
     * …1.1 Read the next symbol from the input.
     * 2.If the symbol is an operand
     * …2.1 Push it onto the stack.
     * 3.Otherwise,
     * …3.1 the symbol is an operator.
     * …3.2 Pop the top 2 values from the stack.
     * …3.3 Put the operator, with the values as arguments and form a string.
     * …3.4 Push the resulted string back to stack.
     * 4.If there is only one value in the stack
     * …4.1 That value in the stack is the desired infix string.
     *
     *References :
     * https://www.geeksforgeeks.org/postfix-to-infix/
     * http://scanftree.com/Data_Structure/postfix-to-infix
     */

    String postfixToInfix(String exp) {
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char curr = exp.charAt(i);
            if (Character.isLetterOrDigit(curr)) {
                stack.push(curr + "");
            } else { // in postfix there is no bracket "(",")", so it is operator
                String op1 = stack.pop();
                String op2 = stack.pop();
                String comStr = "(" + op2 + curr + op1 + ")";
                stack.push(comStr);
            }
        }
        return stack.peek();
    }


    public static void main(String[] args) {
        PostfixToInfix postfixToInfix = new PostfixToInfix();
        System.out.println("Infix to postfix : " + postfixToInfix.postfixToInfix("abc-+de-fg-h+/*"));
    }
}
