package Strings;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacter {
    /*
     *@Author : Sahil
     * Date : 05 May 2019
     *
     * Given a string, find the length of the longest substring without repeating characters.
     *
     * Example 1:
     *
     * Input: "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * Example 2:
     *
     * Input: "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     *
     * Input: "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     *
     *
     * References :
     * https://leetcode.com/problems/longest-substring-without-repeating-characters/
     *
     *
     * Solution :
     *1. keep two pointers i & j
     *2. Initialise j from 0
     *3. Start putting character in map with its index
     *4. As soon as you find repeated character in map, place J at that index +1 , because you don't want to get
     *  over that repeated first occuring index of that character, as till that character we have already found
     *  maximum contiguous string without repeated character .
     */

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int j = 0;
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) != null) {
                j = Math.max(j, map.get(s.charAt(i)) + 1); //We check maximum of j,charAt(i) because, it might be the case like
                                                            //ABBA -> then after 2nd index, j might try to go back, so we have to prevent it
            }
            map.put(s.charAt(i), i);
            answer = Math.max(answer, i - j + 1);
        }
        return answer;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacter lswrc = new LongestSubstringWithoutRepeatingCharacter();
        System.out.println(lswrc.lengthOfLongestSubstring("pwwkew"));
    }
}
