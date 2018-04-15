package Array;

public class MajorityElement {
    /*
     *@Author : Sahil
     * Date : 09 Apr 2018
     *
     *
     * Write a function which takes an array and prints the majority element (if it exists),
     *  otherwise prints “No Majority Element”.
     *  A majority element in an array A[] of size n is an element that
     *  appears more than n/2 times (and hence there is at most one such element).
     *
     * Examples :
     *
     * Input : {3, 3, 4, 2, 4, 4, 2, 4, 4}
     * Output : 4

     * Input : {3, 3, 4, 2, 4, 4, 2, 4}
     * Output : No Majority Element
     *
     * Solution :
     *
     * Using Moore's Voting Algorithm :
     *
     * It is 2 step algorithm
     *
     * Step 1 :
     * findCandidate(a[], size)
     * 1.  Initialize index and count of majority element
     *      maj_index = 0, count = 1
     * 2.  Loop for i = 1 to size – 1
     *     (a) If a[maj_index] == a[i]
     *           count++
     *     (b) Else
     *        count--;
     *    (c) If count == 0
     *        maj_index = i;
     *        count = 1
     * 3.  Return a[maj_index]
     *
     * Step 2 :
     *
     * In the second phase we need to check if the candidate is really a majority element.
     * Second phase is simple and can be easily done in O(n).
     * We just need to check if count of the candidate element is greater than n/2.
     *
     */

    public Integer majorityElement(int[] arr) {

        int count = 0;
        Integer candidate = null;

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        count = 0;
        //Check again, if it is really greater than n/2
        for (int num : arr) {
            count += (num == candidate) ? 1 : 0;
        }

        return count > arr.length / 2 ? candidate : null;
    }

    public static void main(String args[]) {
        MajorityElement majority = new MajorityElement();
        int arr[] = {3, 3, 4, 2, 4, 4, 2, 4, 4};
        System.out.print(majority.majorityElement(arr));

    }
}
