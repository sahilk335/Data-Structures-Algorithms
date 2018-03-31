package DynamicProgramming;

public class MaximumSumRectangularSubMatrix {
    /*
     *@Author : Sahil
     * Date : 31 March 2018
     *
     * Write a program to find maximum sum rectangle in give 2D matrix.
     *
     *
     * Solution:
     * Keep temp array with size as number of rows. Start left and right from 0
     * and keep adding values for each row and maintain them in this temp array.
     * Run Kadane's algorithm to find max sum subarray in temp. Now increment right by
     * 1. When right reaches last column reset right to 1 and left to 1.
     *
     * Space complexity of this algorithm is O(row)
     * Time complexity of this algorithm is O(row*col*col)
     *
     * References :
     * https://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
     * https://www.youtube.com/watch?v=yCQN096CwWM
     *
     */

    public int maximumSumSubRectangle(int T[][]) {
        int answer = 0;
        int row = T.length;
        int col = T[0].length;
        int currSum;
        int left;
        int right;
        int maxSum = Integer.MIN_VALUE;
        int maxLeft = 0;
        int maxRight = 0;
        int maxUp = 0;
        int maxDown = 0;
        int rowArr[] = new int[row];

        //Use Kadane's algorithm to find maxmimum sum continguous subarray
        Kadane kadane;

        for (left = 0; left < col; left++) {

            //Initialize rowArray with 0;
            for (int i = 0; i < row; i++) {
                rowArr[i] = 0;
            }

            //start Left and right operation
            for (right = left; right < col; right++) {
                for (int i = 0; i < row; i++) {
                    rowArr[i] += T[i][right];
                }
                kadane = maxSumSubarray(rowArr);
                if (kadane.currSum > maxSum) {
                    maxLeft = left;
                    maxRight = right;
                    maxSum = kadane.currSum;
                    maxUp = kadane.start;
                    maxDown = kadane.end;
                }
            }
        }

        //Print the maxSumSubRectangle
        for (int i = maxUp; i <= maxDown; i++) {
            for (int j = maxLeft; j <= maxRight; j++) {
                System.out.print(T[i][j] + "\t");
            }
            System.out.print("\n");
        }

        return maxSum;
    }

    public Kadane maxSumSubarray(int a[]) {

        Kadane sum = new Kadane();
        int maxEndng = a[0];
        int start;
        int end;
        int maxSumSoFar = Integer.MIN_VALUE;
        for (int i = 1; i < a.length; i++) {
            maxEndng = Math.max(a[i], a[i] + maxEndng);
            if (maxEndng == a[i]) {
                sum.start = i;
            }
            maxSumSoFar = Math.max(maxSumSoFar, maxEndng);
            if (maxSumSoFar == maxEndng) {
                sum.end = i;
            }
        }

        sum.currSum = maxSumSoFar;

       /* for(int i=sum.start;i<=sum.end;i++){
            System.out.print(a[i]+",");
        }
        System.out.print("\n"+sum.currSum);*/
        return sum;
    }

    class Kadane {
        int currSum;
        int start;
        int end;
    }

    public static void main(String args[]) {
        int arr[][] = {{1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        };
        int a[] = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};

        MaximumSumRectangularSubMatrix msr = new MaximumSumRectangularSubMatrix();
        System.out.println("\n" + msr.maximumSumSubRectangle(arr));
        //System.out.print(msr.maxSumSubarray(a));
    }
}
