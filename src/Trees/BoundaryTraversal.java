package Trees;

import java.util.LinkedList;
import java.util.List;

public class BoundaryTraversal {
    /*
     *@Author : Sahil Khurana
     * Date : 19 June 2019
     *
     *
     * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root.
     * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
     *
     * Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path
     * from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself
     * is left boundary or right boundary. Note this definition only applies to the input binary tree, and not
     * applies to any subtrees.
     *
     * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree
     * if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.
     *
     * The right-most node is also defined by the same way with left and right exchanged.
     *
     * Example 1
     * Input:
     *   1
     *    \
     *     2
     *    / \
     *   3   4
     *
     * Ouput:
     * [1, 3, 4, 2]
     *
     * Explanation:
     * The root doesn't have left subtree, so the root itself is left boundary.
     * The leaves are node 3 and 4.
     * The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
     * So order them in anti-clockwise without duplicates and we have [1,3,4,2].
     * Example 2
     * Input:
     *     ____1_____
     *    /          \
     *   2            3
     *  / \          /
     * 4   5        6
     *    / \      / \
     *   7   8    9  10
     *
     * Ouput:
     * [1,2,4,7,8,9,10,6,3]
     *
     * Explanation:
     * The left boundary are node 1,2,4. (4 is the left-most node according to definition)
     * The leaves are node 4,7,8,9,10.
     * The right boundary are node 1,3,6,10. (10 is the right-most node).
     * So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
     *
     * References :
     * https://www.leetfree.com/problems/boundary-of-binary-tree.html
     *
     */
    List<Integer> left_boundary;
    List<Integer> right_boundary;
    List<Integer> leaves;

    public boolean isLeaf(Node root) {
        return (root.left == null && root.right == null);
    }

    public boolean isRightBoundary(int flag) {
        return (flag == 2);
    }

    public boolean isLeftBoundary(int flag) {
        return (flag == 1);
    }

    public boolean isRoot(int flag) {
        return (flag == 0);
    }

    public int leftChildFlag(Node parent, int parentFlag) {
        if (isLeftBoundary(parentFlag) || isRoot(parentFlag))
            return 1;
        else if (isRightBoundary(parentFlag) && parent.right == null)
            return 2;
        else return 3;
    }

    public int rightChildFlag(Node parent, int parentFlag) {
        if (isRightBoundary(parentFlag) || isRoot(parentFlag))
            return 2;
        else if (isLeftBoundary(parentFlag) && parent.left == null)
            return 1;
        else return 3;
    }

    public void preorder(Node root, int flag) {
        if (root == null)
            return;
        if (isRightBoundary(flag))
            right_boundary.add(0, root.data);
        else if (isLeftBoundary(flag) || isRoot(flag))
            left_boundary.add(root.data);
        else if (isLeaf(root))
            leaves.add(root.data);
        preorder(root.left, leftChildFlag(root, flag));
        preorder(root.right, rightChildFlag(root, flag));
    }

    public List<Integer> boundaryOfBinaryTree(Node root) {
        left_boundary = new LinkedList<>();
        right_boundary = new LinkedList<>();
        leaves = new LinkedList<>();
        preorder(root, 0);
        left_boundary.addAll(leaves);
        left_boundary.addAll(right_boundary);
        return left_boundary;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();



	/*

	 				1
	 			/	   \
	 		   2		3
	 		  / \	   /
	         4   5    6
	        / \      /
	       7   8     9
	      /			/
	     10		   11
	     		  /
				12
				  \
				   13
	 */

        BinaryTree.root = new Node(1);
        BinaryTree.root.left = new Node(2);
        BinaryTree.root.right = new Node(3);
        BinaryTree.root.left.left = new Node(4);
        BinaryTree.root.left.left.left = new Node(7);
        BinaryTree.root.left.left.left.left = new Node(10);
        BinaryTree.root.left.left.right = new Node(8);
        BinaryTree.root.left.right = new Node(5);
        BinaryTree.root.right.left = new Node(6);
        BinaryTree.root.right.left.left = new Node(9);
        BinaryTree.root.right.left.left.left = new Node(11);
        BinaryTree.root.right.left.left.left.left = new Node(12);
        BinaryTree.root.right.left.left.left.left.right = new Node(13);

        BoundaryTraversal boundaryTraversal = new BoundaryTraversal();
        List<Integer> res = boundaryTraversal.boundaryOfBinaryTree(BinaryTree.root);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
