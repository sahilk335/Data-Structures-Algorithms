package Array;

import java.util.List;
import java.util.PriorityQueue;

/*
 *@Author : Sahil Khurana
 * Date : 14 June 2019
 *
 * You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.
 *
 * We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
 *
 * Example 1:
 * Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * Note:
 * The given list may contain duplicates, so ascending order means >= here.
 * 1 <= k <= 3500
 * -105 <= value of elements <= 105.
 *
 * Refrences :
 * https://leetcode.com/problems/smallest-range/
 *
 * Solution :
 * 1. Take a min heap, and add first number from each row
 * 2. calculate the initial range  (max-min) element in a minHeap
 * 3. pop the top element from the minHeap and add the next element from the same row
 * 4. keep updating the range
 * 5. Continue step 2-4 till minElement row is finished
 * 6. return range
 *
 * Time Complexity : O(N log (k))
 *
 * where k is number of rows
 */
public class SmallestRange {

    class Pair {
        int row;
        int col;
        int val;

        Pair(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        int[] res = new int[2];

        PriorityQueue<Pair> minHeap = new PriorityQueue<>((Pair p1, Pair p2) -> {
            return p1.val - p2.val;
        });

        int currMax = Integer.MIN_VALUE;
        //Add first element from each row to minHeap
        for (int i = 0; i < nums.size(); i++) {
            currMax = Math.max(currMax, nums.get(i).get(0));
            Pair p = new Pair(i, 0, nums.get(i).get(0));
            minHeap.add(p);
        }
        res[0] = minHeap.peek().val;
        res[1] = currMax;

        //remove min element from the heap, and add next pair from the same row
        while (true) {
            Pair minElement = minHeap.poll();
            int currMinRow = minElement.row;
            int currMinCol = minElement.col;


            //MinRowElement's row has reached the end
            if (currMinCol + 1 == nums.get(currMinRow).size()) {
                return res;
            }
            //Add new Pair to minHeap from the minELementRow
            Pair newElement = new Pair(currMinRow, currMinCol + 1, nums.get(currMinRow).get(currMinCol + 1));
            minHeap.add(newElement);

            //check if currently added element is the new MaxELement
            currMax = Math.max(newElement.val, currMax);
            int currMinVal = minHeap.peek().val;
            if (currMax - currMinVal < res[1] - res[0]) {
                res[0] = currMinVal;
                res[1] = currMax;
            } else if (currMax - currMinVal == res[1] - res[0] && res[0] > currMinVal) {
                res[0] = currMinVal;
                res[1] = currMax;
            }


        }

    }

}
