package Array;

public class SearchInMatrix2 {
    /*
     *@Author : Sahil
     * Date : 08 May 2019
     *
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     *
     * Integers in each row are sorted in ascending from left to right.
     * Integers in each column are sorted in ascending from top to bottom.
     * Example:
     *
     * Consider the following matrix:
     *
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * Given target = 5, return true.
     *
     * Given target = 20, return false.
     *
     * References : https://leetcode.com/problems/search-a-2d-matrix-ii/
     *
     * Solution :
     *
     * We start search the matrix from top right corner, initialize the current position to top right corner,
     * if the target is greater than the value in current position, then the target can not be in entire row of
     * current position because the row is sorted, if the target is less than the value in current position,
     * then the target can not in the entire column because the column is sorted too.
     *
     * We can rule out one row or one column each time, so the time complexity is O(m+n).
     *
     */

    public boolean searchMatrix(int[][] matrix, int target) {

        if(matrix.length==0)
            return false;

        int R = matrix.length;
        int C = matrix[0].length;


        int row = 0;
        int col = C - 1;

        while (col >= 0 && row <= R - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target > matrix[row][col]) {
                row++;
            } else if (target < matrix[row][col]) {
                col--;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        SearchInMatrix2 searchInMatrix2 = new SearchInMatrix2();

    }
}
