package Backtracking_DFS_BFS;

import java.util.ArrayList;
import java.util.List;

public class GenerateParanthesis {
    /*
     *@Author : Sahil Khurana
     * Date : 14 June 2019
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     *
     * For example, given n = 3, a solution set is:
     *
     * [
     *  "((()))",
     *  "(()())",
     *   "(())()",
     *  "()(())",
     *   "()()()"
     * ]
     *
     * References : https://leetcode.com/problems/generate-parentheses/
     *
     * Solution
     * 1. Number of open brackets should be equal to closing brackets
     * 2. Number of open bracket at any point should be lesser than equal to close bracket
     * 3. generate string after open=close=0
     * 4. Backtrack
     *
     */

    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        generate(new StringBuilder(), n, n);
        return res;
    }

    public void generate(StringBuilder sb, int open, int close) {
        if (open > close)
            return;

        if (open == 0 && close == 0) {
            res.add(sb.toString());
            return;
        }

        if (open > 0) {
            sb.append("(");             //append at end of string
            generate(sb, open - 1, close);
            sb.deleteCharAt(sb.length() - 1);     //backtrack         //remove at end of string
        }
        if (close > 0) {
            sb.append(")");                  //append at end of string
            generate(sb, open, close - 1);
            sb.deleteCharAt(sb.length() - 1);        //backtrack         //remove at end of string
        }
    }

}
