package Array;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    /*
     *@Author : Sahil
     * Date : 09 Mar 2019
     *
     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
     *
     * Your algorithm should run in O(n) complexity.
     *
     * Example:
     *
     * Input: [100, 4, 200, 1, 3, 2]
     * Output: 4
     * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
     *
     * Solution :
     *  1. Add all the numbers in the set
     *  2. start scanning the number from the set such that its num-1 is not present inside the set
     *  3. if not present , then start a streak that follows maximum set.contains(num+1)
     *  4. update the maxStreak,currStreak & then return the maxStreak
     *
     */

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        for (int num : nums) {
            int currStreak = 0;
            if (!set.contains(num - 1)) {
                currStreak = 1;
                int currNum = num;
                while (set.contains(currNum + 1)) {
                    currNum = currNum + 1;
                    currStreak++;
                }
            }
            ans = Math.max(ans, currStreak);
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutiveSequence.longestConsecutive(nums));
    }
}
