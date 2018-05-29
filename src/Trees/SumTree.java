package Trees;

public class SumTree {
    /*
     *@Author : Sahil
     * Date : 29 May 2018
     *
     * Check if a given Binary Tree is SumTree
     *
     * Write a function that returns true if the given Binary Tree is SumTree else false. A SumTree is a Binary Tree
     * where the value of a node is equal to sum of the nodes present in its left subtree and right subtree.
     * An empty tree is SumTree and sum of an empty tree can be considered as 0.
     * A leaf node is also considered as SumTree.
     *
     * Following is an example of SumTree.
     *
     *           26
     *         /   \
     *       10     3
     *     /    \     \
     *   4      6      3
     *
     *
     * Solution :
     *
     * 2 rules to get the sum directly.
     *
     * 1) If the node is a leaf node then sum of subtree rooted with this node is equal to value of this node.
     * 2) If the node is not a leaf node then sum of subtree rooted with this node is twice the value of this node
     *
     * References :
     * https://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-sumtree/
     *
     */

    class Count {
        int size;
    }

    public boolean isSumTree(Node root) {
        Count count = new Count();
        return isSumTree(root, count);
    }

    private boolean isSumTree(Node root, Count count) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            count.size = root.data;
            return true;
        }
        Count leftCount = new Count();
        Count rightCount = new Count();
        boolean isLeft = isSumTree(root.left, leftCount);
        boolean isRight = isSumTree(root.right, rightCount);
        count.size = root.data + leftCount.size + rightCount.size;
        return isLeft && isRight && root.data == (leftCount.size + rightCount.size);
    }

    public static void main(String[] args) {

        SumTree sumTree = new SumTree();
               /*

	 			  26
	 			/   \
	 		   10    3
	 		  / \     \
	         4   6     3

	     */
        BinaryTree.root = new Node(26);
        BinaryTree.root.left = new Node(10);
        BinaryTree.root.left.left = new Node(4);
        BinaryTree.root.left.right = new Node(6);
        BinaryTree.root.right = new Node(3);
        BinaryTree.root.right.right = new Node(3);

        System.out.print("Is Sum Tree : " + sumTree.isSumTree(BinaryTree.root));

    }
}
