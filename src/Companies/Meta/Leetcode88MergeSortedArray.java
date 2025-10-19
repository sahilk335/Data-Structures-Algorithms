/* Leetcode-88 Merge Sorted Array.java */

import java.util.Arrays;

class Leetcode88MergeSortedArray {

    /**
     * =================================================================================
     * PICTORIAL ALGORITHM: MERGE SORTED ARRAYS (Two-Pointer Approach from End)
     * =================================================================================
     * 
     * ALGORITHM OVERVIEW:
     * We merge two sorted arrays by comparing elements from the END and filling
     * the result array from the END. This avoids overwriting unprocessed elements.
     * 
     * KEY INSIGHT: Since both arrays are sorted, the largest remaining element
     * will always be at the current pointer position of either array.
     * 
     * =================================================================================
     * STEP-BY-STEP VISUALIZATION:
     * =================================================================================
     * 
     * INITIAL STATE:
     * nums1 = [1, 2, 3, 0, 0, 0]    nums2 = [2, 5, 6]
     *         ↑     ↑     ↑                    ↑     ↑
     *         p1=2  p1=1  p1=0                 p2=2  p2=1
     *         (m=3)                            (n=3)
     * 
     * p = 5 (fill position from end)
     * 
     * =================================================================================
     * ITERATION 1: Compare nums1[2]=3 vs nums2[2]=6
     * =================================================================================
     * nums1[2] = 3 < nums2[2] = 6
     * 
     * nums1 = [1, 2, 3, 0, 0, 6]    nums2 = [2, 5, 6]
     *         ↑     ↑     ↑                    ↑     ↑
     *         p1=2  p1=1  p1=0                 p2=1  p2=0
     * 
     * p = 4, p2 = 1
     * 
     * =================================================================================
     * ITERATION 2: Compare nums1[2]=3 vs nums2[1]=5
     * =================================================================================
     * nums1[2] = 3 < nums2[1] = 5
     * 
     * nums1 = [1, 2, 3, 0, 5, 6]    nums2 = [2, 5, 6]
     *         ↑     ↑     ↑                    ↑
     *         p1=2  p1=1  p1=0                 p2=0
     * 
     * p = 3, p2 = 0
     * 
     * =================================================================================
     * ITERATION 3: Compare nums1[2]=3 vs nums2[0]=2
     * =================================================================================
     * nums1[2] = 3 > nums2[0] = 2
     * 
     * nums1 = [1, 2, 3, 3, 5, 6]    nums2 = [2, 5, 6]
     *         ↑     ↑     ↑
     *         p1=1  p1=0  p1=-1
     * 
     * p = 2, p1 = 1
     * 
     * =================================================================================
     * ITERATION 4: Compare nums1[1]=2 vs nums2[0]=2
     * =================================================================================
     * nums1[1] = 2 == nums2[0] = 2 (take from nums2)
     * 
     * nums1 = [1, 2, 2, 3, 5, 6]    nums2 = [2, 5, 6]
     *         ↑     ↑
     *         p1=0  p1=-1
     * 
     * p = 1, p2 = -1
     * 
     * =================================================================================
     * ITERATION 5: p2 < 0, exit main loop
     * =================================================================================
     * 
     * =================================================================================
     * CLEANUP: Copy remaining elements from nums1 (if any)
     * =================================================================================
     * p1 = 0, p2 = -1
     * Since p1 >= 0, copy nums1[0] = 1 to position p = 0
     * 
     * FINAL RESULT:
     * nums1 = [1, 2, 2, 3, 5, 6]
     * 
     * =================================================================================
     * ALGORITHM PSEUDOCODE:
     * =================================================================================
     * 1. Initialize three pointers:
     *    - p1 = m-1 (last valid element in nums1)
     *    - p2 = n-1 (last element in nums2)  
     *    - p = m+n-1 (position to fill in nums1)
     * 
     * 2. While both arrays have elements (p1 >= 0 AND p2 >= 0):
     *    a. Compare nums1[p1] with nums2[p2]
     *    b. Place the LARGER element at nums1[p]
     *    c. Move the pointer of the array we took from left
     *    d. Move fill pointer p left
     * 
     * 3. If nums2 still has elements (p2 >= 0):
     *    - Copy remaining elements from nums2 to nums1
     * 
     * 4. If nums1 still has elements (p1 >= 0):
     *    - They are already in correct position, no action needed
     * 
     * =================================================================================
     * TIME COMPLEXITY: O(m + n) - Each element is visited exactly once
     * SPACE COMPLEXITY: O(1) - Only using constant extra space
     * =================================================================================
     * 
     * You are given two integer arrays nums1 and nums2, sorted in non-decreasing
     * order, and two integers m and n, representing the number of elements in nums1
     * and nums2 respectively.
     * 
     * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
     * 
     * The final sorted array should not be returned by the function, but instead be
     * stored inside the array nums1. To accommodate this, nums1 has a length of m +
     * n, where the first m elements denote the elements that should be merged, and
     * the last n elements are set to 0 and should be ignored. nums2 has a length of
     * n.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * Output: [1,2,2,3,5,6]
     * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
     * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming
     * from nums1.
     * Example 2:
     * 
     * Input: nums1 = [1], m = 1, nums2 = [], n = 0
     * Output: [1]
     * Explanation: The arrays we are merging are [1] and [].
     * The result of the merge is [1].
     * Example 3:
     * 
     * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
     * Output: [1]
     * Explanation: The arrays we are merging are [] and [1].
     * The result of the merge is [1].
     * Note that because m = 0, there are no elements in nums1. The 0 is only there
     * to ensure the merge result can fit in nums1.
     * 
     * 
     * Constraints:
     * 
     * nums1.length == m + n
     * nums2.length == n
     * 0 <= m, n <= 200
     * 1 <= m + n <= 200
     * -109 <= nums1[i], nums2[j] <= 109
     * 
     */

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1; // pointer to last valid in nums1
        int p2 = n - 1; // pointer to last in nums2
        int p = m + n - 1; // pointer to fill from end of nums1

        // Merge until one array is exhausted
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1]; // take from nums1 if larger
                p1--; // move p1 left
            } else {
                nums1[p] = nums2[p2]; // otherwise take from nums2
                p2--; // move p2 left
            }
            p--; // move fill pointer left
        }

        // If nums2 still has elements, copy them
        while (p2 >= 0) {
            nums1[p] = nums2[p2]; // copy remaining
            p2--;
            p--;
        }
        // If p1 >= 0, those are already in place
    }

    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        int[] nums2 = { 2, 5, 6 };
        Leetcode88MergeSortedArray solution = new Leetcode88MergeSortedArray();

        solution.merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
