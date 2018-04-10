package Array;

import static Sort.QuickSort.swap;

public class Segregate012 {
    /*
     *@Author : Sahil
     * Date : 10 Apr 2018
     *
     * Solution : Dutch National Flag Algorithm
     *
     * a[1..Lo-1] zeroes (red)
     * a[Lo..Mid-] ones (white)
     * a[Mid..Hi] unknown
     * a[Hi+1..N] twos (blue)
     * The unknown region is shrunk while maintaining these conditions
     *
     * Lo := 1; Mid := 1; Hi := N;
     * while Mid <= Hi do
     * Invariant: a[1..Lo-1]=0 and a[Lo..Mid-1]=1 and a[Hi+1..N]=2; a[Mid..Hi] are unknown.
     * case a[Mid] in
     * 0: swap a[Lo] and a[Mid]; Lo++; Mid++
     * 1: Mid++
     * 2: swap a[Mid] and a[Hi]; Hi–-
     *
     * References :
     * https://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
     */

    public void dutchNationalFlag(int arr[]) {
        int low = 0;
        int mid = 0;
        int high = arr.length - 1;

        while (mid <= high) {
            switch (arr[mid]) {
                case 0: {
                    swap(arr, low, mid);
                    low++;
                    mid++;
                    break;
                }
                case 1: {
                    mid++;
                    break;
                }
                case 2: {
                    swap(arr, mid, high);
                    high--;
                    break;
                }
            }
        }


        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }


    public static void main(String args[]) {
        Segregate012 seg = new Segregate012();
        int[] arr = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        seg.dutchNationalFlag(arr);

    }
}
