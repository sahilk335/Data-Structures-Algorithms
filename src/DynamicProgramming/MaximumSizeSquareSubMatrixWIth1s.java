package DynamicProgramming;

public class MaximumSizeSquareSubMatrixWIth1s {
    /*
     *@Author : Sahil
     * Date : 29 March 2018
     *
     *Given a binary matrix, find out the maximum size square sub-matrix with all 1s.
     *
     * References :
     * https://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
     *https://www.youtube.com/watch?v=g8bSdXCG-lA
     *
     *
     * Solution :
     * 1) Construct a sum matrix S[R][C] for the given M[R][C].
     *      a)    Copy first row and first columns as it is from M[][] to S[][]
     *      b)    For other entries, use following expressions to construct S[][]
     *          If M[i][j] is 1 then
     *             S[i][j] = min(S[i][j-1], S[i-1][j], S[i-1][j-1]) + 1
     *          Else
     *          If M[i][j] is 0
     *     S[i][j] = 0
     *            2) Find the maximum entry in S[R][C]
     * 3) Using the value and coordinates of maximum entry in S[i], print
     * sub-matrix of M[][]
     */

    public int maxSizeSquareSubMatrix(int T[][]) {
        int row = T.length;
        int col = T[0].length;
        int dp[][] = new int[row][T[0].length];
        int answer = 0;
        //Copy First column from given matrix
        for (int i = 0; i < row; i++) {
            dp[i][0] = T[i][0];
            answer = Math.max(answer, dp[i][0]);   //TEST CASE : when only row is filled
        }
        //Copy First row from given matrix
        for (int j = 0; j < col; j++) {
            dp[0][j] = T[0][j];                 //TEST CASE : when only col is filled
            answer = Math.max(answer, dp[0][j]);
        }

        //Apply the logic, if T[i,j]==1, then DP(i,j)=1+min(up,left and diagonal(up,left) element)
        //if T[i][j]==0 -> dp[i][j]=0
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (T[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }


        return answer;
    }

    public static void main(String args[]) {
        MaximumSizeSquareSubMatrixWIth1s msm = new MaximumSizeSquareSubMatrixWIth1s();
        int T[][] = {{0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}};
        System.out.print(msm.maxSizeSquareSubMatrix(T));
    }
}
