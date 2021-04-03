package Array;

import java.util.Arrays;

public class Test {

    public int maxArea(int[] height) {
        int ans = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            ans = Math.max(area, ans);
            if (height[right] > height[left]) {
                left++;
            } else
                right--;
        }
        return ans;
    }

    public static void main(String[] args) {
        Test test = new Test();
        int arr[] = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(test.maxArea(arr));
    }

}
