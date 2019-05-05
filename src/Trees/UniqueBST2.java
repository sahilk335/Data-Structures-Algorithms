package Trees;

import java.util.ArrayList;
import java.util.List;

public class UniqueBST2 {
    /*
     *@Author : Sahil
     * Date : 05 May 2019
     *
     * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
     *
     * Example:
     *
     * Input: 3
     * Output:
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     * Explanation:
     * The above output corresponds to the 5 unique BST's shown below:
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     *
     *
     * References : https://leetcode.com/problems/unique-binary-search-trees-ii/
     *
     * Solution :
     *start by noting that 1..n is the in-order traversal for any BST with nodes 1 to n. So if I pick i-th node as my
     * root, the left subtree will contain elements 1 to (i-1), and the right subtree will contain elements (i+1) to n.
     * I use recursive calls to get back all possible trees for left and right subtrees and combine them in all
     * possible ways with the root.
     */

    public List<Node> generateTrees(int n) {

        return genTrees(1, n);
    }

    public List<Node> genTrees(int start, int end) {

        List<Node> list = new ArrayList<Node>();

        if (start > end) {
            list.add(null);
            return list;
        }

        if (start == end) {
            list.add(new Node(start));
            return list;
        }

        List<Node> left, right;
        for (int i = start; i <= end; i++) {

            left = genTrees(start, i - 1);
            right = genTrees(i + 1, end);

            for (Node lnode : left) {
                for (Node rnode : right) {
                    Node root = new Node(i);
                    root.left = lnode;
                    root.right = rnode;
                    list.add(root);
                }
            }

        }

        return list;
    }

    public static void main(String[] args) {
        UniqueBST2 uniqueBST2 = new UniqueBST2();
    }
}
