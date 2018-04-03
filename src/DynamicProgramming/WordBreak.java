package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class WordBreak {
    /*
     *@Author : Sahil
     * Date : 03 April 2018
     *
     * Given an input string and a dictionary of words, find out if the input string can be segmented into a
     * space-separated sequence of dictionary words
     *
     * Example :
     *
     * { i, like, sam, sung, samsung, mobile, ice,
     *  cream, icecream, man, go, mango}
     *
     * Input:  ilike
     * Output: Yes
     * The string can be segmented as "i like".
     *
     * Input:  ilikesamsung
     * Output: Yes
     * The string can be segmented as "i like samsung"
     * or "i like sam sung".
     *
     * References : https://leetcode.com/problems/word-break/description/#
     */

    public boolean wordBreak(String word, List<String> wordDict) {
        if (word == null || word.length() == 0)
            return false;
        if (word.length() == 1) {
            return wordDict.contains(word);
        }
        boolean dp[] = new boolean[word.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= word.length(); i++) {
            for (int j = 1; j <= i; j++) {
                String str = word.substring(j - 1, i);
                if (wordDict.contains(str) && dp[j - 1] == true) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }

    public static void main(String args[]) {
        WordBreak wordBreak = new WordBreak();
        List<String> wordList = new ArrayList<>();
        wordList.add("mobile");
        wordList.add("samsung");
        wordList.add("sam");
        wordList.add("sung");
        wordList.add("woman");
        wordList.add("mango");
        wordList.add("icecream");
        wordList.add("and");
        wordList.add("go");
        wordList.add("i");
        wordList.add("like");
        wordList.add("ice");
        wordList.add("cream");

        String word = "ilikewoman";
        System.out.println(wordBreak.wordBreak(word, wordList));

    }
}
