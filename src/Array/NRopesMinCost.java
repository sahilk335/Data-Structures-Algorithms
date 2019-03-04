package Array;

import java.util.PriorityQueue;

public class NRopesMinCost {
    /*
     *@Author : Sahil
     * Date : 05 March 2019
     *
     *
     * Connect n ropes with minimum cost
     * There are given n ropes of different lengths, we need to connect these ropes into one rope.
     *  The cost to connect two ropes is equal to sum of their lengths. We need to connect the ropes with minimum cost.
     *
     * For example if we are given 4 ropes of lengths 4, 3, 2 and 6. We can connect the ropes in following ways.
     * 1) First connect ropes of lengths 2 and 3. Now we have three ropes of lengths 4, 6 and 5.
     * 2) Now connect ropes of lengths 4 and 5. Now we have two ropes of lengths 6 and 9.
     * 3) Finally connect the two ropes and all ropes have connected.0
     *
     * Total cost for connecting all ropes is 5 + 9 + 15 = 29. This is the optimized cost for connecting ropes.
     *  Other ways of connecting ropes would always have same or more cost. For example, if we connect 4 and 6 first
     *  (we get three strings of 3, 2 and 10), then connect 10 and 3 (we get two strings of 13 and 2). Finally
     *  we connect 13 and 2. Total cost in this way is 10 + 13 + 15 = 38.
     *
     *
     *  Solution :
     *  If we observe the above problem closely, we can notice that the lengths of the ropes which are picked first
     *  are included more than once in total cost. Therefore, the idea is to connect smallest two ropes first and
     *  recur for remaining ropes.
     *
     *  Let there be n ropes of lengths stored in an array len[0..n-1]
     * 1) Create a min heap and insert all lengths into the min heap.
     * 2) Do following while number of elements in min heap is not one.
     * ……a) Extract the minimum and second minimum from min heap
     * ……b) Add the above two extracted values and insert the added value to the min-heap.
     * ……c) Maintain a variable for total cost and keep incrementing it by the sum of extracted values.
     * 3) Return the value of this total cost.
     *
     *
     * References :
     * https://www.geeksforgeeks.org/connect-n-ropes-minimum-cost/
     */

    public int nRopesMinCostVal(int[] ropes) {
        int answer = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : ropes) {
            priorityQueue.add(num);
        }

        while (priorityQueue.size() != 1) {
            int min = priorityQueue.poll();
            int secondMin = priorityQueue.poll();
            int combine = min + secondMin;
            answer += combine;
            priorityQueue.add(combine);
        }
        //answer = priorityQueue.peek();
        return answer;
    }

    public static void main(String[] args) {

        NRopesMinCost nRopesMinCost = new NRopesMinCost();
        int[] ropes = {4, 3, 2, 6};
        System.out.println("Min Value for ropes : " + nRopesMinCost.nRopesMinCostVal(ropes));
    }
}
