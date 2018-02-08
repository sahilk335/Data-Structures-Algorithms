package Array;

public class KthSmallestElementInSortedMatrix {
    /*
     * @Author : Sahil
     * Date 7 Feb 2018
     *
     * References : https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/#
     * Solution : Submitted on leetcode
     * Approach :
     * The idea is to use min heap. Following are detailed step.
     * 1) Build a min heap of elements from first row. A heap entry also stores row number and column number.
     * 2) Do following k times.
     *      …a) Get minimum element (or root) from min heap.
     *      …b) Find row number and column number of the minimum element.
     *      …c) Replace root with the next element from same column and min-heapify the root.
     * 3) Return the last extracted root.
     *
     */

    class HeapElement {
        int x;
        int y;
        int data;

        HeapElement() {
        }
    }

    void buildHeap(HeapElement heapArr[]) {
        int size = heapArr.length;
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(heapArr, size, i);
        }
    }

    int buildHeapAndFind(int arr[][], int k) {

        //k=1 means smallest element which is arr[0][0]
        if (k == 1)
            return arr[0][0];

        int solution = 0;
        int size = arr.length;

        //Create heap array of first row of matrix
        HeapElement heapArr[] = new HeapElement[size];
        for (int j = 0; j < size; j++) {
            heapArr[j] = new HeapElement();
            heapArr[j].data = arr[0][j];
            heapArr[j].x = 0;
            heapArr[j].y = j;
        }

        //Call BuildHeap
        buildHeap(heapArr);

        for (int i = 1; i <= k; i++) {
            solution = heapArr[0].data;
            int nextMinimumFromlastMinimumColumn = 0;

            //Replace next down element of the same column which is not yet visited
            if (heapArr[0].x + 1 < size) {
                nextMinimumFromlastMinimumColumn = arr[heapArr[0].x + 1][heapArr[0].y];
            } else {
                nextMinimumFromlastMinimumColumn = Integer.MAX_VALUE;
            }
            /*
             * Since EVERY ROW AND EVERY COLUMN IS SORTED IN ASCENDING ORDER.
             *  - At position (i,j), both the element's position (i+1,j) and (i,j+1) are greater that this.
             *  - But, we don't know, whether (i+1,j) is greater/lesser than (i,j+1).
             *  -So, At first, we consider the element at (i,j+1). And come again to (i+1,j) with the help of HEAP.
             */
            heapArr[0].data = nextMinimumFromlastMinimumColumn;
            heapArr[0].x = heapArr[0].x + 1;
            heapArr[0].y = heapArr[0].y;

            //Call heapify on 1st element only, because everytime this element will change
            heapify(heapArr, size, 0);
        }

        return solution;
    }

    public void heapify(HeapElement heapArr[], int size, int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < size && heapArr[left].data <= heapArr[smallest].data) {
            smallest = left;
        }
        if (right < size && heapArr[right].data <= heapArr[smallest].data) {
            smallest = right;
        }
        if (smallest != i) {
            swap(heapArr, smallest, i);
            heapify(heapArr, size, smallest);
        }
    }

    public void swap(HeapElement heapArr[], int i, int j) {
        HeapElement temp = heapArr[i];
        heapArr[i] = heapArr[j];
        heapArr[j] = temp;
    }

    public static void main(String args[]) {
        int arr[][] = {
                {1, 2},
                {1, 3}
        };
        int k = 2;
        KthSmallestElementInSortedMatrix element = new KthSmallestElementInSortedMatrix();
        System.out.print(element.buildHeapAndFind(arr, k));
    }
}
