package StacksQueues;

import java.util.Arrays;
import java.util.Stack;

public class StockSpan {
    /*
     *@Author : Sahil
     * Date : 21 Apr 2019
     * The stock span problem is a financial problem where we have a series of n daily price quotes for a stock and we
     * need to calculate span of stock’s price for all n days.
     * The span Si of the stock’s price on a given day i is defined as the maximum number of consecutive days just before the
     * given day, for which the price of the stock on the current day is less than or equal to its price on the given day.
     * For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, then the span values for
     * corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
     *
     * Solution :
     * 1. Take a result array, first stock will have span =1 .i.e. result[0]=1 , always
     * 2. Now take a stack, push curr, if st.top() is greater, else keep on pop
     * 3. when push, update result array prev st.top - current stack.top
     *
     * References : https://www.geeksforgeeks.org/the-stock-span-problem/
     */
    int[] calculateSpan(int[] price) {
        int[] result = new int[price.length];
        Stack<Integer> st = new Stack();
        //first stock span is always 1
        result[0] = 1;
        st.push(0);
        for (int i = 1; i < price.length; i++) {

            while (!st.isEmpty() && price[st.peek()] <= price[i]) {
                st.pop();
            }
            result[i] = st.isEmpty() ? i + 1 : i - st.peek();

            st.push(i);

        }
        return result;
    }

    public static void main(String[] args) {
        StockSpan stockSpan = new StockSpan();
        int[] price = {10, 4, 5, 90, 120, 80};
        System.out.println(Arrays.toString(stockSpan.calculateSpan(price)));
    }
}
