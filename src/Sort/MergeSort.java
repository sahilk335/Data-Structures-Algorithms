package Sort;

public class MergeSort {
    /*
     *@Author : Sahil
     * Date : 22 Feb 2018
     * References : https://www.programiz.com/dsa/merge-sort
     *
     */

    public void mergeSort(int arr[], int startIdx, int endIdx) {
        if (startIdx < endIdx) {
            int mid = (startIdx + endIdx) / 2;
            mergeSort(arr, startIdx, mid);
            mergeSort(arr, mid + 1, endIdx);
            merge(arr, startIdx, mid , endIdx);
        }
    }

    public void merge(int[] arr, int startIdx1, int startIdx2, int length) {


        int lenghtFirstArray = startIdx2 - startIdx1 + 1;
        int lengthSecondArray = length - startIdx2;

        int firstArr[] = new int[lenghtFirstArray];
        int secondArr[] = new int[lengthSecondArray];

        for (int i = 0; i < lenghtFirstArray; i++) {
            firstArr[i] = arr[startIdx1+i];
        }
        for (int j = 0; j < lengthSecondArray; j++) {
            secondArr[j] = arr[startIdx2+j+1];
        }

        int startPointer1 = 0;
        int startPointer2 = 0;
        int startMergePointer = startIdx1;

        while (startPointer1 < lenghtFirstArray && startPointer2 < lengthSecondArray) {
            if (firstArr[startPointer1] <= secondArr[startPointer2]) {
                arr[startMergePointer] = firstArr[startPointer1];
                startPointer1++;
                startMergePointer++;
            } else {
                arr[startMergePointer] = secondArr[startPointer2];
                startPointer2++;
                startMergePointer++;
            }
        }
        while (startPointer1 < lenghtFirstArray) {
            arr[startMergePointer] = firstArr[startPointer1];
            startPointer1++;
            startMergePointer++;
        }
        while (startPointer2 < lengthSecondArray) {
            arr[startMergePointer] = secondArr[startPointer2];
            startPointer2++;
            startMergePointer++;
        }
    }

    public static void main(String args[]) {
        int arr[] = {9, 8, 6, 4, 5, 1, 2, 3, 7};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(arr, 0, arr.length-1 );
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
