package Array;


public class FirstMissingPositive {
    /*
     * Given an unsorted integer array, find the smallest missing positive integer.
     *
     * Example 1:
     *
     * Input: [1,2,0]
     * Output: 3
     * Example 2:
     *
     * Input: [3,4,-1,1]
     * Output: 2
     * Example 3:
     *
     * Input: [7,8,9,11,12]
     * Output: 1
     *
     *
     *
     */
    public int firstMissingPositive(int[] nums) {

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] >= 0 && nums[i] < n && nums[nums[i]] != nums[i])
                swap(nums, i, nums[i]);
        }
        int k = 1;

        // Check from k=1 to see whether each index and value can be corresponding.
        while (k < n && nums[k] == k)
            k++;

        // If it breaks because of empty array or reaching the end. K must be the first missing number.
        if (n == 0 || k < n)
            return k;
        else if (nums[0] == n) {
            return n + 1;                   // if first index is  the length of array then length of array+1 is max number
        }
        return n;       //return length of array + 1
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
        int[] nums = {3, 4, -1, 1};
        System.out.println("First Missing Positive : " + firstMissingPositive.firstMissingPositive(nums));

    }
}
