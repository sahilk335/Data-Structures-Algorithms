package Backtracking_DFS_BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class RemoveInvalidParanthesis {
    /*
     *@Author : Sahil Khurana
     * Date : 14 June 2019
     *
     * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
     *
     * Note: The input string may contain letters other than the parentheses ( and ).
     *
     * Example 1:
     *
     * Input: "()())()"
     * Output: ["()()()", "(())()"]
     * Example 2:
     *
     * Input: "(a)())()"
     * Output: ["(a)()()", "(a())()"]
     * Example 3:
     *
     * Input: ")("
     * Output: [""]
     *
     * References : https://leetcode.com/problems/remove-invalid-parentheses/
     *
     * Solution 1 :
     *  Apply all combinations with pruning
     *
     */

    public List<String> removeInvalidParentheses(String s) {
        int len = s.length();
        int resSize = (int) Math.pow(2, len);
        int removedIndexCount = 0;
        int minRemovedCount = Integer.MAX_VALUE;
        HashMap<Integer, HashSet<String>> map = new HashMap<>();
        for (int i = 0; i < resSize; i++) {
            StringBuilder sb = new StringBuilder();
            removedIndexCount = 0;
            int removeShift = 0;
            sb.append(s);
            int count = 0;
            count = len - Integer.bitCount(i);
            if (count <= minRemovedCount) {
                for (int j = 0; j < len; j++) {
                    if ((i & (1 << j)) == 0) {
                        sb.deleteCharAt(j - removeShift);
                        removeShift++;
                        removedIndexCount++;
                    }
                }
                if (minRemovedCount >= removedIndexCount && isValid(sb.toString())) {
                    minRemovedCount = removedIndexCount;
                    HashSet<String> currString = new HashSet<>();
                    if (map.get(minRemovedCount) == null) {
                        currString.add(sb.toString());
                    } else {
                        currString = map.get(minRemovedCount);
                        currString.add(sb.toString());
                    }
                    map.put(minRemovedCount, currString);
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (Object o : map.get(minRemovedCount).toArray()) {
            res.add(o.toString());
        }
        return minRemovedCount != Integer.MAX_VALUE ? res : new ArrayList<String>();
    }

    public boolean isValid(String s) {

        int score = 0; // Indicates whether left parenthesis and right parenthesis are balanced.

        for (char ch : s.toCharArray()) {
            if (ch == '(')
                score++;
            else if (ch == ')')
                score--;
            if (score < 0)
                return false;
        }
        return score == 0;
    }

    public static void main(String[] args) {
        RemoveInvalidParanthesis removeInvalidParanthesis = new RemoveInvalidParanthesis();
        System.out.println(removeInvalidParanthesis.removeInvalidParentheses("()())()"));

    }
}
