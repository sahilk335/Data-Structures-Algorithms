package DynamicProgramming;

public class OptimalGameEndArray {
    /*
     *@Author : Sahil
     * Date : 04 April 2018
     *
     * Given an array of integers, there are 2 players, you and other player.
     * you have to collect maximum sum of integers. you can only select integer from either end of an array
     *
     * Solution :
     * 1 traverse digonally ->
     *          when you traverse diagonally, you check if only item 0,0 ..1,1 ... n,n exist
     *          then you check if item 1,2....2.3....3,4.....n-1,n exist and so on till 0,n
     *      How to traverse :
     *      for(int i=0; i<n ;i++){
     *          for(int l=0; j=0 ;j<=i;j++ ; l++){
     *              {
     *                 print T[l][j]
     *              }
     *          }
     *
     *
     * References :
     * https://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game/
     * https://www.youtube.com/watch?v=WxpIHvsu1RI
     */

    class pair {
        int first;
        int second;
    }

    int optimalValue(int arr[]) {
        int row = arr.length;
        int col = arr.length;

        pair T[][] = new pair[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                T[i][j] = new pair();
            }
        }

        //Intialize diagonal elements
        for (int i = 0; i < arr.length; i++) {
            T[i][i].first = arr[i];
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i, l = 0; j < arr.length; j++, l++) {
                T[l][j].first = Math.max((arr[l] + T[l + 1][j].second), (arr[j] + T[l][j - 1].second));
                if (T[l][j].first == arr[l] + T[l + 1][j].second) {
                    T[l][j].second = T[l + 1][j].first;
                } else {
                    T[l][j].second = T[l][j - 1].first;
                }
            }
        }

        return T[0][col - 1].first;
    }


    public static void main(String args[]) {
        OptimalGameEndArray optimalgame = new OptimalGameEndArray();
        int arr[] = {5, 3, 7, 10};
        System.out.print("\n" + optimalgame.optimalValue(arr));
    }
}
