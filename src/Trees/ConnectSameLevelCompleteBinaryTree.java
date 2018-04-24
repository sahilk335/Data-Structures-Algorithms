package Trees;

public class ConnectSameLevelCompleteBinaryTree {
    /*
     *@Author : Sahil
     * Date : 23 Apr 2018
     *
     *
     * Given a binary tree
     *
     * struct TreeLinkNode {
     *   TreeLinkNode *left;
     *   TreeLinkNode *right;
     *   TreeLinkNode *next;
     * }
     *
     * Populate each next pointer to point to its next right node. If there is no next right node,
     * the next pointer should be set to NULL.
     *
     * Initially, all next pointers are set to NULL.
     *
     * Note:
     *
     * You may only use constant extra space.
     * Recursive approach is fine, implicit stack space does not count as extra space for this problem.
     * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
     * Example:
     *
     * Given the following perfect binary tree,
     *
     *      1
     *    /  \
     *   2    3
     *  / \  / \
     * 4  5  6  7
     * After calling your function, the tree should look like:
     *
     *      1 -> NULL
     *    /  \
     *   2 -> 3 -> NULL
     *  / \  / \
     * 4->5->6->7 -> NULL
     *
     * References :
     * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
     */

    public void connect(Node root) {
        if (root == null)
            return;

        Node levelFirstNode = root;

        while (levelFirstNode != null && levelFirstNode.left != null) {
            Node curr = levelFirstNode;
            while (curr != null) {
                curr.left.next = curr.right;
                if (curr.next == null) {
                    curr.right.next = null;
                } else {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            levelFirstNode = levelFirstNode.left;
        }
    }

    public void printTreeNext(Node root) {

        Node levelFirstNode = root;

        while (levelFirstNode != null) {
            Node curr = levelFirstNode;
            while (curr != null) {
                System.out.print(curr.data + " ");
                curr = curr.next;
            }
            levelFirstNode = levelFirstNode.left;
        }
    }

    public static void main(String args[]) {
        ConnectSameLevelCompleteBinaryTree connect = new ConnectSameLevelCompleteBinaryTree();
        /*

	 				1
	 			/	   \
	 		   2		3
	 		  / \	   / \
	         4   5    6   7

	     */
        BinaryTree.root = new Node(1);
        BinaryTree.root.left = new Node(2);
        BinaryTree.root.right = new Node(3);
        BinaryTree.root.left.left = new Node(4);
        BinaryTree.root.left.right = new Node(5);
        BinaryTree.root.right.left = new Node(6);
        BinaryTree.root.right.right = new Node(7);

        connect.connect(BinaryTree.root);

        connect.printTreeNext(BinaryTree.root);
    }
}
