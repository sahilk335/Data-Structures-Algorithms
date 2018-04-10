package Array;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {
    /*
     *@Author : Sahil
     * Date : 10 Apr 2018
     *
     * Implement Min Heap
     *
     */

    List<HeapElement> heap = new ArrayList<>();

    class HeapElement {
        int val;
        int nextIdx;
        int arrIdx;

        HeapElement(int val, int arrIdx, int nextIdx) {
            this.val = val;
            this.nextIdx = nextIdx;
            this.arrIdx = arrIdx;
        }
    }

    public void add(int val, int arrIdx, int nextIdx) {
        HeapElement heapElement = new HeapElement(val, arrIdx, nextIdx);
        heap.add(heapElement);
        int currIdx = heap.size() - 1;
        int parentIdx = (currIdx - 1) / 2;

        while (parentIdx >= 0) {
            if (heap.get(parentIdx).val > heap.get(currIdx).val) {
                swap(heap.get(currIdx), heap.get(parentIdx));
                currIdx = parentIdx;
                parentIdx = (currIdx - 1) / 2;
            } else
                break;
        }
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }


    public int deleteMin() {
        int size = heap.size() - 1;

        int min = heap.get(0).val;
        heap.get(0).val = heap.get(size).val;
        heap.get(0).nextIdx = heap.get(size).nextIdx;
        heap.get(0).arrIdx = heap.get(size).arrIdx;

        heap.remove(size);

        size = size - 1;

        int currIdx = 0;

        while (true) {
            int left = 2 * currIdx + 1;
            int right = 2 * currIdx + 2;

            if (left > size)
                break;

            if (right >= size) {
                right = left;
            }

            int smallerIdx = heap.get(left).val <= heap.get(right).val ? left : right;

            if (heap.get(smallerIdx).val < heap.get(currIdx).val) {
                swap(heap.get(smallerIdx), heap.get(currIdx));
                currIdx = smallerIdx;
            } else {
                break;
            }
        }
        return min;
    }

    public int minValue() {
        return heap.get(0).val;
    }

    public HeapElement minHeapElement() {
        return heap.get(0);
    }


    public void swap(HeapElement h1, HeapElement h2) {
        int tempVal, tempIdx, tempArrIdx;
        tempVal = h1.val;
        tempArrIdx = h1.arrIdx;
        tempIdx = h1.nextIdx;
        h1.val = h2.val;
        h1.nextIdx = h2.nextIdx;
        h1.arrIdx = h2.arrIdx;
        h2.val = tempVal;
        h2.nextIdx = tempIdx;
        h2.arrIdx = tempArrIdx;
    }

    public static void main(String args[]) {
        MinHeap minHeap = new MinHeap();
        minHeap.add(-1, 0, 9);
        minHeap.add(2, 1, 7);
        minHeap.add(9, 2, 6);


        //System.out.print(minHeap.minValue());

        // minHeap.replaceMinNode(6, 0, 3);


        minHeap.deleteMin();
        System.out.println("print :" + minHeap.minHeapElement().val + "\n");
        for (int i = 0; i < minHeap.heap.size(); i++)
            System.out.print(minHeap.heap.get(i).val + " ");
    }


}
