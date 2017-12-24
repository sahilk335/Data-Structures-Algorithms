package Backtracking_DFS_BFS;

import Sort.QuickSort;

import java.util.Arrays;
import java.util.Scanner;

import static Sort.QuickSort.swap;
import static Sort.QuickSort.printArray;

/*
Problem : We need to print all subsets whose elements sum is equal to given sum

It is given that there is no duplicates and subset always exist

 Example : 3,1,2,4,5
 Given sum : 5
 Answer
 5
 3,2
 4,1
https://www.geeksforgeeks.org/backttracking-set-4-subset-sum/

Solution :
Step 1 : Sort the array
Step 2 : Apply all combination with pruning
 */

public class SubsetSum {

    int sumAvailable = 0;

    void findSubset(int arr[], int sum, int visited[], int index) {
        sumAvailable = 1;
        if (sum == 0) {
            for (int i = 0; i < visited.length; i++) {
                if (visited[i] == 1) {
                    System.out.print(arr[i] + ",");
                }
            }
            System.out.print("\n");
            return;
        }

        for (int i = index; i < arr.length; i++) {
            if (visited[i] == 1)
                continue;
            visited[i] = 1;
            findSubset(arr, sum - arr[i], visited, i + 1);
            visited[i] = 0;
        }
    }

    void findSubsetUtil(int arr[], int sum) {
        int visited[] = new int[arr.length];
        findSubset(arr, sum, visited, 0);
    }

    public static void main(String args[]) {
        int arr[] = {9, 8, 15, 4, 3, 2, 1, 5, 10, 32};
        Arrays.sort(arr);
        SubsetSum subsetSum = new SubsetSum();
        Scanner scanner = new Scanner(System.in);
        int givenSum = scanner.nextInt();
        subsetSum.findSubsetUtil(arr, givenSum);
    }
}
