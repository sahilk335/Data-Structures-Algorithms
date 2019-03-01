package Backtracking_DFS_BFS;

public class Combination {
    /*
     *@Author : Sahil
     * Date : 1 March 2019
     *
     * Generate all combination given N and R
     * Generate all combination nCr
     *
     * Example :
     *
     * given numbers 1234 , n=4,r=3
     * 123
     * 124
     * 234
     * 134
     */

    public void generateCombinations(int[] arr, int[] combArray, int r, int index, int i) {
        if (index == r) {
            for (int num : combArray) {
                System.out.print(num + " ");
            }
            System.out.println("");
            return;
        }

        if (i >= arr.length)
            return;

        combArray[index] = arr[i];


        generateCombinations(arr, combArray, r, index + 1, i + 1);
        generateCombinations(arr, combArray, r, index, i + 1);
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4};
        int r = 3;
        int[] combArr = new int[r];
        Combination comb = new Combination();
        comb.generateCombinations(arr, combArr, r, 0, 0);
    }
}
