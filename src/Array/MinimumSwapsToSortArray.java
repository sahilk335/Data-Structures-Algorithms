package Array;

public class MinimumSwapsToSortArray {
    /*
     * Minimum number of swaps required to sort an array
     * Given an array of n distinct elements, find the minimum number of swaps required to sort the array.
     *
     * Examples:
     *
     * Input : {4, 3, 2, 1}
     * Output : 2
     * Explanation : Swap index 0 with 3 and 1 with 2 to
     *               form the sorted array {1, 2, 3, 4}.
     *
     * Input : {1, 5, 4, 3, 2}
     * Output : 2
     *
     * Refrences :
     * https://www.geeksforgeeks.org/minimum-number-swaps-required-sort-array/
     *
     * Solution :
     * This problem can be solved easily by observing the actual position of elements and their current position ,
     * the actual position of element in sorted array will be the a[cur]-1 (element-1), by tracking the actual position
     * of element if we come back to the current element then there exist a cycle , then count the size of that cycle ,
     * the number of swaps will be cycling size-1, do this for all the cycles and add them together.
     */

    int minimumSwaps(int[] arr) {
        int swaps = 0;
        boolean visited[] = new boolean[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int j = i;
            int cycleNode = 0;

            //this while loop will track one cycle
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j] - 1;
                cycleNode++;
            }

            if (cycleNode != 0) {
                swaps += cycleNode - 1;
            }
        }

        return swaps;
    }

    public static void main(String[] args) {
        MinimumSwapsToSortArray minimumSwapsToSortArray = new MinimumSwapsToSortArray();
        int[] arr = {4, 3, 2, 1};
        System.out.println("Minimum Swaps to Sort an array : " + minimumSwapsToSortArray.minimumSwaps(arr));
    }
}
