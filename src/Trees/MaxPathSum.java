package Trees;

public class MaxPathSum {
    /*
     * @Author : Sahil
     * Date: 6 Feb 2018
     *
     * Given a binary tree, find the maximum path sum.

     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
     *
     * For example:
     * Given the below binary tree,
     *
     *        1
     *       / \
     *     2   3
     * Return 6.
     *
     * Solution :
     *
     * For each node there can be four ways that the max path goes through the node:
     * 1. Node only
     * 2. Max path through Left Child + Node
     * 3. Max path through Right Child + Node
     * 4. Max path through Left Child + Node + Max path through Right Child
     *
     * References :
     * https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
     * https://www.geeksforgeeks.org/find-maximum-path-sum-in-a-binary-tree/
     */

    int maximum = Integer.MIN_VALUE;

    public int maxPathSum(Node root) {
        maxSumfromAllNodes(root);
        return maximum;
    }

    public int maxSumfromAllNodes(Node root) {
        //return 0 from leaf node
        if (root == null)
            return 0;

        int left = Math.max(0, maxSumfromAllNodes(root.left));
        int right = Math.max(0, maxSumfromAllNodes(root.right));
        //Note this step is important, the answer will always contains the root, the left and the right
        //Unless the left is completely negative, but it can never be because , we are checking in above statement
        //if is Math.max(0, maxSumfromAllNodes(root.left)); so, at minimum it can be 0
        maximum = Math.max(maximum, root.data + left + right);

        //With this statement it means the path can take either of left or right
        //So, whichever returns the greater value we chose that out of left or right
        return root.data + Math.max(left, right);
    }


    public static void main(String args[]) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.makeTree();
        MaxPathSum mps = new MaxPathSum();
        System.out.print(mps.maxPathSum(binaryTree.root));

    }
}
