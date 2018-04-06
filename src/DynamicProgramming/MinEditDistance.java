package DynamicProgramming;

public class MinEditDistance {
    /*
     *@Author : Sahil
     * Date : 05-Apr-2018
     *
     * Given two strings str1 and str2 and below operations that can performed on str1. Find minimum number
     * of edits (operations) required to convert ‘str1’ into ‘str2’.
     * Insert
     * Remove
     * Replace
     *
     * Input:   str1 = "geek", str2 = "gesek"
     * Output:  1
     * We can convert str1 into str2 by inserting a 's'.
     *
     * Input:   str1 = "cat", str2 = "cut"
     * Output:  1
     * We can convert str1 into str2 by replacing 'a' with 'u'.
     *
     * Input:   str1 = "sunday", str2 = "saturday"
     * Output:  3
     *
     * References :
     * https://web.stanford.edu/class/cs124/lec/med.pdf
     * https://www.youtube.com/watch?v=We3YDTzNXEk
     * https://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
     *
     * Solution :
     * 1. Column contains -> String that should be changed
     * 2. Row contains -> String that is target string
     * 3. put minimum of  up,left,and diagonal left +1 to current position
     */
    int minDistance(String transform, String target) {
        int col = transform.length() + 1;
        int row = target.length() + 1;

        int T[][] = new int[row][col];

        //Intialize row and column with 0,1,2, upto length
        for (int i = 0; i < row; i++) {
            T[i][0] = i;
        }
        for (int j = 0; j < col; j++) {
            T[0][j] = j;
        }


        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (target.charAt(i - 1) == transform.charAt(j - 1)) {
                    T[i][j] = T[i - 1][j - 1];
                } else {
                    T[i][j] = 1 + Math.min(T[i][j - 1], Math.min(T[i - 1][j], T[i - 1][j - 1]));
                }
            }
        }
        return T[row - 1][col - 1];
    }


    public static void main(String args[]) {
        MinEditDistance minDistance = new MinEditDistance();
        String transform = "sunday";
        String target = "saturday";
        System.out.print(minDistance.minDistance(transform, target));
    }
}


