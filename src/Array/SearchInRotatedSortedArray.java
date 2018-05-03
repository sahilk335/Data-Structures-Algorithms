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

    public int search(int[] nums, int target) {
        return searchInRotatedArray(nums, target, 0, nums.length - 1);
    }

    public int searchInRotatedArray(int[] arr, int key, int low, int high) {

        if (high >= low) {

            int mid = low + (high - low) / 2;

            //Step 1: check if key is the mid element
            if (key == arr[mid]) {
                return mid;
            }

            //Step 2 : check if left half is sorted.. if it is then right is obviously not sorted
            if (arr[low] <= arr[mid]) {
                //check if key lies inside the left array
                if (key <= arr[mid] && key >= arr[low]) {
                    return searchInRotatedArray(arr, key, low, mid - 1);
                } else {
                    return searchInRotatedArray(arr, key, mid + 1, high);
                }
            } else {
                //Step 3: if step 2 is false then , right half is sorted
                if (key >= arr[mid] && key <= arr[high]) {
                    return searchInRotatedArray(arr, key, mid + 1, high);
                } else {
                    return searchInRotatedArray(arr, key, low, mid - 1);
                }
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        SearchInRotatedSortedArray search = new SearchInRotatedSortedArray();
        int arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
        System.out.print(search.search(arr, 9));
    }


}
