package Array;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MaximumLengthPairChain {
    /*
     *@Author : Sahil Khurana
     * Date : 14 July 2019
     *
     * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
     *
     * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
     *
     * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
     *
     * Example 1:
     * Input: [[1,2], [2,3], [3,4]]
     * Output: 2
     * Explanation: The longest chain is [1,2] -> [3,4]
     *
     * References :
     * https://leetcode.com/problems/maximum-length-of-pair-chain/
     * https://leetcode.com/problems/maximum-length-of-pair-chain/discuss/334853/Java-Easy-N-Log-(N)-solution-using-Max-Priority-Queue
     *  Solution 1 : O(NlogN) using Priority queue
     * 1. Sort the input on the basis of starting point of the pair, and take a max priority queue pq. for next step 2
     * 2. if pq is empty insert the pair
     * 3. if pq peek's end element is greater than current's end element. pop the pq element .because the upcoming element can give us more opportunity to combine with other pair
     * 4. if pq peek's end is less than current Pair's starting element. then push it
     * 5. Keep updating answer : which is max size of the heap uptill now.
     *
     * Solution 2 : Can be done using LIS
     *
     * Solution 3 :
     * 1. Sort according to end element of pair
     * 2. start comparing
     * https://leetcode.com/problems/maximum-length-of-pair-chain/solution/
     */

    public class Pairs {
        int s;
        int e;

        Pairs(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public int findLongestChain(int[][] pair) {

        int ans = 0;
        List<Pairs> list = new ArrayList<>();
        for (int i = 0; i < pair.length; i++) {
            list.add(new Pairs(pair[i][0], pair[i][1]));
        }
        list.sort((a, b) -> {
            return a.s - b.s;
        });

        PriorityQueue<Pairs> pq = new PriorityQueue<>((Pairs x, Pairs y) -> y.e - x.e);
        for (int i = 0; i < list.size(); i++) {
            if (pq.size() == 0)
                pq.add(list.get(i));
            else if (pq.peek().e > list.get(i).e) {
                pq.poll();
                pq.add(list.get(i));
            } else if (pq.peek().e < list.get(i).s) {
                pq.add(list.get(i));
            }
            ans = Math.max(ans, pq.size());
        }
        return ans;
    }

}
