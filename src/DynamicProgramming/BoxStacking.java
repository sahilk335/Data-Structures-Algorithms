package DynamicProgramming;

import Graphs.BinaryMinHeap;

import java.util.Arrays;

public class BoxStacking {
    /*
     *@Author : Sahil
     * Date : 5 April 2018
     *

     * Given different dimensions and unlimited supply of boxes for each dimension, stack boxes
     * on top of each other such that it has maximum height but with caveat that length and width
     * of box on top should be strictly less than length and width of box under it. You can
     * rotate boxes as you like.
     *
     * Solution
     *
     * 1) Create all rotations of boxes such that length is always greater or equal to width
     * 2) Sort boxes by base area in non increasing order (length * width). This is because box
     * with more area will never ever go on top of box with less area.
     * 3) Take T[] and result[] array of same size as total boxes after all rotations are done
     * 4) Apply longest increasing subsequence type of algorithm to get max height.
     *
     * If n number of dimensions are given total boxes after rotation will be 3n.
     * So space complexity is O(n)
     * Time complexity - O(nlogn) to sort boxes. O(n^2) to apply DP on it So really O(n^2)
     *
     * References
     * http://www.geeksforgeeks.org/dynamic-programming-set-21-box-stacking-problem/
     * http://people.cs.clemson.edu/~bcdean/dp_practice/
     */

    static class Box implements Comparable<Box> {
        int h;
        int l;
        int w;
        int area;

        Box(int l, int w, int h) {
            this.l = l;
            this.w = w;
            this.h = h;
        }

        @Override
        //returns decreasing order
        public int compareTo(Box o) {
            //subtract previos value from curr value<- returns negative if increasing , returns positive when decreasing
            return o.area - this.area;
        }
    }

    public int maxBoxStackHeight(Box arr[]) {
        int answer = 0;

        //Create all rotation of boxes such that length is greater than width
        Box newBox[] = new Box[3 * arr.length];
        generateBoxRotation(arr, newBox);

        //Generate all the base areas of the box
        for (int i = 0; i < newBox.length; i++) {
            newBox[i].area = newBox[i].l * newBox[i].w;
        }

        //Sort boxes with their areas decreasing
        Arrays.sort(newBox);

        //Apply longest increasing sum of heights subsequence on sorted areas
        int height[] = new int[newBox.length];
        int resultFrom[] = new int[height.length];
        for (int i = 0; i < height.length; i++) {
            height[i] = newBox[i].h;
            resultFrom[i] = i;
        }

        for (int i = 1; i < height.length; i++) {
            for (int j = 0; j < i; j++) {
                if (newBox[i].l < newBox[j].l && newBox[i].w < newBox[j].w) {
                    height[i] = Math.max(height[i], height[j] + newBox[i].h);
                    if (height[i] == height[j] + newBox[i].h)
                        resultFrom[i] = j;
                }
            }
            answer = Math.max(height[i], answer);
        }


        return answer;
    }

    public void generateBoxRotation(Box arr[], Box newBox[]) {

        //Initialize the boxes
        for (int i = 0; i < newBox.length; i++) {
            newBox[i] = new Box(0, 0, 0);
        }

        //Rotation such that length > Width
        for (int i = 0; i < arr.length; i++) {
            //1st Rotation
            newBox[3 * i].h = arr[i].h;
            newBox[3 * i].l = Math.max(arr[i].w, arr[i].l);
            newBox[3 * i].w = Math.min(arr[i].w, arr[i].l);

            //2nd Rotation
            newBox[3 * i + 1].h = arr[i].w;
            newBox[3 * i + 1].l = Math.max(arr[i].h, arr[i].l);
            newBox[3 * i + 1].w = Math.min(arr[i].h, arr[i].l);

            //3rd Rotation
            newBox[3 * i + 2].h = arr[i].l;
            newBox[3 * i + 2].l = Math.max(arr[i].h, arr[i].w);
            newBox[3 * i + 2].w = Math.min(arr[i].h, arr[i].w);
        }
    }

