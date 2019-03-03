package Backtracking_DFS_BFS;

import static Sort.QuickSort.swap;

public class Permutation {
    /*
     *@Author : Sahil
     * Date : 1 March 2019
     *
     * Generate all permutation given N and R
     * Generate all combination nPr
     * where n=r
     *
     * Example :
     *
     * given numbers 1234 , n=3,r=3
     * 123
     * 213
     * 321
     * 312
     * 231
     * 132
     */

    public void permutation(int[] arr, int[] permArr, boolean[] visited, int r, int index) {

        if (index == r) {
            for (int num : permArr) {
                System.out.print(num + " ");
            }
            System.out.println("");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            permArr[i] = arr[index];
            permutation(arr, permArr, visited, r, index + 1);
            visited[i] = false;

        }

    }

    public void inplacePermutation(int[] arr, int index) {
        if (index == arr.length - 1) {
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println("");
            return;
        }
        if (index >= arr.length)
            return;
        for (int i = index; i < arr.length; i++) {
            swap(arr, i, index);
            inplacePermutation(arr, index + 1);
            swap(arr, i, index);

        }
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3};
        int r = 3;
        int[] permarr = new int[r];
        boolean[] visited = new boolean[r];
        Permutation permutation = new Permutation();
        permutation.permutation(arr, permarr, visited, r, 0);
        permutation.inplacePermutation(arr,0);
    }
}
