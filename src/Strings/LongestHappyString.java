package Strings;

import java.nio.channels.Pipe;
import java.util.PriorityQueue;

public class LongestHappyString {
    /**
     * https://leetcode.com/problems/longest-happy-string/
     * <p>
     * A string s is called happy if it satisfies the following conditions:
     * <p>
     * s only contains the letters 'a', 'b', and 'c'.
     * s does not contain any of "aaa", "bbb", or "ccc" as a substring.
     * s contains at most a occurrences of the letter 'a'.
     * s contains at most b occurrences of the letter 'b'.
     * s contains at most c occurrences of the letter 'c'.
     * Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".
     * <p>
     * A substring is a contiguous sequence of characters within a string.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: a = 1, b = 1, c = 7
     * Output: "ccaccbcc"
     * Explanation: "ccbccacc" would also be a correct answer.
     * Example 2:
     * <p>
     * Input: a = 7, b = 1, c = 0
     * Output: "aabaa"
     * Explanation: It is the only correct answer in this case.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 0 <= a, b, c <= 100
     * a + b + c > 0
     * <p>
     * <p>
     * <p>
     * <p>
     * Solution :
     * 1. Take a Max Heap, push all characters (Note : only if count of Character !=0) this is very important to note
     * 2. Keep on pulling the max number and check condition if last characters are not same , since we can not afford
     * to have 3 contiguous same character
     * 3. if point 2 is true, then get the next available character from heap.
     * 4. At the end push the currCharacter with updated count-1>0
     */

    class Pair {
        Character character;
        Integer count;

        Pair(Character character, Integer count) {
            this.character = character;
            this.count = count;
        }
    }

    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> y.count - x.count);
        if (a > 0) {
            pq.add(new Pair('a', a));
        }
        if (b > 0) {
            pq.add(new Pair('b', b));
        }
        if (c > 0) {
            pq.add(new Pair('c', c));
        }
        int currIdx = 0;
        while (!pq.isEmpty()) {
            int currCharCount = pq.peek().count;
            char currChar = pq.poll().character;
            if (currIdx < 2) {
                sb.append(currChar);
                currCharCount--;
            } else if (currIdx >= 2 && (sb.charAt(currIdx - 2) != currChar || sb.charAt(currIdx - 1) != currChar)) {
                sb.append(currChar);
                currCharCount--;
            } else if (sb.charAt(currIdx - 2) == currChar && pq.peek() != null) {
                int nextCharCount = pq.peek().count;
                char nextChar = pq.poll().character;
                sb.append(nextChar);
                if (nextCharCount - 1 > 0) {
                    pq.add(new Pair(nextChar, nextCharCount - 1));
                }
            } else {
                return sb.toString();
            }
            if (currCharCount > 0) {
                pq.add(new Pair(currChar, currCharCount));
            }

            currIdx++;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LongestHappyString longestHappyString = new LongestHappyString();
        int a = 7, b = 1, c = 0;
        System.out.println(longestHappyString.longestDiverseString(a, b, c));
    }
}
