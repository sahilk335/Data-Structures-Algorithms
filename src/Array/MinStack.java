package Array;

import java.util.Stack;

public class MinStack {
    /*
     *@Author : Sahil
     * Date : 09 Apr 2018
     *
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     *
     * push(x) -- Push element x onto stack.
     * pop() -- Removes the element on top of the stack.
     * top() -- Get the top element.
     * getMin() -- Retrieve the minimum element in the stack.
     * Example:
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> Returns -3.
     * minStack.pop();
     * minStack.top();      --> Returns 0.
     * minStack.getMin();   --> Returns -2.
     *
     * Reference :
     * https://leetcode.com/problems/min-stack/description/
     *
     */

    Stack<Integer> stack;
    int min;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int x) {

        if (stack.isEmpty()) {
            //Initial no element , so we make first element as min, and push 0 in stack
            stack.push(0);
            min = x;
        } else {
            stack.push(x - min);//Store the difference between min and currElement
            if (min > x) {
                //update min if currELement is smaller than min
                min = x;
                //Note if this is the case we have already pushed negative number in a stack
                //This indicated at every negative  value in a stack , we have updated a min value
            }
        }
    }

    public void pop() {
        if (stack.isEmpty())
            return;

        int pop = stack.pop();

        if (pop < 0) {      //negative element indicates a updated min
            min = min - pop;
        }
    }

    public int top() {
        int peek = stack.peek();

        if (peek < 0) {     //If it is negative then that means min is the value
            return min;
        } else {
            return min + peek;  //else return top + difference
        }
    }

    public int getMin() {
        return min;
    }

    public static void main(String args[]) {
        MinStack minStack = new MinStack();
        minStack.push(3);
        System.out.print("\n" + minStack.getMin());
        minStack.push(5);
        minStack.push(2);
        System.out.print("\n" + minStack.getMin());
        minStack.push(1);
        System.out.print("\n" + minStack.getMin());
        minStack.pop();
        minStack.pop();
        System.out.print("\n" + minStack.top());


    }
}
