package DynamicProgramming;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class WildCardMatching {
    /**
     * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
     * <p>
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * <p>
     * The matching should cover the entire input string (not partial).
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "aa", p = "a"
     * Output: false
     * Explanation: "a" does not match the entire string "aa".
     * <p>
     * Example 2:
     * <p>
     * Input: s = "aa", p = "*"
     * Output: true
     * Explanation: '*' matches any sequence.
     * <p>
     * Example 3:
     * <p>
     * Input: s = "cb", p = "?a"
     * Output: false
     * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
     * <p>
     * Example 4:
     * <p>
     * Input: s = "adceb", p = "*a*b"
     * Output: true
     * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
     * <p>
     * Example 5:
     * <p>
     * Input: s = "acdcb", p = "a*c?b"
     * Output: false
     * <p>
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 0 <= s.length, p.length <= 2000
     * s contains only lowercase English letters.
     * p contains only lowercase English letters, '?' or '*'
     * <p>
     * Problem : https://leetcode.com/problems/wildcard-matching/
     * <p>
     * <p>
     * Solution : https://www.youtube.com/watch?v=3ZDZ-N0EPV0
     * <p>
     * Step 1 : Clean up collective * . For eg pattern like "a*****b**" reduced to "a*b*"
     * Step 2 :
     * if
     * s[i-1] == p[j-1] || p[j-1]=='?' => dp[i][j] = dp[i-1][j-1];
     * else if
     * p[j-1] == '*'
     * dp[i][j] = dp[i-1][j] || dp[i][j-1]
     */

    public String cleanUp(String p) {
        List<Character> patternList = p.chars()
                .mapToObj(s -> (char) s)
                .collect(Collectors.toList());
        for (int i = 1; i < patternList.size(); i++) {
            if (patternList.get(i) == '*' && patternList.get(i - 1) == patternList.get(i)) {
                patternList.remove(i);
                i--;
            }
        }
        return patternList.stream()              // Stream<Character>
                .map(String::valueOf)   // Stream<String>
                .collect(Collectors.joining());
    }

    public boolean isMatch(String str, String pattern) {

        //Step 1 cleanUp joined *
        pattern = cleanUp(pattern);

        boolean[][] dp = new boolean[str.length() + 1][pattern.length() + 1];

        char[] p = pattern.toCharArray();
        char[] s = str.toCharArray();
        dp[0][0] = true;
        if (p[0] == '*') {
            dp[0][1] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (p[j - 1] == '?' || p[j - 1] == s[i - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p[j - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }


        return dp[str.length()][pattern.length()];

    }


    public static void main(String[] args) {
        WildCardMatching wildCardMatching = new WildCardMatching();
        String s = "adceb";
        String pattern = "a********b****c***";
        System.out.println(wildCardMatching.cleanUp(pattern));
        System.out.println(wildCardMatching.isMatch(s, pattern));

    }
}
