package Strings;

import java.util.*;

public class TopKFrequentWords {
    /*
     *@Author : Sahil
     * Date : 12 May 2019
     * Given a non-empty list of words, return the k most frequent elements.
     *
     * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency,
     * then the word with the lower alphabetical order comes first.
     *
     * Example 1:
     * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     * Output: ["i", "love"]
     * Explanation: "i" and "love" are the two most frequent words.
     *     Note that "i" comes before "love" due to a lower alphabetical order.
     * Example 2:
     * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
     * Output: ["the", "is", "sunny", "day"]
     * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
     *     with the number of occurrence being 4, 3, 2 and 1 respectively.
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
     * Input words contain only lowercase letters.
     * Follow up:
     * Try to solve it in O(n log k) time and O(n) extra space.
     *
     *
     * References :
     * https://leetcode.com/problems/top-k-frequent-words/
     *
     *
     * Solution :
     *Count the frequency of each word, then add it to heap that stores the best k candidates.
     * Here, "best" is defined with our custom ordering relation, which puts the worst candidates at the top of the heap.
     * At the end, we pop off the heap up to k times and reverse the result so that the best candidates are first.
     */
    public List<String> topKFrequent(String[] words, int k) {
        //make frequency map for all the integers
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        //Make Minimum heap based on frequency in the map
        PriorityQueue<String> pq = new PriorityQueue<>((w1, w2) -> {
            if (map.get(w1) == map.get(w2))
                return w2.compareTo(w1);
            return map.get(w1) - map.get(w2);
        });


        //keep only K top elements in the heap
        for (String word : map.keySet()) {
            pq.add(word);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        //Make result List
        List<String> top_k = new LinkedList<>();
        while (!pq.isEmpty()) {
            top_k.add(pq.poll());
        }

        //reverse the minHeap
        Collections.reverse(top_k);
        return top_k;
    }

    public static void main(String[] args) {
        TopKFrequentWords topKFrequentWords = new TopKFrequentWords();

    }
}
