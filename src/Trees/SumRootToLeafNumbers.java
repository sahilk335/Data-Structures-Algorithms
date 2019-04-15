package Trees;

public class SumRootToLeafNumbers {
    /*
     *@Author : Sahil
     * Date : 15 April 2019
     *
     * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
     *
     * An example is the root-to-leaf path 1->2->3 which represents the number 123.
     *
     * Find the total sum of all root-to-leaf numbers.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Input: [1,2,3]
     *     1
     *    / \
     *   2   3
     * Output: 25
     * Explanation:
     * The root-to-leaf path 1->2 represents the number 12.
     * The root-to-leaf path 1->3 represents the number 13.
     * Therefore, sum = 12 + 13 = 25.
     * Example 2:
     *
     * Input: [4,9,0,5,1]
     *     4
     *    / \
     *   9   0
     * / \
     * 5   1
     * Output: 1026
     * Explanation:
     * The root-to-leaf path 4->9->5 represents the number 495.
     * The root-to-leaf path 4->9->1 represents the number 491.
     * The root-to-leaf path 4->0 represents the number 40.
     * Therefore, sum = 495 + 491 + 40 = 1026.
     *
     *
     * References :
     * https://leetcode.com/problems/sum-root-to-leaf-numbers/
     *
     * Solution
     * 1. Take a global total value
     * 2. Keep on adding number in preOrder.
     * sum= sum*10 + root.data
     * 3. when reach leaf node add to total
     */
    int total;

    public int sumNumbers(Node root) {
        total = 0;
        helper(root, 0);
        return total;
    }

    void helper(Node root, int sum) {
        if (root == null) return;

        sum = sum * 10 + root.data;

        if (root.left == null && root.right == null) {
            String binarySum = Integer.toString(sum);       // these two steps required if tree is binary
            Integer actualSum = Integer.parseInt(binarySum, 2);
            total += actualSum;
            return;
        }

        helper(root.left, sum);
        helper(root.right, sum);
    }


    public static void main(String[] args) {
        SumRootToLeafNumbers sumRootToLeafNumbers = new SumRootToLeafNumbers();
        	/*

    1
   / \
  0   1
 / \
1   1

	 */

        BinaryTree.root = new Node(1);
        BinaryTree.root.left = new Node(0);
        BinaryTree.root.right = new Node(1);
        BinaryTree.root.left.left = new Node(1);
        BinaryTree.root.left.right = new Node(1);
        System.out.println(sumRootToLeafNumbers.sumNumbers(BinaryTree.root));

    }
}
