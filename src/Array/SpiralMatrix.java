package Array;

import java.util.ArrayList;

public class SpiralMatrix {
    /*
     *@Author : Sahil
     * Date : 8 March 2018
     * Given a 2D matrix(square or rectangular) print it in spiral way.
     * e.g 1 2 3
     *     4 5 6
     *     7 8 9
     * Printing should be 1 2 3 6 9 8 7 4 5
     *
     * Solution:
     * Keep 4 pointers which are bounds for this matrix, up, down, left, right. Print each
     * row or column and keep incrementing and decrementing the bounds. As soon as up meets down
     * or left meets right we are done.
     *
     * Reference
     * https://leetcode.com/problems/spiral-matrix/
     * http://stackoverflow.com/questions/726756/print-two-dimensional-array-in-spiral-order
     * http://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/
     * https://www.youtube.com/watch?v=siKFOI8PNKM
     */

    public ArrayList<Integer> printSpiral(int arr[][]) {

        int top = 0;
        int bottom = arr.length - 1;
        int left = 0;
        int right = arr[0].length - 1;

        ArrayList<Integer> sol = new ArrayList<>();      //ArrayList to store Solution

        int dir = 0;
        while (top <= bottom && left <= right) {
            if (dir == 0) {                                 //Direction Right
                for (int i = left; i <= right; i++) {
                    sol.add(arr[top][i]);
                }
                top++;
            }

            if (dir == 1) {                                 //Direction Down
                for (int i = top; i <= bottom; i++) {
                    sol.add(arr[i][right]);
                }
                right--;
            }

            if (dir == 2) {                                 //Direction Left
                for (int i = right; i >= left; i--) {
                    sol.add(arr[bottom][i]);
                }
                bottom--;
            }

            if (dir == 3) {                                 //Direction Up
                for (int i = bottom; i >= top; i--) {
                    sol.add(arr[i][left]);
                }
                left++;
            }

            dir = (dir + 1) % 4;                          //Direction will go from right,down,left,up in each iteration
        }

        return sol;
    }

    public static void main(String args[]) {
        int arr[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}};
        SpiralMatrix sm = new SpiralMatrix();
        System.out.print(sm.printSpiral(arr));

    }
}