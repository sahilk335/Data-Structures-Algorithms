package Array;

public class SearchInRotatedSortedArray {
    /*
     *@Author : Sahil
     * Date : 03 May 2018
     *
     * An element in a sorted array can be found in O(log n) time via binary search. But suppose we rotate an ascending
     * order sorted array at some pivot unknown to you beforehand. So for instance, 1 2 3 4 5 might become 3 4 5 1 2.
     * Devise a way to find an element in the rotated array in O(log n) time.
     * Input  : arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
     *         key = 3
     * Output : Found at index 8
     *
     * Input  : arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
     *          key = 30
     * Output : Not found
     *
     * Input : arr[] = {30, 40, 50, 10, 20}
     *         key = 10
     * Output : Found at index 3
     *
     * References :
     * https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/
     * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
     *
     *
     * Solution :
     * 1) Find middle point mid = (l + h)/2
     * 2) If key is present at middle point, return mid.
     * 3) Else If arr[l..mid] is sorted
     *     a) If key to be searched lies in range from arr[l]
     *       to arr[mid], recur for arr[l..mid].
     *    b) Else recur for arr[mid+1..r]
     * 4) Else (arr[mid+1..r] must be sorted)
     *     a) If key to be searched lies in range from arr[mid+1]
     *       to arr[r], recur for arr[mid+1..r].
     *    b) Else recur for arr[l..mid]
     *
     *
     *    NOTE : ALL ELEMENTS MUST BE UNIQUE
     */

    int search(int[] A, int target) {
        int n=A.length;
        int lo = 0;
        int hi = n - 1;

        //Find inflection point
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (A[mid] > A[hi])
                lo = mid + 1;
            else
                hi = mid;
        }

        //Now lo is at inflection point and also the number of places rotated
        int rot = lo;
        lo = 0;
        hi = n - 1;

        //usual binary search
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int realmid = (mid + rot) % n;
            if (A[realmid] == target) return realmid;
            if (A[realmid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }


    public static void main(String[] args) {
        SearchInRotatedSortedArray search = new SearchInRotatedSortedArray();
        int[] arr = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        System.out.print(search.search(arr, 1));
    }


}
