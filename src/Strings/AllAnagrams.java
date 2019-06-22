package Strings;

import java.util.ArrayList;
import java.util.List;

public class AllAnagrams {
    /*
     *@Author : Sahil Khurana
     * Date : 22 June 2019
     *
     * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
     *
     * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
     *
     * The order of output does not matter.
     *
     * Example 1:
     *
     * Input:
     * s: "cbaebabacd" p: "abc"
     *
     * Output:
     * [0, 6]
     *
     * Explanation:
     * The substring with start index = 0 is "cba", which is an anagram of "abc".
     * The substring with start index = 6 is "bac", which is an anagram of "abc".
     * Example 2:
     *
     * Input:
     * s: "abab" p: "ab"
     *
     * Output:
     * [0, 1, 2]
     *
     * Explanation:
     * The substring with start index = 0 is "ab", which is an anagram of "ab".
     * The substring with start index = 1 is "ba", which is an anagram of "ab".
     * The substring with start index = 2 is "ab", which is an anagram of "ab".
     *
     *
     * References :
     * https://leetcode.com/problems/find-all-anagrams-in-a-string/
     *
     * Solution :
     * 1. Using 2 pointer keep check charMap[26]
     *
     */

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int count = p.length();
        if (p.length() > s.length())
            return res;

        int[] map = new int[26];
        for (int k = 0; k < p.length(); k++) {
            map[p.charAt(k) - 'a']++;
        }
        int start = 0;
        int end = 0;
        while (end < s.length()) {
            if (map[s.charAt(end) - 'a'] >= 1) {
                count--;
            }
            map[s.charAt(end) - 'a']--;
            end++;

            if (end - start == p.length()) {
                if (count == 0) {
                    res.add(start);
                }
                if (map[s.charAt(start) - 'a'] >= 0) {
                    count++;
                }
                map[s.charAt(start) - 'a']++;
                start++;
            }
        }
        return res;

    }


    public static void main(String[] args) {
        AllAnagrams allAnagrams = new AllAnagrams();
        List<Integer> res = new ArrayList<>();

        res = allAnagrams.findAnagrams("cbaebabacd", "abc");
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}

