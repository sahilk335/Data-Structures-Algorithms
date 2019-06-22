package Geometry;

public class RectangleOverlap {

    /*
     *@Author : Sahil Khurana
     * Date : 22 June 2019
     * A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left
     * corner, and (x2, y2) are the coordinates of its top-right corner.
     *
     * Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only
     * touch at the corner or edges do not overlap.
     *
     * Given two (axis-aligned) rectangles, return whether they overlap.
     *
     * Example 1:
     *
     * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
     * Output: true
     * Example 2:
     *
     * Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
     * Output: false
     * Notes:
     *
     * Both rectangles rec1 and rec2 are lists of 4 integers.
     * All coordinates in rectangles will be between -10^9 and 10^9
     *
     * References :
     * https://leetcode.com/problems/rectangle-overlap/
     *
     * Solution :
     * We just need to check where does the left-bottom and right-top of the second rectangle lie with respect to the
     * third rectangle. Cases that would not cause overlap are:
     *
     * if left-bottom of second rectangle is greater than right-top of first. (X-axis).
     * if right-top of second rectangle is less than left-bottom of first. (X-axis).
     * if left-bottom of second rectangle is greater than right-top of first. (Y-axis).
     * if right-top of second rectangle is less than left-bottom of first. (Y-axis).
     */

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int x1 = rec1[0], y1 = rec1[1];
        int x2 = rec1[2], y2 = rec1[3];
        int x3 = rec2[0], y3 = rec2[1];
        int x4 = rec2[2], y4 = rec2[3];

        if ((x3 >= x2) || (x4 <= x1) || (y3 >= y2) || (y4 <= y1))
            return false;
        return true;
    }
}
