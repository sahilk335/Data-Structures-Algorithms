package DynamicProgramming;

public class ProbabilityKnightInsideChess {
    /*
     *@Author : Sahil
     * Date : 20 May 2018
     *
     * Given an NxN chessboard and a Knight at position (x,y).
     * The Knight has to take exactly K steps, where at each step it chooses any of the 8 directions uniformly at
     * random. What is the probability that the Knight remains in the chessboard after taking K steps,
     * with the condition that it can’t enter the board again once it leaves it.
     *
     * Examples:
     *
     * Let's take:
     * 8x8 chessboard,
     * initial position of the knight : (0, 0),
     * number of steps : 1
     * At each step, the Knight has 8 different positions to choose from.
     *
     * If it starts from (0, 0), after taking one step it will lie inside the
     * board only at 2 out of 8 positions, and will lie outside at other positions.
     * So, the probability is 2/8 = 0.25
     *
     * Solution :
     * N is size of the board
     * start_x,start_y are the starting positions
     * steps are the number of steps it would take
     *
     * DP bottom case
     * when steps =0
     * Probability of knight inside chess is 1 at each block
     *
     * References :
     * https://www.geeksforgeeks.org/probability-knight-remain-chessboard/
     * https://leetcode.com/problems/knight-probability-in-chessboard/description/
     */

    int N = 8;

    int dx[] = {1, 2, 2, 1, -1, -2, -2, -1};

    int dy[] = {2, 1, -1, -2, -2, -1, 1, 2};

    // returns true if the knight is
    // inside the chessboard
    boolean inside(int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N);
    }

    double findProb(int start_x, int start_y, int steps) {

        double[][][] dp = new double[N][N][N];

        //Step 0 : Probability at each step is 1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j][0] = 1;
            }
        }

        //For each step
        for (int step = 1; step <= steps; step++) {
            //For each position (i,j)
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    double prob = 0.0;

                    //For every reachable position from (i,j) of previous step
                    for (int k = 0; k < 8; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if (inside(nx, ny)) {
                            prob += dp[nx][ny][step - 1]/8.0;
                        }
                    }

                    dp[x][y][step]=prob;
                }
            }
        }

        return dp[start_x][start_y][steps];
    }

    public static void main(String[] args) {
        ProbabilityKnightInsideChess probabilityKnightInsideChess = new ProbabilityKnightInsideChess();
        System.out.print(probabilityKnightInsideChess.findProb(0, 0, 3));

    }
}
