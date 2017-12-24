package Array;

import java.util.Stack;

/*
[LEETCODE]
2 ways to do this question

1. Using dynamic programming
in O(N) create two arrays that stores max size in left and max size in right
Calculate minimum(max left , max right) and subtract it from current height
add all these values

2. Using stacks

Use stack to store the indices of the bars.
Iterate the array:
While stack is not empty and \text{height}[current]>\text{height}[st.top()]height[current]>height[st.top()]
It means that the stack element can be popped. Pop the top element as \text{top}top.
Find the distance between the current element and the element at top of stack, which is to be filled. \text{distance} = \text{current} - \text{st.top}() - 1distance=current−st.top()−1
Find the bounded height bounded_height=min(height[current],height[st.top()])−height[top]bounded_height=min(height[current],height[st.top()])−height[top]
Add resulting trapped water to answer ans+=distance∗bounded_heightans+=distance∗bounded_height
Push current index to top of the stack
Move \text{current}current to the next position

 */
public class TrappingRainWater {

//Using Dynamic programming

    int trappingDP(int arr[]) {
        if (arr.length <= 0) {
            return 0;
        }
        int len = arr.length;
        int leftarr[] = new int[len];
        int rightarr[] = new int[len];
        leftarr[0] = arr[0];
        rightarr[len - 1] = arr[len - 1];
        for (int i = 1; i < len - 1; i++) {
            leftarr[i] = Math.max(arr[i], leftarr[i - 1]);
            rightarr[len - i - 1] = Math.max(arr[len - i - 1], rightarr[len - i]);
        }

        int answer = 0;
        for (int i = 0; i < len; i++) {
            int min = Math.min(leftarr[i], rightarr[i]);
            if (arr[i] < min) {
                answer += min - arr[i];
            }
        }
        return answer;
    }

    //Using Stacks
    int trappingStack(int height[]) {
        if (height.length <= 0) {
            return 0;
        }
        int len = height.length;
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        int current = 0;
        while (current < len) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.peek();
                stack.pop();
                if (stack.isEmpty())
                    break;
                int distance = current - stack.peek() - 1;
                int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
                answer += distance * bounded_height;                //Caculate area Lengthx Breadth
            }
            stack.push((current++));
        }
        return answer;
    }

    public static void main(String args[]) {
        int arr[] = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        System.out.println("DP Solution : " + trappingRainWater.trappingDP(arr));
        System.out.println("Stack Solution : " + trappingRainWater.trappingStack(arr));

    }
}
