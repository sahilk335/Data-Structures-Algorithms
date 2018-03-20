package DynamicProgramming;

public class SubsetSum {
    /*
     *@Author :Sahil
     * Date : 27 March 2018
     * Given an array of non negative numbers and a total, is there a subset of numbers in the array which adds
     * upto given total
     * Example : 1,2,3
     * Total : 4
     * Can be formed -> by 3,1
     * So, answer : yes
     *
     * Reference :
     * https://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/
     * Youtube video - https://youtu.be/s6FhG--P7z0
     *
     *
     */

    public boolean isSubsetSum(int a[], int sum) {
        //No. of column equals to sum , you have to form
        //No. of rows is the input array
        int col = sum + 1;
        int row = a.length;
        boolean T[][] = new boolean[row][col];
        //Given number can made its own sum, so mark all such as true
        for (int i = 0; i < a.length; i++) {
            if (a[i] < sum)
                T[i][a[i]] = true;
        }


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                //copy the true value from upper part
                if (i > 0 && T[i - 1][j] == true) {
                    T[i][j] = true;
                    continue;
                }

                if (i > 0 && j - a[i] >= 0 && T[i - 1][j - a[i]] == true) {
                    T[i][j] = T[i - 1][j - a[i]];
                }
            }
        }

        return T[row - 1][sum];
    }

    public static void main(String args[]) {
        SubsetSum ss = new SubsetSum();
        int T[] = {1, 2, 3};
        int sum = 6;
        System.out.print(ss.isSubsetSum(T, sum));
    }
}
