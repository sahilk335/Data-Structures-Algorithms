package Trees;

public class ArbitrarySumTree {
    /*
     *@Author : Sahil Khurana
     * Date : 05 June 2019
     * Convert an arbitrary Binary Tree to a tree that holds Children Sum Property
     * Question: Given an arbitrary binary tree, convert it to a binary tree that holds Children Sum Property. You can only increment data values in any node (You cannot change the structure of the tree and cannot decrement the value of any node).
     *
     * For example, the below tree doesn’t hold the children sum property, convert it to a tree that holds the property.
     *
     *              50
     *            /     \
     *          /         \
     *        7             2
     *      / \             /\
     *    /     \          /   \
     *   3        5      1      30
     * Algorithm:
     * Traverse the given tree in post order to convert it, i.e., first change left and right children to hold the children sum property then change the parent node.
     *
     * Let difference between node’s data and children sum be diff.
     *
     *      diff = node’s children sum - node’s data
     * If diff is 0 then nothing needs to be done.
     *
     *
     *
     * If diff > 0 ( node’s data is smaller than node’s children sum) increment the node’s data by diff.
     *
     * If diff < 0 (node’s data is greater than the node's children sum) then increment one child’s data. We can
     * choose to increment either left or right child if they both are not NULL. Let us always first increment the
     * left child. Incrementing a child changes the subtree’s children sum property so we need to change left subtree
     * also. So we recursively increment the left child. If left child is empty then we recursively call increment()
     * for right child.
     *
     * Let us run the algorithm for the given example.
     *
     * First convert the left subtree (increment 7 to 8).
     *
     *              50
     *            /     \
     *          /         \
     *        8             2
     *      / \             /\
     *    /     \          /   \
     *   3        5      1      30
     * Then convert the right subtree (increment 2 to 31)
     *
     *           50
     *         /    \
     *       /        \
     *     8            31
     *    / \           / \
     *  /     \       /     \
     * 3       5    1       30
     * Now convert the root, we have to increment left subtree for converting the root.
     *
     *           50
     *         /    \
     *       /        \
     *     19           31
     *    / \           /  \
     *  /     \       /      \
     * 14      5     1       30
     * Please note the last step – we have incremented 8 to 19, and to fix the subtree we have incremented 3 to 14.
     *
     *
     * References : https://www.geeksforgeeks.org/convert-an-arbitrary-binary-tree-to-a-tree-that-holds-children-sum-property/
     *
     *
     * Note: No node's data should be decremented. You can only increment the data
     *
     */

    public void incrementChild(Node root, int increment) {
        if (root == null || (root.left == null && root.right == null))
            return;

        if (root.left != null) {
            root.left.data = root.left.data + increment;
            incrementChild(root.left, increment);
        } else {      // reach here only if left child was null
            root.right.data = root.right.data + increment;
            incrementChild(root.right, increment);
        }
    }

    public int toChildSumTree(Node root) {
        if (root == null)
            return 0;

        //If leaf return itself's data
        if (root.left == null && root.right == null)
            return root.data;


        int sumLeft = toChildSumTree(root.left);
        int sumRight = toChildSumTree(root.right);

        //chota hai khud to bacho waala sum bnja
        if (root.data < sumLeft + sumRight) {
            root.data = sumLeft + sumRight;
        } else if (root.data > sumLeft + sumRight) { //nhi toh bacho ko bada kr
            incrementChild(root, root.data - (sumLeft + sumRight));
        }

        //return its own data to its parent
        return root.data;
    }

    public static void main(String[] args) {
        BinaryTree.root = new Node(50);
        BinaryTree.root.left = new Node(7);
        BinaryTree.root.right = new Node(2);
        BinaryTree.root.left.left = new Node(3);
        BinaryTree.root.left.right = new Node(5);
        BinaryTree.root.right.left = new Node(1);
        BinaryTree.root.right.right = new Node(30);

        PrintTree printTree = new PrintTree();
        printTree.inorder(BinaryTree.root);

        System.out.println("\n");

        ArbitrarySumTree arbitrarySumTree = new ArbitrarySumTree();
        arbitrarySumTree.toChildSumTree(BinaryTree.root);

        printTree.inorder(BinaryTree.root);

    }
}
