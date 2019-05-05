package Trees;

public class UniqueBST1 {
    /*
     *@Author : Sahil
     * Date : 05- May-2019
     *
     * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
     *
     * Example:
     *
     * Input: 3
     * Output: 5
     * Explanation:
     * Given n = 3, there are a total of 5 unique BST's:
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     *
     *
     * Solution :
     *
     * 1. Let G(n): the number of unique BST for a sequence of length n.
     * 2. G(1) = 1 , when only one node is present
     * 3. G(0)= 1
     * 4. Lets assume , F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the root of BST
     * 5. For example, F(3, 7): the number of unique BST tree with number 3 as its root. To construct an unique BST out
     *  of the entire sequence [1, 2, 3, 4, 5, 6, 7] with 3 as the root, which is to say, we need to construct an unique
     *  BST out of its left subsequence [1, 2] and another BST out of the right subsequence [4, 5, 6, 7], and then
     *  combine them together (i.e. cartesian product). The tricky part is that we could consider the number of
     *  unique BST out of sequence [1,2] as G(2), and the number of of unique BST out of sequence [4, 5, 6, 7]
     *  as G(4). Therefore, F(3,7) = G(2) * G(4).
     *
     * 6. F(i, n) = G(i-1) * G(n-i)	1 <= i <= n
     *
     * 7. we can see that the total number of unique BST G(n), is the sum of BST F(i) using each number i as a root.
     * i.e.
     *
     *   G(n) = F(1, n) + F(2, n) + ... + F(n, n).
     *
     *   References : https://leetcode.com/problems/unique-binary-search-trees/
     *
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = G[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    public static void main(String[] args) {
        UniqueBST1 uniqueBST1 = new UniqueBST1();
        System.out.println(uniqueBST1.numTrees(5));

    }
}