    public static void main(String args[]) {
        BoxStacking boxStacking = new BoxStacking();
        Box[] arr = new Box[4];
        arr[0] = new Box(4, 6, 7);
        arr[1] = new Box(1, 2, 3);
        arr[2] = new Box(4, 5, 6);
        arr[3] = new Box(10, 12, 32);
        System.out.print(boxStacking.maxBoxStackHeight(arr));

    }
}
package DynamicProgramming;

public class MinEditDistance {
    /*
     *@Author : Sahil
     * Date : 05-Apr-2018
     *
     * Given two strings str1 and str2 and below operations that can performed on str1. Find minimum number
     * of edits (operations) required to convert ‘str1’ into ‘str2’.
     * Insert
     * Remove
     * Replace
     *
     * Input:   str1 = "geek", str2 = "gesek"
     * Output:  1
     * We can convert str1 into str2 by inserting a 's'.
     *
     * Input:   str1 = "cat", str2 = "cut"
     * Output:  1
     * We can convert str1 into str2 by replacing 'a' with 'u'.
     *
     * Input:   str1 = "sunday", str2 = "saturday"
     * Output:  3
     *
     * References :
     * https://web.stanford.edu/class/cs124/lec/med.pdf
     * https://www.youtube.com/watch?v=We3YDTzNXEk
     * https://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
     *
     * Solution :
     * 1. Column contains -> String that should be changed
     * 2. Row contains -> String that is target string
     * 3. put minimum of  up,left,and diagonal left +1 to current position
     */
    int minDistance(String transform, String target) {
        int col = transform.length() + 1;
        int row = target.length() + 1;

        int T[][] = new int[row][col];

        //Intialize row and column with 0,1,2, upto length
        for (int i = 0; i < row; i++) {
            T[i][0] = i;
        }
        for (int j = 0; j < col; j++) {
            T[0][j] = j;
        }


        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (target.charAt(i-1) == transform.charAt(j-1)) {
                    T[i][j] = T[i - 1][j - 1];
                } else {
                    T[i][j] = 1 + Math.min(T[i][j - 1], Math.min(T[i - 1][j], T[i - 1][j - 1]));
                }
            }
        }
        return T[row-1][col-1];
    }


    public static void main(String args[]) {
        MinEditDistance minDistance = new MinEditDistance();
        String transform = "sunday";
        String target = "saturday";
        System.out.print(minDistance.minDistance(transform,target));
    }
}
package DynamicProgramming;

public class OptimalGameEndArray {
    /*
     *@Author : Sahil
     * Date : 04 April 2018
     *
     * Given an array of integers, there are 2 players, you and other player.
     * you have to collect maximum sum of integers. you can only select integer from either end of an array
     *
     * Solution :
     * 1 traverse digonally ->
     *          when you traverse diagonally, you check if only item 0,0 ..1,1 ... n,n exist
     *          then you check if item 1,2....2.3....3,4.....n-1,n exist and so on till 0,n
     *      How to traverse :
     *      for(int i=0; i<n ;i++){
     *          for(int l=0; j=0 ;j<=i;j++ ; l++){
     *              {
     *                 print T[l][j]
     *              }
     *          }
     *
     *
     * References :
     * https://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game/
     * https://www.youtube.com/watch?v=WxpIHvsu1RI
     */

    class pair {
        int first;
        int second;
    }

    int optimalValue(int arr[]) {
        int row = arr.length;
        int col = arr.length;

        pair T[][] = new pair[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                T[i][j] = new pair();
            }
        }

        //Intialize diagonal elements
        for (int i = 0; i < arr.length; i++) {
            T[i][i].first = arr[i];
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i, l = 0; j < arr.length; j++, l++) {
                T[l][j].first = Math.max((arr[l] + T[l + 1][j].second), (arr[j] + T[l][j - 1].second));
                if (T[l][j].first == arr[l] + T[l + 1][j].second) {
                    T[l][j].second = T[l + 1][j].first;
                } else {
                    T[l][j].second = T[l][j - 1].first;
                }
            }
        }

        return T[0][col - 1].first;
    }


    public static void main(String args[]) {
        OptimalGameEndArray optimalgame = new OptimalGameEndArray();
        int arr[] = {5, 3, 7, 10};
        System.out.print("\n" + optimalgame.optimalValue(arr));
    }
}
