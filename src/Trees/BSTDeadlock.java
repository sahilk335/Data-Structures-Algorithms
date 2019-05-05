package Trees;

public class BSTDeadlock {
    /*
     *@Author : Sahil
     * Date : 01 May 2019
     *
     * Check whether BST contains Dead End or not
     * Given a Binary search Tree that contains positive integer values greater then 0. The task is to check whether the BST contains a dead end or not. Here Dead End means, we are not able to insert any element after that node.
     *
     * Examples:
     *
     * Input :        8
     *              /   \
     *            5      9
     *          /   \
     *         2     7
     *        /
     *       1
     * Output : Yes
     * Explanation : Node "1" is the dead End because
     *          after that we cant insert any element.
     *
     * Input :       8
     *             /   \
     *            7     10
     *          /      /   \
     *         2      9     13
     *
     * Output : Yes
     * Explanation : We can't insert any element at
     *              node 9.
     *
     * References :
     * https://www.geeksforgeeks.org/check-whether-bst-contains-dead-end-not/
     *
     *
     * Solution :
     * 1. Maintain minimum and maximum at each node.
     * 2. If at any point  cuur.data -> min= curr.data-1 && max=curr.data+1 . Then that point is deadlock or deadEnd
     * 3. if any point is curr.data=1, then also it is deadend as ,only positive number should be present in BST.
     *
.     */

    public Node bstDeadlockUtil(Node root, int min, int max) {
        if (root == null)
            return null;

        if (root.data + 1 == max && root.data - 1 == min)
            return root;

        Node left = bstDeadlockUtil(root.left, min, root.data);
        Node right = bstDeadlockUtil(root.right, root.data, max);

        if (left == null && right == null)
            return null;

        if (left == null)
            return right;

        return left;
    }

    public Node bstDeadlock(Node root) {
        return bstDeadlockUtil(root, 0, Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        BSTDeadlock bstDeadlock = new BSTDeadlock();
         /*

	 	 8
       /   \
      5    11
     /  \
    2    7
     \
      3
       \
        4
	 */

        BinarySearchTree.root = new Node(8);

        //left subtree
        BinarySearchTree.root.left = new Node(5);
        BinarySearchTree.root.right = new Node(11);
        BinarySearchTree.root.left.left = new Node(2);
        BinarySearchTree.root.left.right = new Node(7);
        BinarySearchTree.root.left.left.right = new Node(3);
        BinarySearchTree.root.left.left.right.right = new Node(4);
        System.out.println(bstDeadlock.bstDeadlock(BinarySearchTree.root).data);
    }
}
