package DynamicProgramming;


import StacksQueues.LargestRectangleAreaHistogram;

public class MaximumRectangeInMatrix {
    /*
     *@Author : Sahil
     * Date : 29 March
     *
     * Given a 2D matrix of 0s and 1s. Find largest rectangle of all 1s
     * in this matrix.
     *
     * Maintain a temp array of same size as number of columns.
     * Copy first row to this temp array and find largest rectangular area
     * for histogram. Then keep adding elements of next row to this temp
     * array if they are not zero. If they are zero then put zero there.
     * Every time calculate max area in histogram.
     *
     * References :
     * https://www.geeksforgeeks.org/maximum-size-rectangle-binary-sub-matrix-1s/
     *  https://youtu.be/2xvJ41-hsoE
     *     0 1 1 0
     *     1 1 1 1
     *     1 1 1 1
     *     1 1 0 0
     *
     * COnvert this to :
     *
     *     0 1 1 0     |
     *     1 2 2 1     |
     *     2 3 3 2     |    -- > Maximum of (Max area in historam for row 0,1,2,3)
     *     3 4 0 0     |
     *
     *  Solution :
     * Step 1: Find maximum area in for row[0]
     * Step 2:
     *     for each row in 1 to N - 1
     *         for each column in that row
     *            if A[row][column] == 1
     *             update A[row][column] with
     *               A[row][column] += A[row - 1][column]
     *  find area for that row
     * and update maximum area in histogram for each row so far
     */

    public int maxAreaRectangle(int a[][]) {
        //Create a temporary array for size equal to number of columns of given array
        int tempRowOfMatrix[] = new int[a[0].length];
        int answer = 0;
        LargestRectangleAreaHistogram largArea = new LargestRectangleAreaHistogram();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 0) {
                    tempRowOfMatrix[j] = 0;
                } else {
                    tempRowOfMatrix[j] += a[i][j];
                }
            }
            answer = Math.max(answer, largArea.largestRectangleAreaStack(tempRowOfMatrix));
        }
        return answer;
    }

    public static void main(String args[]) {
        MaximumRectangeInMatrix mrm = new MaximumRectangeInMatrix();
        int a[][] =
                {{0, 1, 1, 0},
                        {1, 1, 1, 1},
                        {1, 1, 1, 1},
                        {1, 1, 0, 0},
                };

        System.out.print("Area of largest rectange : " + mrm.maxAreaRectangle(a));

    }
}
