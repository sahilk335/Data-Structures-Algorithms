package StacksQueues;


import java.util.Stack;

public class NextGreaterElement {
    /*
     * @Author : Sahil
     * Date : 1 Feb 2018
     *
     * Given an array, print the Next Greater Element (NGE) for every element. The Next greater Element for an
     * element x is the first greater element on the right side of x in array. Elements for which no greater element
     * exist, consider next greater element as -1.
     *
     * References : https://www.geeksforgeeks.org/next-greater-element/
     *
     * Example
     *
     * Element       NGE
     * 4      -->   5
     * 5      -->   25
     * 2      -->   25
     * 25     -->   -1
     *
     *
     */

    public void nextGreaterElement(int arr[]) {
        int sol[] = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);     //Push the first element's index
        int index = 1;
        while (index < arr.length) {
            //If next array element is less than stack top element means it is not NGE , so push it in stack
            if (arr[index] < arr[stack.peek()]) {
                stack.push(index);
            } else { //Next element is greater that means it is the NGE for stack top
                while (!stack.isEmpty() && arr[index] > arr[stack.peek()]) {
                    sol[stack.pop()] = arr[index];
                }
                stack.push(index);          //Push this element after all emenents lesser than this element in stack is
                                            //popped out already
            }
            index++;
        }

        //For left over element in stack assign -1 because they don't have NGE anymore
        if (!stack.isEmpty()) {
            while (!stack.isEmpty()) {
                sol[stack.pop()] = -1;
            }
        }

        //Print solution
        for (int i = 0; i < sol.length; i++) {
            System.out.print(sol[i] + " ");
        }
    }

    public static void main(String args[]) {
        int arr[] = {11, 13, 21, 3};
        NextGreaterElement nge = new NextGreaterElement();
        nge.nextGreaterElement(arr);

    }
}
