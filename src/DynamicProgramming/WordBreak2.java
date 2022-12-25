package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 * Example 2:
 * <p>
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 * <p>
 * Reference : https://leetcode.com/problems/word-break-ii/description/
 * Video Solution : https://www.youtube.com/watch?v=necF6yyegiE
 * <p>
 * 1. We will create decision trees
 * catsanddog
 * cat    /   cats \
 * sanddog      anddog
 * sand  /        and  \
 * dog              dog
 * dog /           dog     \
 * ""                     ""
 * 2. Apply dp by storing hashmap<startPos,AnsList>
 */

public class WordBreak2 {

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Map<Integer, List<String>> dpMap = new HashMap<>(); //This map will contain startPos and answerList
        return wordBreak2Helper(s,0,wordDictSet,dpMap);
    }

    public List<String> wordBreak2Helper(String s, int start, Set<String> wordDictSet, Map<Integer, List<String>> dpMap) {

        if (dpMap.containsKey(start)) {
            return dpMap.get(start);
        }
        List<String> validSubStringList = new ArrayList<>();

        //baseCase
        if (start == s.length()) {
            validSubStringList.add("");
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String prefix = s.substring(start, end);
            if (wordDictSet.contains(prefix)) {
                List<String> suffixes = wordBreak2Helper(s, end, wordDictSet, dpMap);
                for (String suffix : suffixes) {
                    validSubStringList.add(prefix + (suffix.equals("") ? "" : " " + suffix));
                }
            }
        }
        dpMap.put(start, validSubStringList);
        return validSubStringList;
    }

    public static void main(String[] args) {
        WordBreak2 wordBreak2 = new WordBreak2();
        String s = "pineapplepenapple";
        List<String> wordDict = new ArrayList<>(Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));
        wordBreak2.wordbreak(s, wordDict).forEach(ans->System.out.println(ans));
    }

}
