package Array;

import java.util.PriorityQueue;

public class MedianInRunningStream {
    /*
     *@Author : Sahil
     * Date : 27 Apr 2018
     *
     * Given that integers are read from a data stream.
     * Find median of elements read so for in efficient way.
     * For simplicity assume there are no duplicates. For example, let us consider the stream 5, 15, 1, 3 …
     *
     * After reading 1st element of stream - 5 -> median - 5
     * After reading 2nd element of stream - 5, 15 -> median - 10
     * After reading 3rd element of stream - 5, 15, 1 -> median - 5
     * After reading 4th element of stream - 5, 15, 1, 3 -> median - 4, so on...
     *
     * References :
     * https://www.geeksforgeeks.org/median-of-stream-of-integers-running-integers/
     * https://www.youtube.com/watch?v=VmogG01IjYc&t=430s
     *
     *
     * Solution :
     * 1. Create 2 Heaps, minHeap & maxHeap (MaxHeaps contains all the small elements on left side of median
     *              & MinHeap will contain all the bigger elements on right side of the median)
     * 2. Add number in maxHeap if currNum is smaller than maxHeap top element
     * 3.Rebalance the heap size if difference is greater than equal to 2
     * 4.Extract medians based on the size of the heaps
     */

    public Double median(int[] arr) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y - x);

        Double medians[] = new Double[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int number = arr[i];
            addNumber(number, minHeap, maxHeap);
            rebalance(minHeap, maxHeap);
            medians[i] = getMedians(minHeap, maxHeap);
        }

        for (int i = 0; i < medians.length; i++) {
            System.out.print(medians[i] + " ");
        }
        return medians[arr.length - 1];
    }

    public void addNumber(int number, PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        if (maxHeap.size() == 0 || number < maxHeap.peek()) {
            maxHeap.add(number);
        } else {
            minHeap.add(number);
        }
    }

    public double getMedians(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        PriorityQueue<Integer> biggerHeap = minHeap.size() > maxHeap.size() ? minHeap : maxHeap;
        PriorityQueue<Integer> smallerHeap = minHeap.size() > maxHeap.size() ? maxHeap : minHeap;

        if (biggerHeap.size() == smallerHeap.size()) {
            return ((double) biggerHeap.peek() + smallerHeap.peek()) / 2;
        } else {
            return biggerHeap.peek();
        }
    }

    public void rebalance(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap) {
        PriorityQueue<Integer> biggerHeap = minHeap.size() > maxHeap.size() ? minHeap : maxHeap;
        PriorityQueue<Integer> smallerHeap = minHeap.size() > maxHeap.size() ? maxHeap : minHeap;

        if (biggerHeap.size() - smallerHeap.size() > 1) {
            smallerHeap.add(biggerHeap.poll());
        }
    }

    public static void main(String args[]) {
        MedianInRunningStream median = new MedianInRunningStream();
        int[] arr = {5, 15, 1, 3};
        median.median(arr);
    }
}
