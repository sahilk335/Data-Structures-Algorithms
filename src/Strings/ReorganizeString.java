package Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganizeString {
    /*
     *@Author : Sahil
     * Date : 30 May 2019
     *
     * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
     *
     * If possible, output any possible result.  If not possible, return the empty string.
     *
     * Example 1:
     *
     * Input: S = "aab"
     * Output: "aba"
     * Example 2:
     *
     * Input: S = "aaab"
     * Output: ""
     *
     * Solution :
     * 1.  task is only impossible if the frequency of any letter exceeds (N+1) / 2.
     * 2. Use max heap(based on word frequency) because it can return the current top 2 letters with the largest
     *      remaining counts , append top 2 to result as they would be adjacent characters
     * 3. poll first two charcters from maxHeap, decrement its count in map, add again to PriorityQueue
     * 4. countinue while priority que has atleast 2 characters
     * 5. append last character in result, and return it
     *
     * References :
     * https://leetcode.com/problems/reorganize-string/
     *
     */
    public String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : S.toCharArray()) {
            int count = map.getOrDefault(c, 0) + 1;
            //Solution not possible
            if (count > (S.length() + 1) / 2) {
                return "";
            }
            map.put(c, count);
        }

        PriorityQueue<Character> pq = new PriorityQueue<>((w1, w2) -> {
            if (map.get(w2) == map.get(w1))
                return w2.compareTo(w1);
            return map.get(w2) - map.get(w1);
        });

        for (Character key : map.keySet()) {
            pq.add(key);
        }

        StringBuilder sb = new StringBuilder();
        while (pq.size() >= 2) {
            //Poll first two highest repeated character
            Character c1 = pq.poll();
            Character c2 = pq.poll();

            sb.append(c1);
            sb.append(c2);

            //Update map and then again add character if its count is greater than 1
            if (map.get(c1) > 1) {
                map.put(c1, map.get(c1) - 1);
                pq.add(c1);
            }

            if (map.get(c2) > 1) {
                map.put(c2, map.get(c2) - 1);
                pq.add(c2);
            }
        }
        //One charater will be remaining after this.
        if (pq.size() > 0)
            sb.append(pq.poll());

        return sb.toString();
    }

    public static void main(String[] args) {
        ReorganizeString reorganizeString = new ReorganizeString();
        System.out.println(reorganizeString.reorganizeString("aab"));
    }
}
