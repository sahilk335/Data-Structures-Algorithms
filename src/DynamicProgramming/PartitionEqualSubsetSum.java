package DynamicProgramming;

public class PartitionEqualSubsetSum {
    /*
     *@Author : Sahil
     * Date : 3 Mar 2019
     *
     * Given a non-empty array containing only positive integers, find if the array can be partitioned into two
     * subsets such that the sum of elements in both subsets is equal.
     *
     * Note:
     * Each of the array element will not exceed 100.
     * The array size will not exceed 200.
     *
     *
     * Solution :
     *
     * 1. First check if sum of the array numbers is even or odd. Logically, it is odd, then it can not be partitioned
     *      into 2 equal sum subsets
     * 2. Secondly, if we partition the equal subsets, then each subset will have total sum = sum/2
     *
     * 3. Apply SubsetSum problem to step 2, that will return the answer.
     *
     *
     * References : https://leetcode.com/problems/partition-equal-subset-sum/
     *
     *
     */

    public boolean partitionSubsetSum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }

        //If the sum is odd, then partition with equal sum subset is not possible
        if (sum % 2 != 0)
            return false;


        sum = sum / 2;
        // Second step is to apply sum subset problem on sum =sum/2
        SubsetSum subsetSum = new SubsetSum();
        return subsetSum.isSubsetSum(arr, sum);

    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum obj = new PartitionEqualSubsetSum();
        int[] arr = {11, 5, 11, 5};
        System.out.println(obj.partitionSubsetSum(arr));
    }
}
