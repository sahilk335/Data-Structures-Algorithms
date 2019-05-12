package Array;

import java.util.*;

public class TopKFrequentElements {
    /*
     *@Author : Sahil
     * Date : 03 May 2019
     *
     * Given a non-empty array of integers, return the k most frequent elements.
     *
     * Example 1:
     *
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Output: [1,2]
     * Example 2:
     *
     * Input: nums = [1], k = 1
     * Output: [1]
     * Note:
     *
     * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
     * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
     *
     * Solution
     *
     * 1. Create frequency map
     * 2. Make min Heap of K elements using frequency map (Imp. step)
     * 3. pop all the elements from heap to a Result list
     * 4. Collections.reverse ( result list)  and return it
     *
     */
    public List<Integer> topKFrequent(int[] nums, int k) {

        //make frequency map for all the integers
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //Make Minimum heap based on frequency in the map
        PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> {
            return map.get(n1) - map.get(n2);
        });


        //keep only K top elements in the heap
        for (int num : map.keySet()) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        //Make result List
        List<Integer> top_k = new LinkedList<>();
        while (!pq.isEmpty()) {
            top_k.add(pq.poll());
        }

        //reverse the minHeap
        Collections.reverse(top_k);
        return top_k;
    }

    public static void main(String[] args) {
        TopKFrequentElements frequentElements = new TopKFrequentElements();
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(frequentElements.topKFrequent(nums, 2));
    }
}
