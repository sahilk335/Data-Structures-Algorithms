package DynamicProgramming;

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
