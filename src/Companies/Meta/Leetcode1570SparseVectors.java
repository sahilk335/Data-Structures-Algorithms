import java.util.*;

/**
 * 1570. Dot Product of Two Sparse Vectors
 * Solved
 * Medium
 * Topics
 * conpanies icon
 * Companies
 * Hint
 * Given two sparse vectors, compute their dot product.
 * 
 * Implement class SparseVector:
 * 
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector
 * and vec
 * A sparse vector is a vector that has mostly zero values, you should store the
 * sparse vector efficiently and compute the dot product between two
 * SparseVector.
 * 
 * Follow up: What if only one of the vectors is sparse?
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 * Example 2:
 * 
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 * Example 3:
 * 
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 * 
 * 
 * Constraints:
 * 
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 100
 * 
 */

 /**
 * SPARSE VECTOR STORAGE ALGORITHM
 * 
 * Instead of storing all elements (including zeros), we only store non-zero elements
 * as (index, value) pairs. This saves memory for sparse vectors.
 * 
 * Example: nums = [1, 0, 0, 2, 3]
 * 
 * Traditional storage:     Sparse storage:
 * Index: 0 1 2 3 4        Index: 0 1 2 3 4
 * Value: 1 0 0 2 3        Value: 1 0 0 2 3
 * 
 * Sparse representation:
 * nonZero = [[0,1], [3,2], [4,3]]
 *           [i,v]  [i,v]  [i,v]
 * 
 * Memory: O(n) → O(k) where k = number of non-zero elements
 */
class Leetcode1570SparseVectors {
    private List<int[]> nonZero; // each element is [index, value]

    /**
     * CONSTRUCTOR: Build sparse representation
     * 
     * Algorithm:
     * 1. Initialize empty list for non-zero elements
     * 2. Scan through input array
     * 3. For each non-zero element, store [index, value] pair
     * 
     * Example: nums = [1, 0, 0, 2, 3]
     * 
     * Step 0: nonZero = []
     * Step 1: nums[0]=1 ≠ 0 → add [0,1] → nonZero = [[0,1]]
     * Step 2: nums[1]=0 → skip
     * Step 3: nums[2]=0 → skip  
     * Step 4: nums[3]=2 ≠ 0 → add [3,2] → nonZero = [[0,1], [3,2]]
     * Step 5: nums[4]=3 ≠ 0 → add [4,3] → nonZero = [[0,1], [3,2], [4,3]]
     * 
     * Final: nonZero = [[0,1], [3,2], [4,3]]
     */
    public Leetcode1570SparseVectors(int[] nums) {
        nonZero = new ArrayList<>();          // allocate list
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {               // skip zeros
                nonZero.add(new int[]{i, nums[i]}); // store index and value
            }
        }
    }

    /**
     * DOT PRODUCT ALGORITHM: Two-Pointer Merge Technique
     * 
     * The dot product of two vectors is: Σ(a[i] * b[i]) for all i
     * 
     * Since we only store non-zero elements, we use a two-pointer approach
     * similar to merging two sorted arrays.
     * 
     * Example: 
     * Vector 1: [1, 0, 0, 2, 3] → sparse: [[0,1], [3,2], [4,3]]
     * Vector 2: [0, 3, 0, 4, 0] → sparse: [[1,3], [3,4]]
     * 
     * Two-pointer traversal:
     * 
     * Initial state:
     * list1: [[0,1], [3,2], [4,3]]    list2: [[1,3], [3,4]]
     *         ↑i=0                      ↑j=0
     * 
     * Step 1: idx1=0, idx2=1 → idx1 < idx2 → advance i
     * list1: [[0,1], [3,2], [4,3]]    list2: [[1,3], [3,4]]
     *              ↑i=1                 ↑j=0
     * 
     * Step 2: idx1=3, idx2=1 → idx1 > idx2 → advance j  
     * list1: [[0,1], [3,2], [4,3]]    list2: [[1,3], [3,4]]
     *              ↑i=1                      ↑j=1
     * 
     * Step 3: idx1=3, idx2=3 → idx1 == idx2 → MATCH!
     * result += 2 * 4 = 8, advance both pointers
     * list1: [[0,1], [3,2], [4,3]]    list2: [[1,3], [3,4]]
     *                    ↑i=2                    ↑j=2
     * 
     * Step 4: j >= list2.size() → exit loop
     * 
     * Final result: 8
     * 
     * Time Complexity: O(k1 + k2) where k1, k2 are non-zero counts
     * Space Complexity: O(1) excluding input storage
     */
    public int dotProduct(Leetcode1570SparseVectors vec) {
        int result = 0;                       // accumulator for dot product
        List<int[]> list1 = this.nonZero;     // this vector's non-zero list
        List<int[]> list2 = vec.nonZero;      // other vector's non-zero list
        int i = 0, j = 0;                     // two pointers

        // merge-like two-pointer traversal
        while (i < list1.size() && j < list2.size()) {
            int idx1 = list1.get(i)[0], val1 = list1.get(i)[1];
            int idx2 = list2.get(j)[0], val2 = list2.get(j)[1];

            if (idx1 == idx2) {               // matching index → contribute
                result += val1 * val2;        // multiply and add
                i++;                           // advance both pointers
                j++;
            } else if (idx1 < idx2) {         // idx1 too small → skip it
                i++;
            } else {                          // idx2 too small → skip it
                j++;
            }
        }
        return result;                       // final result
    }
}

/**
 * COMPLETE ALGORITHM VISUALIZATION
 * 
 * Example: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * 
 * Step 1: Build Sparse Vectors
 * ┌─────────────────────────────────────────────────────────────┐
 * │ Vector 1: [1,0,0,2,3] → [[0,1], [3,2], [4,3]]              │
 * │ Vector 2: [0,3,0,4,0] → [[1,3], [3,4]]                     │
 * └─────────────────────────────────────────────────────────────┘
 * 
 * Step 2: Two-Pointer Dot Product
 * ┌─────────────────────────────────────────────────────────────┐
 * │ Initial: i=0, j=0, result=0                                 │
 * │                                                             │
 * │ Iteration 1: idx1=0, idx2=1 → 0 < 1 → advance i            │
 * │   [[0,1], [3,2], [4,3]]  [[1,3], [3,4]]                    │
 * │    ↑i=0                    ↑j=0                             │
 * │                                                             │
 * │ Iteration 2: idx1=3, idx2=1 → 3 > 1 → advance j           │
 * │   [[0,1], [3,2], [4,3]]  [[1,3], [3,4]]                    │
 * │         ↑i=1              ↑j=0                              │
 * │                                                             │
 * │ Iteration 3: idx1=3, idx2=3 → 3 == 3 → MATCH!              │
 * │   result += 2 * 4 = 8, advance both                        │
 * │   [[0,1], [3,2], [4,3]]  [[1,3], [3,4]]                    │
 * │               ↑i=2              ↑j=2                        │
 * │                                                             │
 * │ Iteration 4: j >= list2.size() → exit                      │
 * │                                                             │
 * │ Final result: 8                                             │
 * └─────────────────────────────────────────────────────────────┘
 * 
 * Key Benefits:
 * • Memory: O(n) → O(k) where k = non-zero elements
 * • Time: O(n) → O(k1 + k2) for dot product
 * • Efficient for sparse vectors (mostly zeros)
 */