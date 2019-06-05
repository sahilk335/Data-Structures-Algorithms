package Array;

import java.util.HashMap;
import java.util.Map;

public class FlipMatrixColumnForMaxEqualRowsCount {

    /*
     * Given a matrix consisting of 0s and 1s, we may choose any number of columns in the matrix and flip every cell in that column.  Flipping a cell changes the value of that cell from 0 to 1 or from 1 to 0.
     *
     * Return the maximum number of rows that have all values equal after some number of flips.
     *
     *
     *
     * Example 1:
     *
     * Input: [[0,1],[1,1]]
     * Output: 1
     * Explanation: After flipping no values, 1 row has all values equal.
     * Example 2:
     *
     * Input: [[0,1],[1,0]]
     * Output: 2
     * Explanation: After flipping values in the first column, both rows have equal values.
     * Example 3:
     *
     * Input: [[0,0,0],[0,0,1],[1,1,0]]
     * Output: 2
     * Explanation: After flipping values in the first two columns, the last two rows have equal values.
     *
     *
     * Note:
     *
     * 1 <= matrix.length <= 300
     * 1 <= matrix[i].length <= 300
     * All matrix[i].length's are equal
     * matrix[i][j] is 0 or 1
     *
     * Solution
     * 1. Take a hashmap
     * 2. Add each row and its flipped version to hashmap, if already present
     * e.g.1
     * 000     110
     * 001 ->  111 <- all ones
     * 110     000 <- all zeros
     *         ^^
     *         flip columns
     *
     * e.g.2
     * 00111       11111 <- all ones
     * 11000       00000 <- all zeros
     * 10101  ->   01101
     * 10100       01100
     * 00111       11111 <- all ones
     *             ^^
     *             flip columns
     * now we look closer, actually row0, row1 and row4 have the same pattern originally
     * row0 = 00111
     * row1 = 11000
     * row4 = 00111
     *
     * so we can just flip the rows count the pattern occurrence instead of brainlessly flipping the columns
     *
     * References :
     * https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/
     *
     */


    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> map = new HashMap<>();
        for (int[] row : matrix) {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder(); //stores flip version of row

            for (int rowElement : row) {
                sb1.append(rowElement);
                sb2.append(1 - rowElement);
            }

            map.put(sb1.toString(), map.getOrDefault(sb1.toString(), 0) + 1);
            map.put(sb2.toString(), map.getOrDefault(sb2.toString(), 0) + 1);
        }
        int res = 0;
        for (int v : map.values()) {
            res = Math.max(res, v);
        }
        return res;
    }

    public static void main(String[] args) {
        FlipMatrixColumnForMaxEqualRowsCount flipper=new FlipMatrixColumnForMaxEqualRowsCount();
        int[][] matrix={{0,0,0,},{0,0,1},{1,1,0}};
        System.out.println(flipper.maxEqualRowsAfterFlips(matrix));
    }
}
