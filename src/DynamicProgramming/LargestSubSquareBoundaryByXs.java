package DynamicProgramming;

public class LargestSubSquareBoundaryByXs {
    /*
     *@Author : Sahil
     *Date : 30 March 2018
     * References :
     * https://www.geeksforgeeks.org/given-matrix-o-x-find-largest-subsquare-surrounded-x/
     * https://www.youtube.com/watch?v=vi_1eHCsR9A&t=1s
     *
      Find maximum subsquare in a matrix made up of Xs and Os such that all four sides of subsquare are Xs.
       It does not matter what is inside
     * the subsquare. All 4 sides should be made up entirely of Xs
     *
     * e.g
     * 0 0 0 0 0 X         0,0  0,0  0,0  0,0  0,0  1,1
     * 0 X 0 X X X         0,0  1,1  0,0  1,1  1,2  2,3
     * 0 X 0 X 0 X         0,0  2,1  0,0  2,1  0,0  3,1
     * 0 X X X X X         0,0  3,1  1,2  3,3  1,4  4,5
     * 0 0 0 0 0 0         0,0  0,0  0,0  0,0  0,0  0,0
     *
     * Output of above program should be 3
     *
     * Solution
     * Have another matrix which is capable of holding 2 values hori and ver.
     * Ver stores how far vertically you can see Xs. Hori stores how far horizontally you can see Xs.
     * Once this matrix is build look for biggest subsquare by getting min of hori and ver at each point and checking
     * if subsquare can be formed from value min to 1.
     *
     * //start iterating from bottom right corner and find min of hori or ver at every cell.
     *  /If this is greater than 1 then see if you can find a number between this min and 1
     *  //such that on left's ver and top's hori is greater greater than or equal to k.
     *
     * Test cases:
     * Matrix entirely made up of Xs
     * Matrix entirely made up of Os
     * Matrix with Xs and Os but maximum subsquare is length 1
     */


    class Cell {
        int hor;
        int ver;
    }

    public int largestXBoundary(char input[][]) {
        int answer = 0;
        int row = input.length;
        int col = input[0].length;
        Cell T[][] = new Cell[row][col];
        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T[0].length; j++) {
                T[i][j] = new Cell();
            }
        }

        //create the dp matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (input[i][j] == 'X') {
                    if (i == 0 && j == 0) {
                        T[i][j].hor = 1;
                        T[i][j].ver = 1;
                    } else if (i == 0) {
                        T[i][j].hor = 1 + T[i][j - 1].hor;
                        T[i][j].ver = 1;
                    } else if (j == 0) {
                        T[i][j].hor = 1;
                        T[i][j].ver = 1 + T[i - 1][j].ver;
                    } else {
                        T[i][j].hor = 1 + T[i][j - 1].hor;
                        T[i][j].ver = 1 + T[i - 1][j].ver;
                    }
                }
            }
        }

        //start iterating from bottom right corner and find min of hori or ver at every cell.
        //If this is greater than 1 then see if you can find a number between this min and 1
        // such that on left's ver and top's hori is greater greater than or equal to k.


        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (T[i][j].ver == 0 || T[i][j].ver == 1 || T[i][j].hor == 0 || T[i][j].hor == 1) {
                    continue;
                }
                int maxSquare = Math.min(T[i][j].hor, T[i][j].ver);
                //If we already have the bigger answer then dont check else check
                if (answer < maxSquare) {
                    int k = 0;
                    for (k = maxSquare; k > 1; k--) {
                        //left's vertical and up's horizontal should cross each other to make a square
                        if (T[i][j - k + 1].ver >= k && T[i - k + 1][j].hor >= k) {
                            break;
                        }
                    }
                    answer = Math.max(answer, k);
                }
            }
        }

        //printMatrix(T);
        return answer;
    }

    public void printMatrix(Cell T[][]) {
        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T[0].length; j++) {
                System.out.print(T[i][j].hor + "," + T[i][j].ver + "  ");
            }
            System.out.print("\n");
        }
        System.out.print(T);
    }

    public static void main(String args[]) {
        LargestSubSquareBoundaryByXs largest = new LargestSubSquareBoundaryByXs();
        char matrix[][] =
                {{'X', 'O', 'X', 'X', 'X', 'X'},
                        {'X', 'O', 'X', 'X', 'O', 'X'},
                        {'X', 'X', 'X', 'O', 'O', 'X'},
                        {'O', 'X', 'X', 'X', 'X', 'X'},
                        {'X', 'X', 'X', 'O', 'X', 'O'},
                        {'O', 'O', 'X', 'O', 'O', 'O'},
                };
        System.out.print(largest.largestXBoundary(matrix));
    }
}
