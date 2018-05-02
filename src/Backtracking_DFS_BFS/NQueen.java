package Backtracking_DFS_BFS;

import java.util.*;

public class NQueen {
    /*
     *@Author : Sahil
     * Date : 03 May 2018
     *
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no
     * two queens attack each other.
     *
     * Given an integer n, return all distinct solutions to the n-queens puzzle.
     *
     * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.'
     * both indicate a queen and an empty space respectively.
     *
     * Example :
     *
     * Input: 4
     * Output: [
     *  [".Q..",  // Solution 1
     *   "...Q",
     *   "Q...",
     *   "..Q."],
     *
     *  ["..Q.",  // Solution 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]
     * ]
     *
     * y2-y1/x2-x1 = 1 (if in same diagpnal 45) means queen can cur
     *
     * y2-y1=x2-x1 or y2 - x2 = y1 - x1 or row1 - col1 = row2 - col2
     *
     * also
     *
     * y2-y1/x2-x1 = -1 (if in diagonal 135) means queen can cut
     *
     * y2-y1 = x1-x2 or y2+x2 = y1 +x1 or row1+col1 = row2+col2
     * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
     * References : https://leetcode.com/problems/n-queens/description/
     */


    Set<Integer> col = new HashSet<Integer>(); //contains the coordinates of the column that has queen
    Set<Integer> dia45 = new HashSet<Integer>(); //contains the coord sum for checking diag of 45 degreee that has queen
    Set<Integer> dia135 = new HashSet<Integer>(); //contains the coord sum for checking diag of 135 degreee that has queen


    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        dfs(res, new ArrayList<String>(), 0, n);
        printChess(res);
        return res;
    }

    public void dfs(List<List<String>> res, List<String> list, int currRow, int n) {
        if (currRow == n) {
            res.add(new ArrayList<String>(list));
            return;
        }

        for (int currCol = 0; currCol < n; currCol++) {

            //do not place queen here
            if (col.contains(currCol) || dia135.contains(currRow + currCol) || dia45.contains(currRow - currCol))
                continue;


            //else place queen here
            char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            charArray[currCol] = 'Q';
            String rowString = new String(charArray);

            list.add(rowString);
            col.add(currCol);
            dia135.add(currRow + currCol);
            dia45.add(currRow - currCol);

            dfs(res, list, currRow + 1, n);

            list.remove(list.size() - 1);
            col.remove(currCol);
            dia135.remove(currRow + currCol);
            dia45.remove(currRow - currCol);
        }

    }

    void printChess(List<List<String>> res) {
        for (List list : res) {
            for (Object s : list) {
                System.out.println((String) s);
            }
            System.out.print("\n\n");
        }
    }

    public static void main(String args[]) {
        NQueen nqueen = new NQueen();
        int n = 4;
        nqueen.solveNQueens(n);
    }
}
