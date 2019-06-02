package Trie;

public class MaximumXor {
    /*
     *@Author : Sahil Khurana
     * Date : 02 June 2019
     * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
     *
     * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
     *
     * Could you do this in O(n) runtime?
     *
     * Example:
     *
     * Input: [3, 10, 5, 25, 2, 8]
     *
     * Output: 28
     *
     * Explanation: The maximum result is 5 ^ 25 = 28.
     *
     * References : https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
     *
     * Solution :
     * 1. Maximum Xor can be acheived if MSB of two numbers are complementary of each other
     * 2. First we add all the numbers in the trie
     * 3. Then for each number we try to find the complementary bits from MSB of every number
     * 4. Each number will give some result, we reutrn the maximum result
     *
     */

    class Trie {
        Trie[] links;

        Trie() {
            links = new Trie[2];
        }
    }

    public int findMaximumXOR(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;


        //Add all numbers bits in Trie
        Trie root = new Trie();
        for (int num : nums) {
            Trie currNode = root;   //add every number from root only (obviously)
            for (int i = 31; i >= 0; i--) {
                int curSetBit = (num >> i) & 1;
                if (currNode.links[curSetBit] == null) {
                    currNode.links[curSetBit] = new Trie();
                }
                currNode = currNode.links[curSetBit];
            }
        }

        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            Trie currNode = root;
            int currSum = 0;
            for (int i = 31; i >= 0; i--) {
                int currSetBit = (num >> i) & 1;
                //check its reverseBit in Trie
                if (currNode.links[currSetBit ^ 1] != null) {
                    currSum += (1 << i);
                    currNode = currNode.links[currSetBit ^ 1];
                } else {
                    //if reverse Bit is not present than what to do,, continue
                    currNode = currNode.links[currSetBit];
                }
            }
            max = Math.max(currSum, max);
        }

        return max;
    }

    public static void main(String[] args) {
        MaximumXor maximumXor = new MaximumXor();
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println(maximumXor.findMaximumXOR(nums));
    }
}
