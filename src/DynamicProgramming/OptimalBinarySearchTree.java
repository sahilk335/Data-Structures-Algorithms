package DynamicProgramming;

public class OptimalBinarySearchTree {
    /*
     *@Author : Sahil
     *Date : 08 Apr 2018
     *
     * Given a sorted array keys[0.. n-1] of search keys and an array freq[0.. n-1] of frequency counts, where freq[i]
     * is the number of searches to keys[i].
     * Construct a binary search tree of all keys such that the total cost of all the searches is as small as possible.
     *Example 1
     * Input:  keys[] = {10, 12}, freq[] = {34, 50}
     * There can be following two possible BSTs
     *         10                       12
     *           \                     /
     *            12                 10
     *          I                     II
     * Frequency of searches of 10 and 12 are 34 and 50 respectively.
     * The cost of tree I is 34*1 + 50*2 = 134
     * The cost of tree II is 50*1 + 34*2 = 118
     *
     * Example 2
     * Input:  keys[] = {10, 12, 20}, freq[] = {34, 8, 50}
     * There can be following possible BSTs
     *     10                12                 20         10              20
     *       \             /    \              /             \            /
     *       12          10     20           12               20         10
     *         \                            /                 /           \
     *          20                        10                12             12
     *      I               II             III             IV             V
     * Among all possible BSTs, cost of the fifth BST is minimum.
     * Cost of the fifth BST is 1*50 + 2*34 + 3*8 = 142
     *
     * References :
     * https://www.geeksforgeeks.org/dynamic-programming-set-24-optimal-binary-search-tree/
     * https://www.youtube.com/watch?v=hgA4xxlVvfQ
     *
     * Solution Travere Diagonally
     *
     */

    public int minCostBST(int input[], int fre[]) {
        int T[][] = new int[input.length][input.length];
        //Initialize with INF
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                T[i][j] = Integer.MAX_VALUE;
            }
        }

        //Initialize diagonal to frequency array as if only one element minCost is itself
        for (int i = 0; i < input.length; i++) {
            T[i][i] = fre[i];
        }

        for (int i = 1; i < input.length; i++) {
            for (int l = 0, j = i; j < input.length; j++, l++) {
                // System.out.print("("+l+","+j+")  ");
                int sum = getSum(fre, l, j);

                for (int k = l; k <= j; k++) {
                    if (k - 1 < l) {        //if root is the first element
                        T[l][j] = Math.min(T[l][j], sum + T[k + 1][j]);
                    } else if (k + 1 > j) { //if root is the last element
                        T[l][j] = Math.min(T[l][j], sum + T[l][k - 1]);
                    } else {            //if root is the middle element
                        T[l][j] = Math.min(T[l][j], sum + T[l][k - 1] + T[k + 1][j]);
                    }
                }
            }
            //System.out.print("\n");
        }

        return T[0][input.length - 1];
    }

    public int getSum(int fre[], int l, int j) {
        int sum = 0;
        for (int i = l; i <= j; i++) {
            sum += fre[i];
        }
        return sum;
    }

    public static void main(String args[]) {
        OptimalBinarySearchTree optimalBST = new OptimalBinarySearchTree();
        int input[] = {10, 12, 20};
        int freq[] = {34, 8, 50};
        System.out.print(optimalBST.minCostBST(input, freq));

    }
}
