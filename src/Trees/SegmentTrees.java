package Trees;

import java.util.Scanner;

/*
* A segment tree is a tree data structure for storing intervals, or segments. It allows
 * for faster querying (e.g sum or min) in these intervals.
 * Write a program to support these operations for sum
 * createSegmentTree(int arr[]) - create segment tree
 * query(int segment[], int startRange, int endRange) - query in this range
 *
 * Similar segment trees can be created for min or max.
 *
 * Time complexity to create segment tree is O(nlogn)
 * Space complexity to create segment tree is O(nlogn)
 * Time complexity to search in segment tree is O(logn)

 * References
 * http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 * https://he-s3.s3.amazonaws.com/media/uploads/a0c7f90.jpgz <- segment tree index diagram
 * http://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/

                  1
            1           3
        1     2       3     4

        -> Segment array -> 1 1 3 1 2 3 4

        as segment tree is complete and full binary tree

        Total no. of nodes at leaf nodes n= arr.length= 2^h
        so H=log n
        convert to log base 10
        H =log n / log 2
        now take the ceil as it is integer , if not it will
        autoincrement 1
        H = ceil(log n / log 2)

        number of elements in segment tree array is
        2^H+1 -1 = size of segment tree array
         */
public class SegmentTrees {

    void makeSegmentTree(int segment[], int arr[], int low, int high, int pos) {
        if (low == high) {
            segment[pos] = arr[low];
            return;
        }
        int mid = (low + high) / 2;
        makeSegmentTree(segment, arr, low, mid, 2 * pos + 1);
        makeSegmentTree(segment, arr, mid + 1, high, 2 * pos + 2);
        segment[pos] = Math.min(segment[2 * pos + 1], segment[2 * pos + 2]);
    }

    /*
    there are 3 cases :

    1. parital overlap - search in both sides
    2. no overlap - return max in case of range min query
    3. total overlap - return the integer.

     */

    int rangeMinQuery(int segment[], int low, int high, int qlow, int qhigh, int pos) {

        //case of total overlap
        if (qlow <= low && qhigh >= high) {
            return segment[pos];
        }
        //case of no overlap
        if (qlow > high || qhigh < low) {
            return Integer.MAX_VALUE;
        }
        int mid = (low + high) / 2;

        //partial overlap case
        return Math.min(rangeMinQuery(segment, low, mid, qlow, qhigh, 2 * pos + 1),
                rangeMinQuery(segment, mid + 1, high, qlow, qhigh, 2 * pos + 2));
    }

    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String args[]) {
        int arr[] = {1, 2, 3, 4};
        int totalLeafNodes = arr.length;
        int heightSegmentTree = (int) Math.ceil(Math.log(totalLeafNodes) / Math.log(2));
        int segmentTree[] = new int[(int) (Math.pow(2, heightSegmentTree + 1) - 1)];
        SegmentTrees segmentTrees = new SegmentTrees();
        segmentTrees.makeSegmentTree(segmentTree, arr, 0, arr.length - 1, 0);
        printArray(segmentTree);
        System.out.print("Enter interval to find min : ");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.print("Minimum in interval " + a + " and " + b + " :" + segmentTrees.rangeMinQuery(segmentTree, 0, arr.length - 1, a, b, 0));
    }
}
