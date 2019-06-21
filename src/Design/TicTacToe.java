package Design;

public class TicTacToe {
    /*
     *@Author : Sahil Khurana
     * Date : 21 June 2019
     *
     * Design Tic Tac Toe
     * Time Complexity : O(1)
     *
     * References :
     * https://leetfree.com/problems/design-tic-tac-toe.html
     */


        int[] rows;
        int[] cols;
        int dc1;
        int dc2;
        int n;
        /** Initialize your data structure here. */
        public TicTacToe(int n) {
            this.n=n;
            this.rows=new int[n];
            this.cols=new int[n];
        }

        /** Player {player} makes a move at ({row}, {col}).
         @param row The row of the board.
         @param col The column of the board.
         @param player The player, can be either 1 or 2.
         @return The current winning condition, can be either:
         0: No one wins.
         1: Player 1 wins.
         2: Player 2 wins. */
        public int move(int row, int col, int player) {
            int val = (player==1?1:-1);

            rows[row]+=val;
            cols[col]+=val;

            if(row==col){
                dc1+=val;
            }
            if(col==n-row-1){
                dc2+=val;
            }

            if(Math.abs(rows[row])==n
                    || Math.abs(cols[col])==n
                    || Math.abs(dc1)==n
                    || Math.abs(dc2)==n){
                return player;
            }

            return 0;
        }
}
