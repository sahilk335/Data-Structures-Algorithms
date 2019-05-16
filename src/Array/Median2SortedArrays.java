package Array;

public class Median2SortedArrays {
    /*
     *@Author : Sahil
     * Date : 16 May 2019
     *
     * There are two sorted arrays nums1 and nums2 of size m and n respectively.
     *
     * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
     *
     * You may assume nums1 and nums2 cannot be both empty.
     *
     * Example 1:
     *
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * The median is 2.0
     * Example 2:
     *
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     *
     * The median is (2 + 3)/2 = 2.5
     *
     * References :
     * https://leetcode.com/problems/median-of-two-sorted-arrays/
     * https://www.youtube.com/watch?v=LPFhl65R7ww&t=745s
     *
     * Solution :
     *  Take minimum size of two array. Possible number of partitions are from 0 to m in m size array.
     * Try every cut in binary search way. When you cut first array at i then you cut second array at (m + n + 1)/2 - i
     * Now try to find the i where a[i-1] <= b[j] and b[j-1] <= a[i]. So this i is partition around which lies the median.
     */

    public double median(int[] a, int[] b, int maxLeftA, int maxLeftB, int minRightA, int minRightB) {
        if ((a.length + b.length) % 2 == 0) {
            return ((double) Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2;
        }
        return (double) Math.max(maxLeftA, maxLeftB);
    }

    public double findMedianSortedArray(int[] A, int[] B) {
        //length of A array must be shorter than B
        if (A.length > B.length) {
            findMedianSortedArray(B, A);
        }
        int x = A.length;
        int y = B.length;

        int low = 0;
        int high = x;

        while (low <= high) {
            int partX = low + (high - low) / 2;
            int partY = (x + y + 1) / 2 - partX;

            int maxLeftA = partX == 0 ? Integer.MIN_VALUE : A[partX - 1];
            int minRightA = partX == x ? Integer.MAX_VALUE : A[partX];
            int maxLeftB = partY == 0 ? Integer.MIN_VALUE : B[partY - 1];
            int minRightB = partY == y ? Integer.MAX_VALUE : B[partY];

            //if we have partitioned array at correct place
            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                return median(A, B, maxLeftA, maxLeftB, minRightA, minRightB);
            } else if (maxLeftA > minRightB) {       //move partX to lower limit , so high to lower limit
                high = partX - 1;
            } else {
                low = partX + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Median2SortedArrays median = new Median2SortedArrays();
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(median.findMedianSortedArray(nums1, nums2));

    }
}
