package Array;

public class FlipZerosForMaximumConsecutiveOne {
    /*
     *@Author : Sahil
     * Date : 09 April 2019
     *
     * Find zeroes to be flipped so that number of consecutive 1’s is maximized
     * Given a binary array and an integer m, find the position of zeroes flipping which creates maximum number of
     * consecutive 1’s in array.
     *
     * Examples :
     *
     * Input:   arr[] = {1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1}
     *          m = 2
     * Output:  5 7
     * We are allowed to flip maximum 2 zeroes. If we flip
     * arr[5] and arr[7], we get 8 consecutive 1's which is
     * maximum possible under given constraints
     *
     * Input:   arr[] = {1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1}
     *          m = 1
     * Output:  7
     * We are allowed to flip maximum 1 zero. If we flip
     * arr[7], we get 5 consecutive 1's which is maximum
     * possible under given constraints.
     *
     * Input:   arr[] = {0, 0, 0, 1}
     *         m = 4
     * Output:  0 1 2
     * Since m is more than number of zeroes, we can flip
     * all zeroes.
     *
     * References :
     * https://www.geeksforgeeks.org/find-zeroes-to-be-flipped-so-that-number-of-consecutive-1s-is-maximized/
     *
     * Solution :
     * 1. Slide the window WR till you get number of Zeroes in window <= K
     * 2. While sliding WR number of Zeroes(nZero) increased above K then start shifting WL to right side
     * 3. Do Steps 1 - 2 till WR reaches length of array
     *
     */

    int maxAns;

    int maxOnes(int[] arr, int k) {

        int n = arr.length;
        int wL = 0;
        int wR = 0;
        int nZero = 0;
        int ansWR = 0, ansWL = 0;
        while (wR < n) {
            if (nZero <= k) {
                if (arr[wR] == 0) {
                    nZero++;
                }
                wR++;
            }

            if (nZero > k) {
                if (arr[wL] == 0) {
                    nZero--;
                }
                wL++;
            }
            if (nZero <= k) {
                maxAns = Math.max(maxAns, wR - wL);
                ansWL = wL;
                ansWR = wR;
            }
        }

        for (int i = ansWL; i < ansWR; i++) {
            if (arr[i] == 0) {
                System.out.print(i + " ");
            }
        }
        return maxAns;
    }


    public static void main(String[] args) {
        FlipZerosForMaximumConsecutiveOne flipZero = new FlipZerosForMaximumConsecutiveOne();
        int arr[] = {1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1};
        int k = 1;
        System.out.println("\nMaximum number of Consecutive Ones : " + flipZero.maxOnes(arr, k));

    }
}
