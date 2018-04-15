package Array;

import java.util.ArrayList;

public class MergeKSortedArray {
    /*
     *@Author : Sahil
     * Date : 10 Apr 2018
     *
     *
     */

    public ArrayList<Integer> mergeKSortedArray(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        ArrayList<Integer> resList = new ArrayList<>();
        MinHeap minHeap = new MinHeap();

        for (int i = 0; i < arr.length; i++) {
            minHeap.add(arr[i][0], i, 1); //Add number, its arrayIndex , nextElement
        }

        for (int i = 0; i < row * col; i++) {
            //Extract min element and put it inside result List
            resList.add(minHeap.minHeapElement().val);


            //Replace the next min from same array and apply heapify
            int nRow = minHeap.minHeapElement().arrIdx;
            int nCol = minHeap.minHeapElement().nextIdx;
            minHeap.deleteMin();
            if (nCol < col)
                minHeap.add(arr[nRow][nCol], nRow, nCol + 1);
            else
                minHeap.add(Integer.MAX_VALUE, nRow, nCol + 1);

        }


        System.out.print(resList);

        return resList;
    }

    public static void main(String args[]) {
        MergeKSortedArray merger = new MergeKSortedArray();
        int arr[][] = {{2, 6, 12, 34},
                {1, 9, 20, 1000},
                {23, 34, 90, 2000}};
        merger.mergeKSortedArray(arr);
    }
}
