package Array;

public class JminusIMaximum {
    /*
     *@Author : Sahil
     * Date : 1 April 2018
     *
     * Given an array arr[], find the maximum j – i such that arr[j] > arr[i].
     *
     * Input: {34, 8, 10, 3, 2, 80, 30, 33, 1}
     * Output: 6  (j = 7, i = 1)
     *
     * Input: {9, 2, 3, 4, 5, 6, 7, 8, 18, 0}
     * Output: 8 ( j = 8, i = 0)
     *
     * Input:  {1, 2, 3, 4, 5, 6}
     * Output: 5  (j = 5, i = 0)
     *
     * Input:  {6, 5, 4, 3, 2, 1}
     * Output: -1
     *
     * Solution :
     * 1.Make leftCritical Array and Start from the left and maintain the critical points that can give answer
     * 2.Make rightCritical Array and Start from right and maintain the critical points that can give answer
     * 3.while (i < len && j < len) {
     *       if(a[leftCriticalArr[i]]<a[righCriticalArr[j]]){
     *          answer=Math.max(answer,j-i);
     *        j++;
     *   }else{
     *     i++;
     *  }
     *  }
     *
     *  References :
     * https://www.geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
     */

    public int maxJminusI(int a[]) {
        int answer = -1;
        int len = a.length;
        int leftCriticalArr[] = new int[len];
        int righCriticalArr[] = new int[len];

        //Initialize left and right first and last index respectively
        leftCriticalArr[0] = a[0];
        righCriticalArr[len - 1] = a[len - 1];

        int min = 0;
        //Maintain left critical points
        for (int i = 0; i < len; i++) {
            if (a[i] < a[min])
                min = i;
            leftCriticalArr[i] = min;
        }

        int max = len - 1;
        //Maintain right critical points
        for (int j = len - 1; j >= 0; j--) {
            if (a[j] > a[max])
                max = j;
            righCriticalArr[j] = max;
        }

        int i = 0;
        int j = 0;
        while (i < len && j < len) {
            if (a[leftCriticalArr[i]] < a[righCriticalArr[j]]) {
                answer = Math.max(answer, j - i);
                j++;
            } else {
                i++;
            }
        }

          /*  System.out.print("\n");
            for ( i = 0; i < len; i++) {
                System.out.print(leftCriticalArr[i] + " ");
            }

            System.out.print("\n");
            for ( i = 0; i < len; i++) {
                System.out.print(righCriticalArr[i] + " ");
            }*/

        return answer;
    }

    public static void main(String args[]) {
        JminusIMaximum jmi = new JminusIMaximum();
        int arr[] = {6, 5, 4, 3, 2, 1};
        System.out.print("\n" + "Answer : " + jmi.maxJminusI(arr));
    }
}
