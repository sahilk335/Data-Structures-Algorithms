package Array;

public class ImageOverlap {
    /**
     * Problem : https://leetcode.com/problems/image-overlap/
     *
     * You are given two images img1 and img2 both of size n x n, represented as binary, square matrices of the same size. (A binary matrix has only 0s and 1s as values.)
     * <p>
     * We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.
     * <p>
     * (Note also that a translation does not include any kind of rotation.)
     * <p>
     * What is the largest possible overlap?
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
     * Output: 3
     * Explanation: We slide img1 to right by 1 unit and down by 1 unit.
     * <p>
     * The number of positions that have a 1 in both images is 3. (Shown in red)
     * <p>
     * Example 2:
     * <p>
     * Input: img1 = [[1]], img2 = [[1]]
     * Output: 1
     * Example 3:
     * <p>
     * Input: img1 = [[0]], img2 = [[0]]
     * Output: 0
     * <p>
     * <p>
     * <p>
     * Solution :
     * <p>
     * start
     * <p>
     * <p>
     * Solution :
     * youtube.com/watch?v=LBDaYdp0G4Y&t=509s
     * <p>
     * Time Complexity : O(N^4)
     * Space Complexity : O(N^2)
     *
     * traverse through all possible overlapping zones, by shifting the image matrix, and then
     * simply count within each overlapping zone and keep updating max
     */

    int n;
    int ans;

    public int largestOverlap(int[][] img1, int[][] img2) {

        ans = 0;
        n = img1.length;

        for (int xShift = 0; xShift < n; xShift++) {
            for (int yShift = 0; yShift < n; yShift++) {
                countOverlap(xShift, yShift, img1, img2);
                countOverlap(xShift, yShift, img2, img1);
            }
        }

        return ans;
    }


    public void countOverlap(int xShift, int yShift, int[][] A, int[][] B) {

        int count = 0;
        for (int i = xShift; i < n; i++) {
            for (int j = yShift; j < n; j++) {
                count += A[i][j] * B[i - xShift][j - yShift]; //00 overlap -> 0 , 01 overlap ->0 11overlap ->1
            }
        }
        ans = Math.max(count, ans);
    }

    public static void main(String[] args) {

        int img1[][] = {
                {0,0,0,0,1},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
        int img2[][] = {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,1}
        };
        ImageOverlap imageOverlap = new ImageOverlap();
        System.out.println(imageOverlap.largestOverlap(img1, img2));
    }
}
