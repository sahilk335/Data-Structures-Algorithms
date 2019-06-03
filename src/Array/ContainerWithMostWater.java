package Array;

public class ContainerWithMostWater {
    /*
     *@Author : Sahil
     * Date : 03 June 2019
     * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
     *
     * Note: You may not slant the container and n is at least 2.
     *
     * Example:
     *
     * Input: [1,8,6,2,5,4,8,3,7]
     * Output: 49
     *
     *
     * References :
     *  https://leetcode.com/problems/container-with-most-water/
     *
     *  Solution :
     *  1. Take 2 pointer one at starting and one at end . calculate the area
     *  2. move the pointer pointing to the shorter line towards the other end by one step. keep calculating area
     *  3. return maximum
     *
     */
    public int maxArea(int[] height) {
        int maxArea = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(containerWithMostWater.maxArea(height));
    }

}
