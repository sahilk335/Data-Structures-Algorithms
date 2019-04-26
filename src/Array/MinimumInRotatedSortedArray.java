package Array;

public class MinimumInRotatedSortedArray {
    /*
     *@Author : Sahil
     * Date : 25 Apr 2019
     *
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
     *
     * Find the minimum element.
     *
     * You may assume no duplicate exists in the array.
     *
     * Example 1:
     *
     * Input: [3,4,5,1,2]
     * Output: 1
     * Example 2:
     *
     * Input: [4,5,6,7,0,1,2]
     * Output: 0
     *
     * References :
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/solution/
     *
     * Solution :
     *
     * 1. We have to find the inflation point. i.e the point where the array is rotated, because first element is the
     * smallest always.
     * 2. so if arr[mid]> arr[high] means something is wrong, and first element is on right side, so shift low to right
     *  low=mid+1;
     * 3. if arr[mid]<arr[high] means the array is normal, so we find first element in left side, and shift high to mid
     *
     * Before that please check if the array is not rotated at all, and then return arr[0].
     *
     */

    public int findMin(int[] nums) {

        //if array is not sorted then return first element
        if (nums[0] < nums[nums.length - 1])
            return nums[0];

        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        while (high > low) {
            mid = low + (high - low) / 2;
            if (nums[mid] > nums[high])
                low = mid + 1;
            else if (nums[mid] < nums[high])
                high = mid;
        }
        return nums[low];
    }


    public static void main(String[] args){
        MinimumInRotatedSortedArray minimumInRotatedSortedArray = new MinimumInRotatedSortedArray();
        int nums[]={3,4,5,1,2};
        System.out.println(minimumInRotatedSortedArray.findMin(nums));
    }
}
