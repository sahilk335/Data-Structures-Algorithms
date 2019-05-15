package DynamicProgramming;

public class MatrixPrint {

    public static void print(int[][] dp) {
        int row = dp.length;
        int col = dp[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
