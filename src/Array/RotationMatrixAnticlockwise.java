package Array;

public class RotationMatrixAnticlockwise {

    /*
     * @Author : Sahil
     * Date : 23 Jan 2017
     * Source : https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
     *
     * How to do ?
     *
     * 1. Transpose the Matrix
     * 2. Reverse Each Row
     *
     *   Input        Transpose      Reverse
     *
     *   1 2 3        1 4 7          3 6 9
     *   4 5 6  ->    2 5 8     ->   2 5 8
     *   7 8 9        3 6 9          1 4 7
     *
     */

    public void transpose(int arr[][]) {
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = i; j < arr[0].length; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
        reverseRow(arr);
        print2DMatrix(arr);
    }

    public void reverseRow(int arr[][]) {
        int numRows = arr[0].length;
        for (int i = 0; i < numRows / 2; i++) {
            for (int j = 0; j < numRows; j++) {
                int temp = arr[numRows - i - 1][j];
                arr[numRows - i - 1][j] = arr[i][j];
                arr[i][j] = temp;
            }
        }
    }

    public void print2DMatrix(int arr[][]) {
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String args[]) {
        int arr[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RotationMatrixAnticlockwise rma = new RotationMatrixAnticlockwise();
        rma.transpose(arr);
    }
}
