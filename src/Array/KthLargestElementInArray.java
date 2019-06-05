package Array;

import java.util.PriorityQueue;

import static Sort.QuickSort.swap;

public class KthLargestElementInArray {
    /*
     *@Author : Sahil
     * Date : 7 Feb 2018
     *
     * References : https://leetcode.com/problems/kth-largest-element-in-an-array/description/
     *
     * Solution : using Max Heap [submitted to leetcode]
     */

   public int findKthLargest(int[] nums, int k) {

    final PriorityQueue<Integer> pq = new PriorityQueue<>();
    for(int val : nums) {
        pq.offer(val);

        if(pq.size() > k) {
            pq.poll();
        }
    }
    return pq.peek();
}


    public static void main(String args[]) {

        int arr[] = {8, 7, 6, 4, 3, 1, 2, 9, 5};
        int k = 2;
        KthLargestElementInArray element = new KthLargestElementInArray();
        System.out.print(element.findKthLargest(arr, k));
    }
}
